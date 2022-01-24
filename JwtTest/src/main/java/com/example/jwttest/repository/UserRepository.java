package com.example.jwttest.repository;

import com.example.jwttest.entity.MyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findMyUserByUsername(String username);

    @Query(nativeQuery = true, value = "select\n" +
            "       case when count(m) = 1\n" +
            "           then true\n" +
            "           else false\n" +
            "           end\n" +
            "from my_user m\n" +
            "where m.username = :username\n" +
            " and m.card_id = :card_id")
    Boolean isAppropriateUsernameAndCardId(@Param("card_id") Integer cardId, @Param("username") String userName);
}
