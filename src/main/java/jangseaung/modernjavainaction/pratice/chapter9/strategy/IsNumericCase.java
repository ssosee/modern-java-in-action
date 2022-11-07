package jangseaung.modernjavainaction.pratice.chapter9.strategy;

public class IsNumericCase implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("[\\d+]+");
    }
}
