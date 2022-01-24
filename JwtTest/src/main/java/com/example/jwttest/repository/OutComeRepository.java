package com.example.jwttest.repository;

import com.example.jwttest.entity.Income;
import com.example.jwttest.entity.OutCome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutComeRepository extends JpaRepository<OutCome,Integer> {

}
