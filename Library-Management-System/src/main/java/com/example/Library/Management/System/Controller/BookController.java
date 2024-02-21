package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Enum.Genre;
import com.example.Library.Management.System.Services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController
{
    @Autowired
    private BookServices bsObj;

    @PostMapping("addBook")
    public ResponseEntity addBook(@RequestBody Book book, @RequestParam("authorId")Integer authorId)
    {
        try{
            String result=bsObj.addBook(book,authorId);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getBookByGenre")
    public List<String> getBookByGenre(@RequestParam("genre") Genre genre)
    {
        return null;
    }
}
