package web.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.entity.BoardEntity;
import web.model.entity.FileEntity;

@Repository
public interface FileEntityRepository extends JpaRepository<FileEntity,String>{

}
