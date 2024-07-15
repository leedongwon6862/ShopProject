package com.example.project.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString //object 변수 한눈에 보고 싶으면 .toString() 생략 가능 으로 출력 해주는 이노 테이션
public class Item {

    @Id // id 컬럼엔 @Id 붙이기 강요.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 상품들 구별 위해.(자동 1씩 증가)
    private Long id;

    private String title; //상품 이름

    private Integer price; //상품 가격




}
