package jangseaung.modernjavainaction.pratice.chapter9.observer;

public class Guardian implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("apple")) {
            System.out.println("Breaking news in Guardian! "+tweet);
        }
    }
}
