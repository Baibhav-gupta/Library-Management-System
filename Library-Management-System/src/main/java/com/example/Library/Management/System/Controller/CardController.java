package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.LibraryCard;
import com.example.Library.Management.System.Services.CardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
public class CardController {
    @Autowired
    private CardServices csObj;

    @PostMapping("generatePlainCard")
    public ResponseEntity generatePlainCard()
    {
        LibraryCard newCard=csObj.generatedCard();
        String response="The New card is generated and having a cardNo = "+newCard.getCardNo();
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @PutMapping("/associateWithStudent")
    public ResponseEntity associateWithStudent(@RequestParam("studentId")Integer studentId,@RequestParam("cardNo")Integer cardNo)
    {
        String result= csObj.associateStudentWithCard(studentId,cardNo);
        return new ResponseEntity(result,HttpStatus.OK);
    }
}
