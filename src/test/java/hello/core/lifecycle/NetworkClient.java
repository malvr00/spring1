package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//implements InitializingBean, DisposableBean
public class NetworkClient  {

    private String url;

    public NetworkClient (){
        System.out.println("생성자 호출 , url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String message){
        System.out.println("call : " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("close = " + url);
    }

//    // 의존 관계가 끝나면 호출 ( 콜백 함수 )
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    // 종료 될때 호출 ( 콜백 함수 )
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }

    @PostConstruct
    public void init(){
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception{
        disconnect();
    }
}
