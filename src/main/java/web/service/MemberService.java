package web.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;
import web.model.entity.BoardEntity;
import web.model.entity.MemberEntity;
import web.model.repository.MemberEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {
    @Autowired
    MemberEntityRepository memberEntityRepository;

    // 시큐리티 로그인 커스텀
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 로그인창에서 입력받은 아이디
        System.out.println("username = " + username);

        // 2. 입력받은 아이디로 실제 아이디와 실제 (암호화된)패스워드
            // 2-1. memail 이용한 회원엔티티 찾기
        MemberEntity memberEntity = memberEntityRepository.findByMemail(username);

        // - ROLE 부여
        List<GrantedAuthority> 등급목록 = new ArrayList<>();
        등급목록.add(new SimpleGrantedAuthority("ROLE_USER")); // ROLE_등급명

        // 3. UserDetails 반환 [1.실제 아이디 , 2.실제 패스워드]
            // UserDetails 목적 : Token에 입력받은 아이디/패스워드 검증하기 위한 실제 정보 반환
        UserDetails userDetails = User.builder()
                .username(memberEntity.getMemail())
                .password(memberEntity.getMpassword())
                .authorities(등급목록) // ROLE 등급
                .build();
        return userDetails;
    }

    // 1. 회원가입 (시큐리티 사용시 패스워드 암호화)
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
        // 1.
//        MemberEntity result1 = memberEntityRepository.findByMemailAndMpassword(memberDto.getMemail() , memberDto.getMpassword());
//        System.out.println("result1 = " + result1);

        // 2.
//        boolean result2 = memberEntityRepository.existsByMemailAndMpassword(memberDto.getMemail(), memberDto.getMpassword());
//        System.out.println("result2 = " + result2);

        // 3.
        MemberEntity result3 = memberEntityRepository.findByLoginSQL(memberDto.getMemail(), memberDto.getMpassword());
        System.out.println("result3 = " + result3);

        if (result3 == null){
            return false;
        }

        // 세션부여
        request.getSession().setAttribute("LoginInfo",result3.toDto());

        return true;
    }

    // 3. 로그아웃(세션 삭제)
    public boolean doLogoutGet(){
        request.getSession().setAttribute("LoginInfo",null);
        return true;
    }

    // 4. 현재 로그인된 회원정보 호출(세션 반환 값 호출)
    public MemberDto doLoginInfo(){
        // 시큐리티 사용 전
//        Object object = request.getSession().getAttribute("LoginInfo");
//        if (object != null){
//            return (MemberDto) object;
//        }else {
//            return null;
//        }

        // 1. 시큐리티를 사용했을때 Principal : 본인/주역/주체자 - 브라우저마다 1개
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("object = " + object);

        // 2. 만약에 로그인 상태가 아니면
        if(object.equals("anonymousUser")){ // anonymous : 익명/비로그인
            return null;
        }

        // 3. 로그인 상태이면 UserDetails 타입 변환
        UserDetails userDetails = (UserDetails) object;

        // 4. 로그인 성공한 엔티티 찾기
        MemberEntity memberEntity = memberEntityRepository.findByMemail(userDetails.getUsername());

        // 5. 회원정보(비밀번호 제외)
        return MemberDto.builder()
                .memail(memberEntity.getMemail())
                .mname(memberEntity.getMname())
                .mno(memberEntity.getMno())
                .build();
    }

    // 5. 아이디 중복검사
    public boolean doIdConfirm(String id){
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();

        for (int i=0; i < memberEntityList.size();i++){
            MemberEntity m = memberEntityList.get(i);
            System.out.println(m.getMpassword());
            if(m.getMemail().equals(id)){
                return true;
            }
        }
        return false;
    }

    // 5-2 아이디 중복검사
    public boolean getFindMemail(String memail){
        // 1. 모든 엔티티에서 해당 필드의 값을 찾는다
//        memberEntityRepository.findAll().forEach((m)->{
//            if (m.getMemail().equals(memail)){
//
//            }
//        });

        // 2. 리포지토리 추상메소드 이용하는 방법
//        MemberEntity result1 = memberEntityRepository.findByMemail(memail);
//        System.out.println("result1 = " + result1);

        // 3. 특정 필드의 조건으로 존재여부 검색
        boolean result2 = memberEntityRepository.existsByMemail(memail);
        System.out.println("result2 = " + result2);

        // 4. 직접 native SQL 지원
//        MemberEntity result3 = memberEntityRepository.findByMemailSQL(memail);
//        System.out.println("result3 = " + result3);

        return result2;
    }

    // 6. (로그인)내가쓴글
    public List<Map<Object,Object>> findMyBoardList(){
        // 1. 세션에서 로그인된 회원번호 찾는다
        MemberDto loginDto = doLoginInfo();

        if(loginDto == null){
            return null;
        }

        // ================= 양방향일때 ================= //
            // 로그인된 회원번호를 이용한 엔티티 찾기
        /*Optional<MemberEntity> optionalMemberEntity = memberEntityRepository.findById(loginDto.getMno());

        if (optionalMemberEntity.isPresent()) { // findById의 결과에 엔티티 존재하면
            MemberEntity memberEntity = optionalMemberEntity.get();

            // 내가 쓴 글
            List<BoardEntity> result1 = memberEntity.getBoardEntityList();
            System.out.println("내가 쓴 글 result1 = " + result1);

            // 내가 쓴 글 엔티티 리스트를 --> 내가 쓴글 DTO 리스트로 변환
            List<BoardDto> boardDtoList = new ArrayList<>();
            result1.forEach((entity)->{
                boardDtoList.add(entity.toDto());
            });
            return boardDtoList;

        }else {
            return null;
        }*/

        // ================= 단방향일때 ================= //
        List<Map<Object,Object>> result2 = memberEntityRepository.findByMyBoardSQL(loginDto.getMno());
        return result2;
    }
}
/*
    Optional 클래스
        - 해당 객체가 null 일수도 있고 아닐수 있다.
        - 혹시나 검색결과가 없을경우 null 반환될때 패키징
*/