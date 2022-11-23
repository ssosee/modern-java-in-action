package jangseaung.modernjavainaction.chapter18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;

public class Practice {
    static List<List<Integer>> subsets(List<Integer> list) {
        if(list.isEmpty()) {
            ArrayList<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }

        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        List<List<Integer>> subans = subsets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans);

        return cancat(subans, subans2);
    }

    private static List<List<Integer>> cancat(List<List<Integer>> a, List<List<Integer>> b) {
        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
        List<List<Integer>> result = new ArrayList<>();
        for(List<Integer> list : lists) {
            ArrayList<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }

        return result;
    }

    static int factorialIterativeByFor(int n) {
        int r = 1;
        for(int i = 0; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    static long factorialIterativeByRec(int n) {
        //재귀 팩토리얼 입력 값에 비례하여 메모리 사용량 증가
        return n == 1 ? 1 : n * factorialIterativeByRec(n - 1);
    }

    static long factorialIterativeStream(int n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long a, long b) -> a * b);
    }

    static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    private static long factorialHelper(long i, long n) {
        return n == 1 ? n : factorialHelper(i * n, n-1);
    }
}
