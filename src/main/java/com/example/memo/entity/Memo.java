package com.example.memo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)  // 외래키 설정, 데이터를 가져올 때마다 Category의 데이터가 필요하면 Eager(첫 로딩시 같이 가져옴),
                                         // 항상 필요하지 않으면 LAZY(나중에 getCategory를 사용하면 가져옴)
    @JoinColumn(name = "category_id")  // 외래키 설정
    Category category;

    @Column(length = 100)
    String name;

    @Column(columnDefinition = "TEXT")  // columnDefinition:타입 지정할 때 사용, TEXT는 사이즈 제한 없는 텍스트를 저장
    String content;

    @Builder
    public Memo(Long id, Category category, String name, String content) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.content = content;
    }
}
