package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;


@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
