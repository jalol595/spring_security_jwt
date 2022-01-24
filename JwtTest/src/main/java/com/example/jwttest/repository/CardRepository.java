package com.example.jwttest.repository;

import com.example.jwttest.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer> {
    Optional<Card> findByUsername(String username);


    @Query(value = "select payment(:from_card ,:to_card ,:amount)",nativeQuery = true)
    Boolean payment(@Param("amount") Double amount, @Param("from_card") Integer fromCard, @Param("to_card") Integer toCard);
}
