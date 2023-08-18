package com.example.memo.controller;

import com.example.memo.dto.CategoryDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 모든 데이터는 요청과 응답의 body를 json으로 전송하게 한다.
                 // 별다른 어노테이션 없이 메서드에 객체를 전달할 시 json으로 응답한다.
@RequestMapping("/categories")
public class CategoryController {
    @PostMapping("")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {  // HTTP 요청의 body부분을 가져온 후,
                                                                               // body의 내용을 Category DTO에 담아서 처리함
        return categoryDTO;  // 자동으로 json으로 응답
    }
}
