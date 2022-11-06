package jangseaung.modernjavainaction.chapter5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class StreamTest {
    List<Dish> specialMenu = Arrays.asList(
            new Dish("1", 120),
            new Dish("2", 130),
            new Dish("3", 140),
            new Dish("4", 150),
            new Dish("5", 160),
            new Dish("6", 170)
    );

    @Test
    void streamTest() {
        //전체 요소를 검사
        specialMenu.stream()
                .filter(dish -> dish.calories < 140)
                .forEach(System.out::println);

        System.out.println("//===========================//");

        //순차적으로 요소를 검사하고 조건에 부합하지 않으면 바로 중단
        specialMenu.stream()
                .takeWhile(dish -> dish.calories < 140)
                .forEach(System.out::println);

        System.out.println("//===========================//");

        //순차적으로 요소를 검사하고
        //조건에 부합하지 않는 요소가 발견되면 그 요소들을 버린다.
        specialMenu.stream()
                .dropWhile(dish -> dish.calories < 140)
                .forEach(System.out::println);
    }


    @ToString(of = {"name", "calories"})
    static class Dish {
        private String name;
        private int calories;

        public Dish(String name, int calories) {
            this.name = name;
            this.calories = calories;
        }
    }

    List<Integer> num1 = Arrays.asList(1, 2, 3);
    List<Integer> num2 = Arrays.asList(5, 6);

    @Test
    void streamTest2() {
        List<int[]> collect1 = num1.stream()
                .flatMap(i -> num2.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());

        List<int[]> collect = num2.stream()
                .flatMap(i -> num1.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());
    }

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    @Test
    void streamTest3() {
        //2011년에 일어난 모든 트랜잭션을 찾아 오름차순으로
        List<Transaction> collect = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());

        //거래자가 근무하는 모든 도시를 중복 없이 나열
        List<String> collect1 = transactions.stream()
                .map(t -> t.getTrader().city)
                .distinct()
                .collect(toList());

//        Set<String> collect3 = transactions.stream()
//                .map(t -> t.getTrader().city)
//                .collect(Collectors.toSet());

        //케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬
        List<Trader> cambridge = transactions.stream()
                .map(t -> t.trader)
                .filter(tr -> tr.getName().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());

        //모든 거래자의 이름을 알파벳순으로 정렬해서 반환
        String collect2 = transactions.stream()
                .map(t -> t.getTrader().name)
                .distinct()
                .sorted()
//                .reduce("", (n1, n2) -> n1 + n2);
                .collect(Collectors.joining());

        //밀라노에 거래자가 있는지
        boolean milan = transactions.stream()
                .anyMatch(t -> t.trader.city.equals("Milan"));

        //케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력
        transactions.stream()
                .filter(t -> t.trader.city.equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        //전체 트랜잭션 중 최댓값
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        //전체 트랜잭션중 최솟값
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

    }

    @Data
    @AllArgsConstructor
    @ToString(of = {"name", "city"})
    static class Trader {
        private final String name;
        private final String city;
    }

    @Data
    @ToString(of = {"trader", "year", "value"})
    static class Transaction {
        private final Trader trader;
        private final int year;
        private final int value;
    }
}
