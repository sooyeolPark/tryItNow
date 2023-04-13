package study.example.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//설정 정보 사용 특징
//메서드 이름을 자유롭게 줄 수 있다.
//스프링 빈이 스프링 코드에 의존하지 않는다.
//코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료
//메서드를 적용할 수 있다.
public class NetworkClientViaBean {

    private String url;

    public NetworkClientViaBean() {
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

    public void close() throws Exception {
        System.out.println("NetworkClientViaBean.close");
        disconnect();
    }

    public void init() throws Exception {
        System.out.println("NetworkClientViaBean.init");
        connect();
        call("초기화 연결 메시지");
    }
}
