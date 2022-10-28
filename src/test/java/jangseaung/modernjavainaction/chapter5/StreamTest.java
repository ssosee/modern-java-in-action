package jangseaung.modernjavainaction.chapter5;

import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
}
