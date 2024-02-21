package com.example.Library.Management.System.Services;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Entities.LibraryCard;
import com.example.Library.Management.System.Entities.Transaction;
import com.example.Library.Management.System.Enum.CardStatus;
import com.example.Library.Management.System.Enum.TransactionStatus;
import com.example.Library.Management.System.Exceptions.BookNotAvailableException;
import com.example.Library.Management.System.Exceptions.BookNotFound;
import com.example.Library.Management.System.Exceptions.InvalidCardStatusException;
import com.example.Library.Management.System.Exceptions.MaxBooksAlreadyIssued;
import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository trObj;

    @Autowired
    private CardRepository crObj;
    @Autowired
    private BookRepository brObj;

    private static final Integer MAX_LIMIT_OF_BOOKS=3;
    private static final Integer FINE_PER_DAY =5;

    public String issueBook(Integer bookId,Integer cardId)throws Exception{
        Transaction transaction=new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        //Validations

        //Valid BookId
        Optional<Book> bookOptional=brObj.findById(bookId);
        if(!bookOptional.isPresent())//ispresnt check is null or not
        {
            throw new BookNotFound("The entered bookId is invalid");
        }
        Book book=bookOptional.get();
        //check Availablity of book

        if(!book.isAvailable()) {
            throw new BookNotAvailableException("Book is Unavailable");

        }
        // check for valid cardId
        Optional<LibraryCard> optlc=crObj.findById(cardId);
        if(!optlc.isPresent())
        {
            throw new ClassCastException("CardId is invalid");
        }
        LibraryCard lc=optlc.get();
        //valid card status
        if(!lc.getCardStatus().equals(CardStatus.ACTIVE))
        {
            throw new InvalidCardStatusException("Card status is not active");
        }
        //Max no of book issued:limit =3
        if(lc.getNoOfBooksIssued()==MAX_LIMIT_OF_BOOKS)
        {
            throw new MaxBooksAlreadyIssued(MAX_LIMIT_OF_BOOKS+" is max limit of book that can be issued");
        }
        //creating the transaction entity
        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        lc.setNoOfBooksIssued(lc.getNoOfBooksIssued()+1);
        book.setAvailable(false);
        // setting fk
        transaction.setBook(book);
        transaction.setCard(lc);

        // saving relevent entities : bidirectional mapping
        book.getTransactionList().add(transaction);
        lc.getTransactionList().add(transaction);

        // Insted of saving parent , jst save the child
        trObj.save(transaction);

        return "The book with bookId "+bookId+" has been issued " +
                "to card with "+cardId;
    }
    public String returnBook(Integer bookId,Integer cardId)
    {
        Book book=brObj.findById(bookId).get();
        LibraryCard card=crObj.findById(cardId).get();

        // i need to find out that issue transaction
        Transaction transaction=trObj.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);

        Date issueDate=transaction.getCreatedOn();
        // predefined method use to calculate days

        long millisecond=Math.abs(System.currentTimeMillis()-issueDate.getTime());
        Long days= TimeUnit.DAYS.convert(millisecond,TimeUnit.MILLISECONDS);
        int fine=0;
        if(days>15)
        {
            fine=Math.toIntExact((days-15)*FINE_PER_DAY);
        }
        Transaction newTransaction=new Transaction();
        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fine);
        //set fk
        newTransaction.setBook(book);
        newTransaction.setCard(card);

        book.setAvailable(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);

        book.getTransactionList().add(newTransaction);
        card.getTransactionList().add(newTransaction);
        trObj.save(newTransaction);
        return "Book has been returned";
    }

}
