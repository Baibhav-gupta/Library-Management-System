package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    private AuthorService asObj;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author)
    {
        String result=asObj.addAuthor(author);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/findAllAuthorName")
    public List<String> findAllAuthorName()
    {
        return asObj.getAllAuthorNames();
    }
    @GetMapping("/getAuthor/{id}")
    public ResponseEntity getAuthor(@PathVariable("id")Integer id)
    {
        try{
            Author author=asObj.getAuthorById(id);
            return new ResponseEntity(author,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getBookNameList")
    public ResponseEntity getBookNameList(@RequestParam("authorId")Integer authorId)
    {
        List<String> bookNames=asObj.getBookNames(authorId);
        return new ResponseEntity(bookNames,HttpStatus.OK);
    }//done;

}
