package web.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                .mpassword(new BCryptPasswordEncoder().encode(this.mpassword))
                // new BCryptPasswordEncoder().encode(암호화 할 데이터)
                /* 암호화란?
                        암호 : 사람이 정보를 이해할 수 없도록
                            - 이해할수 없도록 자기만의 방법으로 변경
                            - 스프링 시큐리티가 제외하는 방법 : bcrypt 암호화 제공
                            - 종류 : 1. bcrypt , 2. AES-256 등등
                 */
                .build();
        // this : 해당 메소드를 호출한 인스턴스
    }
}
