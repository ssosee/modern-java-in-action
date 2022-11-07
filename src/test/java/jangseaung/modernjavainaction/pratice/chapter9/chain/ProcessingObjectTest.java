package jangseaung.modernjavainaction.pratice.chapter9.chain;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class ProcessingObjectTest {

    @Test
    void chainResponse() throws Exception {
        //람다 사용 X
        ProcessingObject<String> p1 = new HeaderTestProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);

        //람다 사용 O
        /**
         * UnaryOperator<String> 형식의 인스턴스로 표현
         * UnaryOperator Type T의 인자 하나를 받고, 동일한 Type T 객체를 리턴하는 함수형 인터페이스입니다.
         * andThen 메소드로 함수 조합 가능
         *
         * Function<T, R>는 T타입의 인자를 받고, R타입의 객체를 리턴합니다.
         */
        UnaryOperator<String> headerProcessing = (String text) -> "Use Lambda From Raoul, Mario and Alan: "+text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        //<T=String, R=String>
        String lambdaResult = pipeline.apply("Aren't labdas really sexy"); //T타입을 받고 R타입 리턴!
        System.out.println(lambdaResult);

    }
}