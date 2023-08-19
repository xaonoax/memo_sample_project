package com.example.memo.controller;

import com.example.memo.dto.MemoDTO;
import com.example.memo.entity.Memo;
import com.example.memo.service.MemoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memos")
@AllArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @PostMapping("")
    public Memo createMemo(@RequestBody MemoDTO memoDTO) {
        return memoService.createMemo(memoDTO);
    }

    @GetMapping("/{id}")
    public Memo getMemoById(@PathVariable Long id) {
        return memoService.getMemoById(id);
    }

    @GetMapping("")
    public Page<Memo> getMemos(Pageable pageable, @RequestParam String keyword) {
        return memoService.getMemos(pageable, keyword);
    }

    @PutMapping("/{id}")
    public Memo updateMemo(@RequestBody MemoDTO memoDTO, @PathVariable Long id) {
        return memoService.updateMemo(memoDTO, id);
    }

    @DeleteMapping("/{id}")
    public Memo deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id);
    }
}
