package com.example.jwttest.service;

import com.example.jwttest.dto.ApiResponse;
import com.example.jwttest.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AbstractService<T, DTO> {
    Page<T> getAllPageable(Integer page, Integer size);

    List<T> getAll();

    T add(DTO dto);

    Object delete(Integer id);

    T edit(UserDto userDto, Integer id);
}
