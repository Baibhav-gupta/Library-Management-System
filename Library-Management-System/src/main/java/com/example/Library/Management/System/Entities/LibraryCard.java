package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="library_card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;
    @Enumerated(value= EnumType.STRING)
    private CardStatus cardStatus;
    private String nameOnCard;
    private Integer noOfBooksIssued;

    /*
        Library Card needs to be connected with the student Table
     */
    @OneToOne
    @JoinColumn
    private Student student;
    //This variable is to be put in mappedBy attribute in the parent class

    @OneToMany(mappedBy = "card",cascade=CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();
    //Done

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public Integer getNoOfBooksIssued() {
        return noOfBooksIssued;
    }

    public void setNoOfBooksIssued(Integer noOfBooksIssued) {
        this.noOfBooksIssued = noOfBooksIssued;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
