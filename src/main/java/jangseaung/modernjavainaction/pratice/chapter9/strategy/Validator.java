package jangseaung.modernjavainaction.pratice.chapter9.strategy;

public class Validator {
    private final ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String s) {
        return this.validationStrategy.execute(s);
    }
}
