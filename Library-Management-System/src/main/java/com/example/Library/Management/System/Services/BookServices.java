package com.example.Library.Management.System.Services;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Enum.Genre;
import com.example.Library.Management.System.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.System.Repository.AuthorRepository;
import com.example.Library.Management.System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServices {
    @Autowired
    public BookRepository brObj;
    @Autowired
    private AuthorRepository arObj;
    public String addBook(Book book, Integer authorId)throws Exception
    {
        //What are the steps of code that we should we write here ???
        //Final goal is : to save the book Entity
        //Author object is missing : how to get author Entity ????
        Optional<Author> optionalAuthor=arObj.findById(authorId);
        if(!optionalAuthor.isPresent())
        {
            throw new AuthorNotFoundException("Author  Id Entered is Invalid ");
        }
        Author author=optionalAuthor.get();
        book.setAuthor(author);
        //Bcz its a bidirectional mapping :
        //Author should also have the information of the Book Entity
        author.getBookList().add(book);


        //Now book and author Entity both have been modified :


        //I will save only the author Entity
        //And because of cascading effect : book Entity will get autosaved

        arObj.save(author);


        return "Book has been added to the DB";

    }
    public List<String> getBookByGenre(Genre genre)
    {
        List<Book> bookList=brObj.findBooksByGenre(genre);
        List<String> bookNames=new ArrayList<>();
        for(Book obj:bookList)
        {
            bookNames.add(obj.getBookName());
        }
        return bookNames;
    }
}
