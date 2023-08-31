package com.example.memo.service;

import com.example.memo.dto.MemoDTO;
import com.example.memo.entity.Category;
import com.example.memo.entity.Memo;
import com.example.memo.repository.CategoryRepository;
import com.example.memo.repository.MemoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Memo createMemo(MemoDTO memoDTO) {
        Optional<Memo> findOne = memoRepository.findByName(memoDTO.getName());
        if(findOne.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 메모입니다.");
        }

        Category category = categoryRepository.getCategoryById(memoDTO.getCategory_id());

        Memo memo = Memo.builder()
                .category(category)
                .name(memoDTO.getName())
                .content(memoDTO.getContent())
                .build();
        return memoRepository.save(memo);
    }

    public Memo getMemoById(Long id) {
        return memoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 존재하지 않습니다."));
    }

    public Page<Memo> getMemos(Pageable pageable, String keyword) {
        if(keyword == null) {
            return memoRepository.findAll(pageable);
        }
        else {
            return memoRepository.findByNameContains(pageable, keyword);
        }
    }

    public Memo updateMemo(MemoDTO memoDTO, Long id) {
       Memo duplicateMemo = memoRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정할 메모가 없습니다."));

        if(!memoDTO.getCategory_id().equals(duplicateMemo.getCategory().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "카테고리 ID가 일치하지 않습니다.");
        }

        Memo updateMemo = Memo.builder()
                .id(duplicateMemo.getId())
                .category(duplicateMemo.getCategory())
                .name(memoDTO.getName())
                .content(memoDTO.getContent())
                .build();
        return memoRepository.save(updateMemo);
    }

    public Memo deleteMemo(Long id) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "메모가 존재하지 않습니다."));
        memoRepository.deleteById(id);
        return memo;
    }

}
