package jangseaung.modernjavainaction.pratice.chapter9.observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObserverTest {

    @Test
    void observerTest() throws Exception {
        //람다 사용 X
        Subject subject = new Feed();
        subject.registerObserver(new Guardian());
        subject.registerObserver(new NYTimes());
        subject.notifyObserver("apple is money!!");

        //람다 사용 O
        Subject lambdaSubject = new Feed();
        subject.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")) {
                System.out.println("Use Lambda, Breaking news in NY! "+tweet);
            }
        });
        subject.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("apple")) {
                System.out.println("Use Lambda, Breaking news in Guardian! "+tweet);
            }
        });
    }

}