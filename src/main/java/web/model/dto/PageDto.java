package web.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class PageDto extends BaseTimeDto{
    private int page; // 현재 페이지
    private int pageCount; // 총 페이지 수
    private List<Object> data; // 본문 내용들
}
