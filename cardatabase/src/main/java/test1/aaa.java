package test1;

import java.util.HashMap;
import java.util.Map;

public class aaa {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 5, 5, 5};
        Map<Integer, Integer> answer = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            answer.put(array[i], answer.getOrDefault(array[i], 0) + 1);
            System.out.println(answer);
        }


    }
}
