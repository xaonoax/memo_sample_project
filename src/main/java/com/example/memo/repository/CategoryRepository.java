package com.example.memo.repository;

import com.example.memo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  // 데이터에 접근하는 객체를 만들 때 사용.
// 인터페이스로 생성한 이유 : 인터페이스로 어떤 메서드를 사용할 지만 선언해주면 JPA에서 db 접근 코드를 생성하여 DI통해 주입해줌.
public interface CategoryRepository extends JpaRepository<Category, Long> {  // JPA를 사용하겠다는 선언
                                                                             // 왼쪽 : 다루는 데이터 타입, 오른쪽 name의 타입
    Optional<Category> findByName(String name);  // 파라미터 name과 같은 name을 가진 Category를 리턴해줌

    Page<Category> findByNameContains(Pageable pageable, String name);  // Contains를 붙리면 검색하는 이름이 포함된 전부를 가져옴

    Category getCategoryById(Long category_id);
}
