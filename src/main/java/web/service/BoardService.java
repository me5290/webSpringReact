package web.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;
import web.model.dto.PageDto;
import web.model.entity.BoardEntity;
import web.model.entity.FileEntity;
import web.model.entity.MemberEntity;
import web.model.entity.ReplyEntity;
import web.model.repository.BoardEntityRepository;
import web.model.repository.FileEntityRepository;
import web.model.repository.MemberEntityRepository;
import web.model.repository.ReplyEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private FileService fileService;
    @Autowired
    private FileEntityRepository fileEntityRepository;


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

        // 파일 업로드
        for(int i = 0; i < boardDto.getUploadList().size(); i++) {
            String filename = fileService.fileUpload(boardDto.getUploadList().get(i));
            System.out.println("filename : " + filename);
            if(filename != null){
                FileEntity fileEntity = FileEntity.builder()
                        .bfile(filename)
                        .boardEntity(saveBoard)
                        .build();
                fileEntityRepository.save(fileEntity);
            }
        }

            // Fk 대입
        if (saveBoard.getBno() >= 1){
            saveBoard.setMemberEntity(memberEntity);
            return true;
        }

        return false;
    }

    // R
    @Transactional
    public PageDto getBoard(int page , int view){
        // ================ 1 ================ //
/*
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
                for(int j=0; j < boardEntity.getFileEntityList().size(); j++){
                    FileEntity fileEntity = boardEntity.getFileEntityList().get(j);

                }
            // 4. 변환된 dto를 리스트에 담는다.
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
*/
        // =================================== //
        // 1. pageable 인터페이스 이용한 페이징처리 (springframework.data.domain)
            // Pageable pageable = PageRequest.of(현재페이지 , 페이지당표시할레코드수)
        Pageable pageable = PageRequest.of( page-1 , view );

        // 페이징처리된 엔티티 호출
        Page<BoardEntity> boardEntityPage = boardEntityRepository.findAll( pageable );

        // List 아닌 Page 타입일때 List와 동일한 메소드 사용하고 추가 기능
            // 1. 전체 페이지수
        System.out.println("boardEntityPage.getTotalPages() = " + boardEntityPage.getTotalPages());
            // 2. 전체 게시물수
        System.out.println("boardEntityPage.getTotalElements() = " + boardEntityPage.getTotalElements());

        // 엔티티를 DTO로 변환
        List<Object> data = boardEntityPage.stream().map((board)->{
            return board.toDto();
        }).collect(Collectors.toList());



        // 2. 페이지DTO 반환 값 구성
        PageDto pageDto = PageDto.builder()
                .data(data) // 페이징 처리된 레코드들을 대입
                .page(page) // 현재 페이지
                .pageCount(boardEntityPage.getTotalPages()) // 전체 페이지 수
                .build();

        return pageDto;
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
    public boolean deleteBoard(int bno){
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

        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById(bno);
        int boardMno = optionalBoardEntity.get().getMemberEntity().getMno();
        System.out.println("로그인한 멤버 번호 = "+memberEntity.getMno());
        System.out.println("글쓴 멤버 번호 = "+boardMno);
        if(memberEntity.getMno() == boardMno){
            boardEntityRepository.deleteById(bno);
            return true;
        }
        return false;
    }
}
