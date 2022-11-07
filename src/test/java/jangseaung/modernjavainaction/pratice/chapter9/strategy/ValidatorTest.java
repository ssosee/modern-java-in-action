package jangseaung.modernjavainaction.pratice.chapter9.strategy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ValidatorTest {
    @Test
    void strategyTest() throws Exception {

        //람다 사용 X
        Validator numericValidator = new Validator(new IsNumericCase());
        boolean notLambda = numericValidator.validate("aaa");

        //람다 사용 O
        Validator numericValidatorLambda = new Validator((s) -> s.matches("a-z+"));
        boolean lambda = numericValidatorLambda.validate("aaa");

        assertThat(notLambda).isFalse();
        assertThat(lambda).isFalse();
    }
}