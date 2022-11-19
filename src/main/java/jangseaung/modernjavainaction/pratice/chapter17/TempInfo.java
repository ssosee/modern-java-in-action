package jangseaung.modernjavainaction.pratice.chapter17;

import lombok.Getter;
import lombok.ToString;

import java.util.Random;

@Getter
@ToString(of = {"town", "temp"})
public class TempInfo {
    public static final Random random = new Random();

    private final String town;
    private final int temp;

    public TempInfo(String town, int temp) {
        this.town = town;
        this.temp = temp;
    }

    public static TempInfo fetch(String town) {
        //10%의 확률로 온도 가져오기 작업이 실패
        if (random.nextInt(10) == 0) {
            throw new RuntimeException("Error");
        }
        //0~99 임의의 화씨 온도를 반환
        return new TempInfo(town, random.nextInt(100));
    }
}
