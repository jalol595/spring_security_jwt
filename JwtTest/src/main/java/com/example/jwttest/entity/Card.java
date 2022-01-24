package com.example.jwttest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private Number cardBalance;

    @Column(nullable = false)
    private Date expiredDate;

    @Column(nullable = false)
    private Boolean active = false;

    @PrePersist
    private void onCreateObject(){
        expiredDate = new Date(System.currentTimeMillis()+1000*60*60*24*365L);
    }
}
