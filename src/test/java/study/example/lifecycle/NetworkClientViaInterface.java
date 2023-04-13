package study.example.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//초기화, 소멸 인터페이스 단점
//이 인터페이스는 스프링 전용 인터페이스다. 해당 코드가 스프링 전용 인터페이스에 의존한다.
//초기화, 소멸 메서드의 이름을 변경할 수 없다.
//내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.
public class NetworkClientViaInterface implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClientViaInterface() {
        System.out.println("생성자 호출, url = "+ url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String msg) {
        System.out.println("call : " + url + " // message = " + msg);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClientViaInterface.destroy");
        disconnect();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClientViaInterface.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }
}
