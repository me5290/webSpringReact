package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.servlet.GraphQlWebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import web.controller.ChatSocket;

@Configuration // 스프링 컨테이너에 빈(설정객체) 등록
@EnableWebSocket // 웹소켓 매핑
public class WebSocketMapping implements WebSocketConfigurer {
    @Autowired
    private ChatSocket socket; // 채팅 관련 서버 소켓
    // * 스프링 버전에 따라 라이브러리 이름이 다를수 있다
        // * spring2.x : WebSocketConfigurer , spring3.x : WebSocketConfigurationSupport
    // 1. 웹소켓 매핑 등록
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // - ws로 요청된 url들을 어디로 핸들러 할건지 정의
        registry.addHandler(socket,"/chat");
    }
}
