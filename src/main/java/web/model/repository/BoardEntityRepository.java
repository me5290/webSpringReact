package web.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.entity.BoardEntity;

@Repository // 매핑된 테이블의 엔티티/레코드 들을 조작/관리 하는 리모콘/인터페이스 역할
public interface BoardEntityRepository extends JpaRepository<BoardEntity,Integer> {

}
