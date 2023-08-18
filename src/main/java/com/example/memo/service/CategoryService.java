package com.example.memo.service;

import com.example.memo.dto.CategoryDTO;
import com.example.memo.entity.Category;
import com.example.memo.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor  // 모든 변수를 포함하는 생성자를 만들어 줌
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional  // Exception이 발생하면 DB를 롤백해준다.
    public Category createCategory(CategoryDTO categoryDTO) {
        // Repository에서 데이터 가져오기
        Optional<Category> findOne = categoryRepository.findByName(categoryDTO.getName());
        if(findOne.isPresent()) {  // 데이터가 이미 존재하면 Exception을 발생시키고 종료함
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 이름입니다.");
        }
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        category = categoryRepository.save(category);

        return category;
    }



}
