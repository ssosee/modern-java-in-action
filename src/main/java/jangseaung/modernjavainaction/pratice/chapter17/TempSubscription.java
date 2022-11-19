package jangseaung.modernjavainaction.pratice.chapter17;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.*;

public class TempSubscription implements Subscription {
    private final Subscriber<? super TempInfo> subscriber;
    private final String town;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public TempSubscription(Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(long n) {
        // Subscriber가 만든 요청을 반복
//        for(long i = 0L; i < n; i++) {
//            try {
//                subscriber.onNext(TempInfo.fetch(town));
//            } catch (Exception e) {
//                subscriber.onError(e);
//                break;
//            }
//        }

        executor.submit(() -> {
            for (long i = 0L; i < n; i++) {
                try {
                    subscriber.onNext(TempInfo.fetch(town));
                } catch (Exception e) {
                    subscriber.onError(e);
                    break;
                }
            }
        });
    }

    @Override
    public void cancel() {
        // 구독이 취소가되면
        // 완료(onComplete) 신호를 Subscriber로 전달
        subscriber.onComplete();
    }
}
