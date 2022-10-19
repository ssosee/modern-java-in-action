# 람다

함수형 인터페이스라는 문맥에서 람다 표현식 사용 가능

## 함수형 인터페이스
정확히 하나의 추상 메서드를 지정하는 인터페이스

또한, 많은 디폴트 메서드가 있더라도 추상 메서드가 1개라면 함수형인터페이스다.
```java
public interface Predicate<T> {
    boolean test(T t);
    default boolean test2(T t) {
        //...디폴트 메서드 구현...//
    }
}
```

## 람다의 특징
* **익명**
  * 보통의 메서드와 달리 이름이 없으므로 `익명`이라고 함.
* **함수**
  * 메서드 처럼 특정 클래스에 종속되지 않으므로 `함수`라고 한다.
* **전달**
  * `인수`로 전달하거나 `변수`로 저장할 수 있다.
* **간결성**
  * 익명 클래스처럼 자질구레한 코드를 구현할 필요가 없다.


```java
//불리언 표현식
(List<String> list) -> list.isEmpty()

//객체 생성
() -> new Apple()

//객체에서 소비
(Apple a) -> {System.out.println(a.getColor())}

//객체에서 선택/추출
(String s) -> s.length()

//두 값을 조합
(int a, int b) -> a * b

//두 객체 비교
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
```