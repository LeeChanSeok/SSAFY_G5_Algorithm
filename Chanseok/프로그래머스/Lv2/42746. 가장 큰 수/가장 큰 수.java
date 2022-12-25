import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";

        int size = numbers.length;

        String[] number = new String[size];
        for(int i = 0; i < size; ++i)
            number[i] = String.valueOf(numbers[i]);

        Arrays.sort(number, (n1, n2) -> (n2+n1).compareTo(n1+n2));

        for(String num : number) answer += num;
        if(answer.charAt(0) == '0') answer = "0";

        return answer;
    }
}
