package jangseaung.modernjavainaction.pratice.chapter17;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.time.LocalDateTime;

public class TempObserver implements Observer<TempInfo> {
    /**
     * Observable은 역압력은 지원하지 않아
     * request 메소드가 필요 없다.
     */
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull TempInfo tempInfo) {
        System.out.println(LocalDateTime.now()+" :: "+tempInfo);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        System.err.println("Got Problem: "+e.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("RxJava Done!");
    }
}
