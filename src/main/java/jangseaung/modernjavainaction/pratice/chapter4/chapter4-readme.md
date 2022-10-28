# 스트림
데이터 처리 연산을 지원하도록 소스에서 추출된 `연속된 요소`로 정의
* 임시 구현 코드 대신 `선언형(질의)`으로 **컬렉션 데이터를 처리**할 수 있다.
* 멀티스레드 코드를 구현하지 않아도 **데이터를 투명하게 병렬로 처리**할 수 있다.

## 스트림 맛보기
저칼로리의 요리명을 반환하고, 칼로리를 기준으로 요리를 정렬하는 자바 코드를 작성하시오.

### 스트림 적용 전
```java
List<Dish> lowCaloricDishes = new ArrayList<>();
for(Dish dish : menu) {
    //누적자로 요소 필터링
    if(dish.getCalories() < 400) {
        lowCaloricDishes.add(dish);
    }
}

Collections.sort(lowCaloricDished, new Comparator<Dish>() {
    //익명클래스로 요리 정렬
    public int compare(Dish dish1, Dish dish2) {
        return Interger.compare(dish1.getCalories(), dish2.getCalories());
    }
});

List<String> lowCaloricDishesName = new ArrayList<>();
for(Dish dish : lowCaloricDishes) {
    //저칼로리 순으로 정렬된 리스트의 이름으로 새롭게 리스트에 저장
    lowCaloricDishesName.add(dish.getName());
}
```

### 스트림 적용 후
```java
List<String> lowCaloricDishesName = 
        menu.stream()
            .filter(d -> d.getCalories() < 400) //400 칼로리 이하 요리선택 
            .sorted(comparing(Dish::getCalories)) //칼로리로 요리 정렬
            .map(Dish::getName) //요리명 추출
            .collect(toList()); //모든 요리명을 리스트에 저장
        
// 멀티코어 아키텍처에서 병렬로 실행 
List<String> lowCaloricDishesName =
        menu.parallelStream()
            .filter(d -> d.getCalories() < 400) //400 칼로리 이하 요리선택 
            .sorted(comparing(Dish::getCalories)) //칼로리로 요리 정렬
            .map(Dish::getName) //요리 추출
            .collect(toList()); //모든 요리명을 리스트에 저장
```
### 스트림의 장점
1. **선언형으로 코드를 구현**
2. `filter`, `sorted`, `map`, `collect` 같은 연산을 고수준 빌딩 블록으로 이루어져 있기 때문에
특정 **스레딩 모델에 제한되지 않고 자유롭게 어떤 상황에서든 사용**할 수 있다.(**데이터 처리 파이프라인 사용**)
3. 데이터 처리 과정을 병렬화하면서 **스레드와 락을 걱정할 필요가 없다.**

## 컬렉션과 스트림
**데이터를 언제 계산**하느냐가 컬렉션과 스트림의 가장 큰 차이
* 컬렉션
  * 현재 자료구조가 포함하는 모든 값을 메모리에 저장하는 자료구조
  * 컬렉션의 모든 요소는 컬렌션에 추가하기 전에 계산되어야 함
* 스트림
  * 이론적으로 요청할 때만 요소를 계산하는 고정된 자료구조
  * 사용자가 요청하는 값만 스트림에서 추출

