package jangseaung.modernjavainaction.pratice.chapter9.observer;

public interface Subject {
    void registerObserver(Observer observer);
    void notifyObserver(String tweet);
}
