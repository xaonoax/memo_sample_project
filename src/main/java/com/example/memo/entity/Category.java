package com.example.memo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity  // JPA에서 해당 정보를 통해 DB테이블을 생성하고 가져올 수 있게 된다.
@Getter
@ToString  // toString()을 자동으로 생성시켜줌
@NoArgsConstructor  // 파라미터가 없는 기본 생성자 추가
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // DB에서 값을 자동으로 생성해주게하는 설정.
    Long id;

    @Column(length = 100, unique = true)  // varchar 사이즈 = 100, 중복 데이터 저장 불가
    String name;

    @Builder
    public Category(String name) {
        this.name = name;
    }

}
