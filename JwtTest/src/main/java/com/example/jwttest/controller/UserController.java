package com.example.jwttest.controller;

import com.example.jwttest.dto.ApiResponse;
import com.example.jwttest.dto.UserDto;
import com.example.jwttest.entity.MyUser;
import com.example.jwttest.repository.UserRepository;
import com.example.jwttest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping(value = "/{username}")
    public HttpEntity<?> getUserByUserName(@PathVariable String username){
        Optional<MyUser> optional = userService.getOne(username);
        return ResponseEntity.status(optional.isPresent()?200:404).body(optional.orElse(null));
    }
    public HttpEntity<?> addUser(@RequestBody UserDto userDto){
        MyUser add = userService.add(userDto);
        return ResponseEntity.ok(add);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<?> editUser(@RequestBody UserDto userDto,@PathVariable  Integer id){
        MyUser edit = userService.edit(userDto, id);
        return ResponseEntity.ok(edit);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<?> deleteUser(@PathVariable Integer id){
        ApiResponse delete = userService.delete(id);
        return ResponseEntity.ok(delete);
    }


}
