package web.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import web.model.dto.BoardDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "file")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FileEntity extends BaseTime {
    @Id
    private String bfile;

    @JoinColumn(name = "bno_fk")
    @ManyToOne
    private BoardEntity boardEntity;
}
