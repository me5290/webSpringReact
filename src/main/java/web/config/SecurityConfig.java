package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.service.MemberService;

@Configuration
public class SecurityConfig { // 시큐리티를 커스텀 하는 곳
    @Autowired
    private MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // 1. HTTP 요청에 따른 부여된 권한/상태 확인 후 PATH/resource 제한
        http.authorizeHttpRequests(
            authorizeRequest -> authorizeRequest.requestMatchers(AntPathRequestMatcher.antMatcher("/board")).authenticated() // board : 인증된(로그인 된) 회원
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/board/write")).hasAnyRole("USER") // board/write : 인증된(로그인 된) 회원이면서 role이 USER이면 허가
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/chatting")).hasAnyRole("TEAM1","TEAM2") // chat : 인증(로그인)되고 role이 TEAM1 이거나 TEAM2이면 허가
                    .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll() // 그외 PATH(/**)는 모두 허가
        );

        // 2. 로그인 폼 커스텀
        // http.formLogin(AbstractHttpConfigurer::disable); // 시큐리티가 제공하는 로그인 폼을 사용안함
        http.formLogin(     // axios , ajax 사용 시 contentType : form
            로그인관련매개변수 -> 로그인관련매개변수
                .loginPage("/member/login")                     // 로그인을 할 view url 정의
                .loginProcessingUrl("/member/login/post.do")    // 로그인을 처리할 url 정의
                .usernameParameter("memail")                    // 로그인에 사용할 id 변수명
                .passwordParameter("mpassword")                 // 로그인에 사용할 pw 변수명
                .defaultSuccessUrl("/")                         // 로그인 성공 시 이동할 url
                .failureForwardUrl("/member/login")             // 로그인 실패 시 이동할 url
        );

        // 3. 로그아웃 커스텀
        http.logout(
            로그아웃관련매개변수 -> 로그아웃관련매개변수
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout/get.do"))  // 로그아웃 처리 요청 url
                .logoutSuccessUrl("/") // 로그아웃 성공 시 이동할 url
                .invalidateHttpSession(true) // 세션 초기화 유무
        );

        // 4. csrf(post,put 요청 금지) 공격 방지 : 특정(인증/허가) url만 post,put 가능하도록
        http.csrf(AbstractHttpConfigurer :: disable); // csrf 사용안함

        // 5. 로그인 처리에 필요한 서비스를 등록
        http.userDetailsService( memberService);

        return http.build();
    }

    // 2. 시큐리티가 패스워드 검증할때 사용할 암호화 객체
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
