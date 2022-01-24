package com.example.jwttest.service;

import com.example.jwttest.config.JwtProvider;
import com.example.jwttest.dto.ApiResponse;
import com.example.jwttest.dto.PaymentDto;
import com.example.jwttest.entity.Card;
import com.example.jwttest.entity.Income;
import com.example.jwttest.entity.OutCome;
import com.example.jwttest.repository.CardRepository;
import com.example.jwttest.repository.IncomeRepository;
import com.example.jwttest.repository.OutComeRepository;
import com.example.jwttest.repository.UserRepository;
import com.sun.net.httpserver.HttpsParameters;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final IncomeRepository incomeRepository;
    private final OutComeRepository outComeRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public ApiResponse doPaymentService(PaymentDto paymentDto, HttpServletRequest request) {
        if (cardRepository.existsById(paymentDto.getToCard()) && cardRepository.existsById(paymentDto.getFromCard())) {
            String userNameFromRequest = getUserNameFromRequest(request);
            Boolean appropriateUsernameAndCardId = userRepository.isAppropriateUsernameAndCardId( paymentDto.getFromCard(),userNameFromRequest);
            if (!appropriateUsernameAndCardId){
                return new ApiResponse(false,"you are not have card that given cardId");
            }
            Boolean payment = cardRepository.payment(paymentDto.getAmount(), paymentDto.getFromCard(), paymentDto.getToCard());
            Card fromCard = cardRepository.getById(paymentDto.getFromCard());
            Card toCard = cardRepository.getById(paymentDto.getToCard());
            Income income = new Income(null, fromCard, toCard, paymentDto.getAmount(), new Timestamp(new Date().getTime()));
            incomeRepository.save(income);
            OutCome outCome = new OutCome(null, fromCard, toCard, paymentDto.getAmount(), new Timestamp(new Date().getTime()), paymentDto.getAmount() / 100);
            outComeRepository.save(outCome);
            return payment ? new ApiResponse(true, "transaction succesfully") : new ApiResponse(false, "error while transaction");
        } else return new ApiResponse(false, "not found your card id");

    }

    public String getUserNameFromRequest(HttpServletRequest request){
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader.startsWith("Bearer")){
            String[] splitted = authHeader.split(" ");
            String jwtToken = splitted[splitted.length-1];
            return jwtProvider.getSubject(jwtToken);
        }else return null;
    }
}
