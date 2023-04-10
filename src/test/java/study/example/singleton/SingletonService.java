package study.example.singleton;

public class SingletonService {

    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    private  static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    //외부 생성을 막는다.
    private  SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
