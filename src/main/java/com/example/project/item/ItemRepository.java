package com.example.project.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
// 인터 페이스 를 만들 어도 같은 이름의 클래스 를 자동 생성 해줌 (DB 입출력 함수 있다. )

    Page<Item> findPageBy(Pageable page);
    List<Item> findByTitleContains(String title);
}
