package web.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity // 해당 클래스와 연동DB내 테이블과 매핑/연결 (ORM)
@Builder
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class BoardEntity { // 테이블
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    @Column(name = "title", length = 10, nullable = false , unique = true)
    private String btitle;

    private boolean 필드0;

    @Column
    private byte 필드1;
    private short 필드2;
    private long 필드3;

    private char 필드4;
    private float 필드5;
    private double 필드6;

    private Date 필드7;
    private LocalDateTime 필드8;
}
