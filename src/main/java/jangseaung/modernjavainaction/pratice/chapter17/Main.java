package jangseaung.modernjavainaction.pratice.chapter17;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.concurrent.Flow.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //안양에 새 Publisher를 만들고 TempSubsciber를 구독시킴
//        getTemperatures("안양시").subscribe(new TempSubscriber());
//        getTemperatures("용인시").subscribe(new TempSubscriber());

//        getCelsiusTemperature("안양시").subscribe(new TempSubscriber());

//        Observable<TempInfo> observable = getTemperature("안양시");
//        observable.blockingSubscribe(new TempObserver());

//        Observable<TempInfo> observable = getCelsiusTemperature("안양시");
//        observable.blockingSubscribe(new TempObserver());

//        Observable<TempInfo> observable = getNegativeTemperature("안양시");
//        observable.blockingSubscribe(new TempObserver());

        Observable<TempInfo> observable = getCelsiusTemperatures("안양시", "용인시", "서울시");
        observable.blockingSubscribe(new TempObserver());
    }

    //구독한 Subscriber에게 TempSubscription을 전송하는 Publisher를 반환
//    public static Publisher<TempInfo> getTemperatures(String town) {
//        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
//    }
//
//    public static Publisher<TempInfo> getCelsiusTemperature(String town) {
//        return subscriber -> {
//            TempProcessor processor = new TempProcessor();
//            processor.subscribe(subscriber);
//            processor.onSubscribe(new TempSubscription(processor, town));
//        };
//    }

    public static Observable<TempInfo> getTemperature(String town) {
        return Observable.create(emitter -> {
            Observable.interval(1, TimeUnit.SECONDS)
                    .subscribe(i -> {
                        //소비된 옵저버가 아직 폐기되지 않았으면 어떤 작업을 수행
                        if(!emitter.isDisposed()) {
                            if(i >= 5) {
                                emitter.onComplete(); //종료
                            } else {
                                try {
                                    emitter.onNext(TempInfo.fetch(town));
                                } catch (Exception e) {
                                    emitter.onError(e);
                                }
                            }
                        }
                    });
        });
    }

    public static Observable<TempInfo> getCelsiusTemperature(String town) {
        return getTemperature(town)
                .map(t -> new TempInfo(
                        t.getTown(),
                        (t.getTemp() - 32) * 5 / 9 ));
    }

    public static Observable<TempInfo> getNegativeTemperature(String town) {
        return getCelsiusTemperature(town)
                .filter(t -> t.getTemp() < 0);
    }

    // Observable은 전달된 Iterable에 포함된
    // 모든 Observable의 이벤트 발행물을
    // 시간 순서대로 방출
    public static Observable<TempInfo> getCelsiusTemperatures(String... towns) {
        return Observable.merge(Arrays.stream(towns)
                .map(Main::getCelsiusTemperature)
                .collect(Collectors.toList()));
    }
}
