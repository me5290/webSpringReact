package web.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import web.model.dto.BoardDto;

import java.util.ArrayList;
import java.util.List;

@Entity // 해당 클래스와 연동DB내 테이블과 매핑/연결 (ORM)
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class BoardEntity extends BaseTime{ // 테이블
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    @Column(columnDefinition = "Longtext")
    private String bcontent;
    @Column
    @ColumnDefault("0")
    private int bview;

    // FK 필드
    @JoinColumn(name = "mno_fk") // fk 필드명
    @ManyToOne // 다수가 하나에게
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .mno_fk(memberEntity.getMno())
                .memail(memberEntity.getMemail())
                .cdate(this.getCdate())
                .udate(this.getUdate())
                .build();
    }
}