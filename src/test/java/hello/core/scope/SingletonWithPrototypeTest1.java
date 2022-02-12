package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean{
//        private final PrototypeBean prototypeBean;

        // 지정한 빈을 컨테이너에서 대신 찾아주는 DL(디펜더시 룩업) 서비스를 제공하는 기능
//        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        // 자바 표준 기술이다. 하지만 라이브러리를 추가해서 사용해야함.
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            // 항상 새로운 프로토타입 빈이 생성된다. ( 대신 조회해주는 대리자 )
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("ProtutupeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
