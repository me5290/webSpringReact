package web.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MemberEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;
    private String mid;

    @ToString.Exclude // 객체 호출시 해당 필드 제외
    @OneToMany(mappedBy = "memberEntity") // 하나가 다수에게
    private List<BoardEntity> boardEntityList = new ArrayList<>();
}