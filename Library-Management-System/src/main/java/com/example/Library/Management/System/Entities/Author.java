package com.example.Library.Management.System.Entities;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Author {
    @Id
    private Integer authorId;
    @Column(nullable = false)
    private String authorName;
    private int age;
    private double rating;
    //Author should also have info of child class book
    @OneToMany(mappedBy = "author",cascade=CascadeType.ALL)
    private List<Book> bookList=new ArrayList<>();
    // here under mapedBy="here write the name with which u are making the object in its child table or class"

//    public Integer getAuthorId() {
//        return authorId;
//    }
//
//    public void setAuthorId(Integer authorId) {
//        this.authorId = authorId;
//    }
//
//    public String getAuthorName() {
//        return authorName;
//    }
//
//    public void setAuthorName(String authorName) {
//        this.authorName = authorName;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public double getRating() {
//        return rating;
//    }
//
//    public void setRating(double rating) {
//        this.rating = rating;
//    }
//
//    public List<Book> getBookList() {
//        return bookList;
//    }
//
//    public void setBookList(List<Book> bookList) {
//        this.bookList = bookList;
//    }
}
