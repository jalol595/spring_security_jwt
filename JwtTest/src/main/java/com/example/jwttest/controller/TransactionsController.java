package com.example.jwttest.controller;

import com.example.jwttest.dto.ApiResponse;
import com.example.jwttest.dto.PaymentDto;
import com.example.jwttest.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/payment")
public class TransactionsController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public HttpEntity<?> doPayment(@RequestBody PaymentDto paymentDto, HttpServletRequest request){
        ApiResponse apiResponse = paymentService.doPaymentService(paymentDto,request);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}
