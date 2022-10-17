package jangseaung.modernjavainaction.pratice.chapter2;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class FilteringApples {

    public static final String GREEN = "green";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Apple> apples = Arrays.asList(
                new Apple("GREEN", 100),
                new Apple("RED", 140),
                new Apple("BLUE", 120)
        );

        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return Integer.valueOf(o1.getWeight()).compareTo(Integer.valueOf(o2.getWeight()));
            }
        });
        apples.sort((Apple a1, Apple a2) -> Integer.valueOf(a1.getWeight()).compareTo(Integer.valueOf(a2.getWeight())));

        /**
         * Runnable 코드로 블록 실행하기
         */
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("싱싱~");
            }
        });

        Thread t2 = new Thread(() -> System.out.println("싱싱"));

        /**
         * Callable을 결과로 반환
         */
        //테스트클 실행하는 스레드의 이름을 반환
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });

        Future<String> submit = executorService.submit(() -> Thread.currentThread().getName());
        System.out.println(submit.get().toString());
    }

    /**
     * 농부가 색에 따라서 필터링해야한다면??
     * 색을 파라미터화 하자!
     *
     * 거의 비슷한 코드가 반복 존재한다면 그 코드를 추상화한다.
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : result) {
            if(apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    enum Color {
        GREEN,
        RED
    }

    /**
     * 구현 코드를 보면 filterApplesByColor()과 대부분 중복된다.
     * 무게나 색 중 어떤 것을 기준으로 필터링 할지 가리키는 플래그를 추가하면 되지 않을까?
     */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : result) {
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 아래처럼 메서드를 정의하면
     * filterApplesByWeight(inventory, RED, 100, true);
     * filterApplesByWeight(inventory, GREEN, 150, false);
     *
     * 위 코드만 보면 true, false가 무엇을 의미하는지 알수 없다.
     */
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : result) {
            if((flag && apple.getColor().equals(color)) || (flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 전략 패턴을 사용
     * ApplePredicate은 사과 선택 전략을 캡슐화 했다.
     *
     * 전략패턴
     *  - 각 알고리즘(전략)을 캡슐화하는 알고리즘 패밀리를 정의해둔 다음에 런타임에 알고리즘을 선택하는 기법!
     *
     *  ApplePredicate : 알고리즘 패밀리
     *  AppleHeavyWeightPredicate, AppleHeavyGreenPredicate : 알고리즘 전략
     */
    public interface ApplePredicate {
        boolean test(Apple apple);
    }

    public static class AppleHeavyWeightPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    public static class AppleHeavyGreenPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return GREEN.equals(apple.getColor());
        }
    }

    /**
     * ApplePredicate 객체로 사과 검사 조건을 캡슐화
     *
     * 이제 빨간 사과, 초록 사과, 파란 사과를 필터링 하거나, 무게 150 이상 100 이하 같은 사과를 적절하게 구현하는 클래스를 만들면
     * 아래 메서드로 모두 해결 할 수 있다.
     *
     * ApplePredicate 객체에 의해 filterApples 메서드의 동작이 결정된다.
     * filterApples 메서드의 동작을 파라미터화 했다.!
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
