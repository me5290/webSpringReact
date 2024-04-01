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

import java.util.ArrayList;
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
    public List<BoardDto> getBoard(){
        // 1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> list = boardEntityRepository.findAll();

        // 2. 엔티티를 DTO로 변환
        List<BoardDto> boardDtoList = new ArrayList<>();
            // 1. 꺼내온 엔티티를 순회한다.
        for(int i=0; i < list.size(); i++){
            // 2. 하나씩 엔티티를 꺼낸다.
            BoardEntity boardEntity = list.get(i);
            // 3. 해당 엔티티를 dto로 변환한다.
            BoardDto boardDto = boardEntity.toDto();
            // 4. 변환된 dto를 리스트에 담는다.
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
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
