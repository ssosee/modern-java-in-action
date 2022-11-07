package jangseaung.modernjavainaction.pratice.chapter9.chain;

public class HeaderTestProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return "From Raul, Mario and Alan: "+input;
    }
}
