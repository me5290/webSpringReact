package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dto.MemberDto;
import web.model.entity.MemberEntity;
import web.model.repository.MemberEntityRepository;

@Service
public class MemberService {
    @Autowired
    MemberEntityRepository memberEntityRepository;
    public boolean doSignUpPost(MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);

        // -- Dao 아닌 엔티티 이용한 레코드 저장하는 방법
        // 1. 엔티티를 만든다.
//        MemberEntity memberEntity = MemberEntity.builder()
//                .memail(memberDto.getMemail())
//                .mpassword(memberDto.getMpassword())
//                .mname(memberDto.getMname())
//                .build();
        // 2. 리포지토리 통한 엔티티를 저장한다.
            // - dto랑 엔티티 서로 변환 후
        memberEntityRepository.save(memberDto.toEntity());

        return false;
    }
}
