package web.model.dto;

import lombok.*;
import web.model.entity.BoardEntity;
import web.model.entity.MemberEntity;
import web.model.entity.ReplyEntity;

import java.util.ArrayList;
import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto {
    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;

    // - dto를 엔티티로 변환하는 메소드 - 저장때문에
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .mname(this.mname)
                .memail(this.memail)
                .mrol(this.mrol)
                .mpassword(this.mpassword)
                .build();
        // this : 해당 메소드를 호출한 인스턴스
    }
}
