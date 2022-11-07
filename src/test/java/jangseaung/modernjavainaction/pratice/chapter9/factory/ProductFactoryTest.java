package jangseaung.modernjavainaction.pratice.chapter9.factory;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class ProductFactoryTest {
    @Test
    void Factory() throws Exception {
        Product loan = ProductFactory.createProduct("loan");

        //Supplier<T>는 인자를 받지 않고 T 타입의 객체를 리턴합니다.
        Supplier<Product> productSupplier = Loan::new;
        Product product = productSupplier.get();

        Product stock = ProductFactoryTest.lambdaCreateProduct("stock");
    }


    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
    }

    public static Product lambdaCreateProduct(String name) {
        Supplier<Product> p = map.get(name);
        if (p != null) return p.get();
        throw new IllegalArgumentException("해당 상품은 없습니다.");
    }
}