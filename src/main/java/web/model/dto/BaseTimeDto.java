package web.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class BaseTimeDto {
    private LocalDateTime cdate;
    private LocalDateTime udate;
}
