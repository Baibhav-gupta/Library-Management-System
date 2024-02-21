package com.example.Library.Management.System.Services;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository arObj;

    public String addAuthor(Author obj)
    {
        arObj.save(obj);
        return "author has been added to Db";

    }
    public List<String> getAllAuthorNames()
    {
        List<Author> authorList=arObj.findAll();
        List<String> authorName=new ArrayList<>();
        for(Author obj:authorList)
        {
            authorName.add(obj.getAuthorName());
        }
        return authorName;
    }
    public Author getAuthorById(Integer id)throws Exception
    {
        Optional<Author> optAuthor=arObj.findById(id);
        if(!optAuthor.isPresent())
        {
            throw new Exception("The Entered id is Invalid");
        }
        Author author=optAuthor.get();
        return author;
    }
    public List<String> getBookNames(Integer authorId)
    {
        List<String> bookNames=new ArrayList<>();
        Author author=arObj.findById(authorId).get();
        List<Book> bokList=author.getBookList();
        for(Book book:bokList)
        {
            bookNames.add(book.getBookName());
        }
        return bookNames;

    }
}
