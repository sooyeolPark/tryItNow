package study.example.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    @DisplayName("인터페이스를 이용하여 생명주기 확인")
    public void lifeCycleTestViaInterface() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClientViaInterface client = ac.getBean(NetworkClientViaInterface.class);
        ac.close();
    }

    @Test
    @DisplayName("빈메서드를 이용하여 생명주기 확인")
    public void lifeCycleTestViaBean() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfigViaBean.class);
        NetworkClientViaBean client = ac.getBean(NetworkClientViaBean.class);
        ac.close();
    }

    @Test
    @DisplayName("어노테이션를 이용하여 생명주기 확인")
    public void lifeCycleTestViaAnnotation() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfigViaAnnotation.class);
        NetworkClientViaAnnotation client = ac.getBean(NetworkClientViaAnnotation.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClientViaInterface networkClient() {
            NetworkClientViaInterface networkClient = new NetworkClientViaInterface();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

    @Configuration
    static class LifeCycleConfigViaBean {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClientViaBean networkClient() {
            NetworkClientViaBean networkClient = new NetworkClientViaBean();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

    @Configuration
    static class LifeCycleConfigViaAnnotation {
        @Bean
        public  NetworkClientViaAnnotation networkClient() {
            NetworkClientViaAnnotation networkClient = new NetworkClientViaAnnotation();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
