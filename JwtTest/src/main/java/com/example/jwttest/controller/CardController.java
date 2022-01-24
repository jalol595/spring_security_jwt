package com.example.jwttest.controller;

import com.example.jwttest.dto.CardDto;
import com.example.jwttest.entity.Card;
import com.example.jwttest.service.CardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/card")
public class CardController {


    @Autowired
    private CardServiceImp cardServiceImp;

    @PostMapping
    public HttpEntity<?> addCard(@RequestBody  CardDto cardDto){
        Card add = cardServiceImp.add(cardDto);
        return ResponseEntity.status(add != null?200:404).body(add);
    }


    @GetMapping
    public HttpEntity<?> getPageable(@RequestParam Integer size,@RequestParam Integer page){
        Page<Card> allPageable = cardServiceImp.getAllPageable(page, size);
        return ResponseEntity.ok(allPageable);
    }

    @DeleteMapping
    public HttpEntity<?> deleteById(@PathVariable Integer id){
        Object delete = cardServiceImp.delete(id);
        return ResponseEntity.ok(delete);
    }




}
