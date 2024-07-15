package com.example.project.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
// 인터 페이스 를 만들 어도 같은 이름의 클래스 를 자동 생성 해줌 (DB 입출력 함수 잔득 )
}
