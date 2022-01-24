package com.example.jwttest.repository;

import com.example.jwttest.entity.Card;
import com.example.jwttest.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income,Integer> {

}
