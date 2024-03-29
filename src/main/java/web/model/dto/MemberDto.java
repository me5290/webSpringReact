package web.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import web.model.entity.BoardEntity;
import web.model.entity.MemberEntity;
import web.model.entity.ReplyEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class MemberDto extends BaseTimeDto{
    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;

    // - dto를 엔티티로 변환하는 메소드 - 저장때문에
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mname(this.mname)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .build();
        // this : 해당 메소드를 호출한 인스턴스
    }
}
