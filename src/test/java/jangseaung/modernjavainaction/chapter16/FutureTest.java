package jangseaung.modernjavainaction.chapter16;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FutureTest {
    @Test
    void FutureTest() throws Exception {
        //given
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "hello");
        System.out.println(cf.get());

        CompletableFuture.runAsync(() -> System.out.println("runAsync"));
        //when

        //then
    }
}
