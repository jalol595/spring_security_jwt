package com.example.jwttest.service;

import com.example.jwttest.dto.ApiResponse;
import com.example.jwttest.dto.UserDto;
import com.example.jwttest.entity.Card;
import com.example.jwttest.entity.MyUser;
import com.example.jwttest.repository.CardRepository;
import com.example.jwttest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements AbstractService<MyUser, UserDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<MyUser> getAll() {
        return userRepository.findAll();
    }
    public Optional<MyUser> getOne(String username) {
        return userRepository.findMyUserByUsername(username);
    }

    @Override
    public Page<MyUser> getAllPageable(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public MyUser add(UserDto userDto) {
        Optional<Card> optionalCard = cardRepository.findById(userDto.getCardId());
        if (!optionalCard.isPresent())
            return null;
        Card card = optionalCard.get();
        MyUser myUser = new MyUser(null, userDto.getUsername(), userDto.getPassword(), null);
        MyUser save = userRepository.save(myUser);
        return save;
    }

    @Override
    public ApiResponse delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ApiResponse(true, "succesfully deleted");
        } else return new ApiResponse(false, "not found");
    }

    @Override
    public MyUser edit(UserDto userDto, Integer id) {
        Optional<MyUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            MyUser myUser = optionalUser.get();
            myUser.setUsername(userDto.getUsername());
            myUser.setPassword(userDto.getPassword());
            MyUser updated = userRepository.save(myUser);
            return updated;
        } else return null;
    }
}
