package com.example.jwttest.service;

import com.example.jwttest.dto.ApiResponse;
import com.example.jwttest.dto.CardDto;
import com.example.jwttest.dto.UserDto;
import com.example.jwttest.entity.Card;
import com.example.jwttest.repository.CardRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class CardServiceImp implements AbstractService<Card, CardDto>{
    @Autowired
    private CardRepository cardRepository;


    @Override
    public Page<Card> getAllPageable(Integer page, Integer size) {
        return null;
    }

    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card add(CardDto cardDto) {
        Card requiredFieldsCard = new Card();
        requiredFieldsCard.setCardNumber(String.valueOf(new Random().nextInt(10000000)));
        requiredFieldsCard.setCardBalance(cardDto.getCardBalance());
        requiredFieldsCard.setActive(true);
        requiredFieldsCard.setUsername(cardDto.getUsername());
        return cardRepository.save(requiredFieldsCard);
    }

    @Override
    public Object delete(Integer id) {
        if (cardRepository.existsById(id)){
            cardRepository.deleteById(id);
            return new ApiResponse(true,"succesfully deleted");
        }else return new ApiResponse(false,"not found");
    }

    @Override
    public Card edit(UserDto userDto, Integer id) {
        //TODO
        return null;
    }
}
