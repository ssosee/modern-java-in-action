package jangseaung.modernjavainaction.pratice.chapter1;

import java.util.ArrayList;
import java.util.List;

public class FilteringApples {

    private static final String GREEN = "green";

    public static void main(String[] args) {

    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory) {
            if(GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * filterGreenApples 를 복사&붙여넣기 함
     *
     * 어떤 코드에 버그가 있다면 복사&붙여넣기 한 모든 코드를 수정해야함... ㅠㅠ
     *
     * 자바8 에서는 코드를 인수로 넘겨줄 수 있으므로 filter 메서드르를 중복으로 구현할 필요 없다.!!
     */
    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory) {
            if(apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return GREEN.equals(apple.getColor());
    }


}
