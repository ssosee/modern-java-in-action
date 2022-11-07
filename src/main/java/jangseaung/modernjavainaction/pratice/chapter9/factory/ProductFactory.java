package jangseaung.modernjavainaction.pratice.chapter9.factory;

import java.util.function.Supplier;

public class ProductFactory {
    public static Product createProduct(String name) {
        switch (name) {
            case "stock" : return new Stock();
            case "loan" : return new Loan();
            default: throw new IllegalArgumentException("해당 상품은 없습니다.");
        }
    }
}
