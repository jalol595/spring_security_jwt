package com.example.jwttest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


// Pul o'tkazmalarini amalga oshiruvchi dastur tuzing.
// User sistemaga authentication orqali kirib, o'ziga tegishli CARD orqali
// boshqa CARD ga pul transfer qilish jarayonini amalga oshirsin.
// Transfer jarayonida CARD da o'tkazilayotgan va transfer uchun kommisiya miqdoridagi mablag'
// yetarli ekanligi va CARD shu user ga tegishli ekanligi tekshirilsin.
// Kartadagi kirimlarni va chiqimlarni alohida yozib boring.
// Foydalanuvchi o'ziga tegishli card tarixini (output, income) ko'rsatuvchi method yozing.

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Card fromCardId;

    @ManyToOne(optional = false)
    private Card toCardId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Timestamp date;

}
