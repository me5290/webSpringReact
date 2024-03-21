package web.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity // 해당 클래스와 연동DB내 테이블과 매핑/연결 (ORM)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class BoardEntity { // 테이블
    @Id // PK
    private int bno;
    private String btitle;
}
