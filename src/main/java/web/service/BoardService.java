package web.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;
import web.model.entity.BoardEntity;
import web.model.entity.MemberEntity;
import web.model.entity.ReplyEntity;
import web.model.repository.BoardEntityRepository;
import web.model.repository.MemberEntityRepository;
import web.model.repository.ReplyEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepository replyEntityRepository;
    @Autowired
    private MemberService memberService;

    // C
    @Transactional
    public boolean postBoard(BoardDto boardDto){
        MemberDto loginDto = memberService.doLoginInfo();
        if (loginDto == null){
            return false;
        }

        // 1. 로그인된 회원 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity = memberEntityRepository.findById(loginDto.getMno());
        // 2. 찾은 엔티티가 존재하지 않으면
        if (!optionalMemberEntity.isPresent()){
            return false;
        }
        // 3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();
            // 글쓰기
        BoardEntity saveBoard = boardEntityRepository.save(boardDto.toEntity());
            // Fk 대입
        if (saveBoard.getBno() >= 1){
            saveBoard.setMemberEntity(memberEntity);
            return true;
        }

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
