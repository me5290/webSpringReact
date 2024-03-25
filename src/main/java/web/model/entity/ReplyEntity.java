package web.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;
    private String rcontent;

    // FK 필드
    @JoinColumn(name = "mno_fk") // fk 필드명
    @ManyToOne // 다수가 하나에게
    private MemberEntity memberEntity;
    @JoinColumn(name = "bno_fk") // fk 필드명
    @ManyToOne // 다수가 하나에게
    private BoardEntity boardEntity;
}
