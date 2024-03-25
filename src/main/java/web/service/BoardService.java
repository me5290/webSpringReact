package web.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.entity.BoardEntity;
import web.model.entity.MemberEntity;
import web.model.entity.ReplyEntity;
import web.model.repository.BoardEntityRepository;
import web.model.repository.MemberEntityRepository;
import web.model.repository.ReplyEntityRepository;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepository replyEntityRepository;

    // C
    @Transactional
    public boolean postBoard(){
        // 1. 회원가입
            // 1. 엔티티 객체 생성
        MemberEntity memberEntity = MemberEntity.builder()
                .memail("asd@naver.com")
                .mpassword("1234")
                .mname("유재석")
                .build();
            // 2. 해당 엔티티를 DB에 저장할수 있도록 조작
        MemberEntity saveMemberEntity = memberEntityRepository.save(memberEntity);

        // 2. 회원가입된 회원으로 글쓰기
            // 1. 엔티티 객체 생성
        BoardEntity boardEntity = BoardEntity.builder()
                .bcontent("게시물 글 입니다.")
                .build();
        boardEntity.setMemberEntity(saveMemberEntity); // 회원 엔티티 대입시 DB에서는 PK만 저장
            // 2. 리포지토리를 이용한 엔티티를 테이블에 대입
        BoardEntity saveBoardEntity = boardEntityRepository.save(boardEntity); // insert

        // 3. 해당 글에 댓글 작성
            // 1. 엔티티 객체 생성
        ReplyEntity replyEntity = ReplyEntity.builder()
                .rcontent("댓글입니다.")
                .build();
        replyEntity.setMemberEntity(saveMemberEntity);
        replyEntity.setBoardEntity(saveBoardEntity);
            // 2. 리포지토리를 이용한 엔티티를 테이블에 대입
        replyEntityRepository.save(replyEntity);

        return false;
    }

    // R
    @Transactional
    public List<Object> getBoard(){
        // 1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> list = boardEntityRepository.findAll();
        System.out.println("list = " + list);
        return null;
    }

    // U
    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById(1).get();
        boardEntity.setBcontent("JPA수정테스트");
        return false;
    }

    // D
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById(1);
        return false;
    }
}
