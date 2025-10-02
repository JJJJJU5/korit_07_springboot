package tesz2;

import java.util.*;

public class cote {
    public static void main(String[] args) {
        int[] emergency = {3, 76, 24};
        int[] answer = Arrays.copyOfRange(emergency,0,emergency.length);
        Arrays.sort(answer);
        for (int i = 0; i < answer.length; i++) {
            answer[answer.length - 1 - i] = i+1;
        }
        System.out.println(Arrays.toString(answer));
    }
}





