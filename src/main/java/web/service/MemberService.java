package web.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.dto.MemberDto;
import web.model.entity.MemberEntity;
import web.model.repository.MemberEntityRepository;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberEntityRepository memberEntityRepository;

    // 1. 회원가입
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
        MemberEntity saveedEntity = memberEntityRepository.save(memberDto.toEntity());
        // 3. 엔티티가 생성이 되었는지 아닌지 확인(PK)
        if (saveedEntity.getMno()>0){
            return true;
        }else{
            return false;
        }
    }

    // 로그인 했다는 증거/기록
    @Autowired
    private HttpServletRequest request;

    // 2. 로그인
    public boolean doLoginPost(MemberDto memberDto){
        // 1. 리포지토리를 통한 모든 회원엔티티 호출
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();

        // 2. dto와 동일한 아이디 패스워드 찾는다
        for(int i=0; i < memberEntityList.size();i++){
            MemberEntity m = memberEntityList.get(i);
            // 3. 만약에 아이디가 동일하면 (엔티티와 DTO)
            if (m.getMemail().equals(memberDto.getMemail())){
                // 4. 만약에 비밀번호가 동일하면
                if(m.getMpassword().equals(memberDto.getMpassword())){
                    // 5. 세션 저장
                    request.getSession().setAttribute("LoginInfo",memberDto);
                    return true;
                }
            }
        }
        return false;
    }

    // 3. 로그아웃(세션 삭제)
    public boolean doLogoutGet(){
        request.getSession().setAttribute("LoginInfo",null);
        return true;
    }

    // 4. 현재 로그인된 회원정보 호출(세션 반환 값 호출)
    public MemberDto doLoginInfo(){
        Object object = request.getSession().getAttribute("LoginInfo");
        if (object != null){
            return (MemberDto) object;
        }else {
            return null;
        }
    }
}
