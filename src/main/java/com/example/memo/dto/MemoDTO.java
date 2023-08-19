package com.example.memo.dto;

import lombok.Data;

@Data
public class MemoDTO {
    String name;
    String content;
    Long category_id;
}
