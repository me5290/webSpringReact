package web.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import web.model.entity.BoardEntity;
import web.model.entity.ReplyEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class BoardDto extends BaseTimeDto{
    private int bno;
    private String bcontent;
    private int bview;
    private int mno_fk;     // (memberEntity) 회원 번호
    private String memail;  // (memberEntity) 회원 이메일

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent(this.bcontent)
                .build();
    }
}
