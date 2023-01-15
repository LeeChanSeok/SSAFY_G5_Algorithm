import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    long max; 
    int opCnt;
    char[] oper_order, operater;
    List<Long> numbers;
    List<Character> opers;
    
    public long solution(String expression) {

        String[] nums = expression.split("\\*|\\+|\\-");
        Set<Character> operset = new HashSet<>();

        int numLen = nums.length;
        numbers = new ArrayList<>(numLen);
        for(int i = 0; i < numLen; ++i) {
            numbers.add(Long.parseLong(nums[i]));
        }

        int idx = 0;
        opers = new ArrayList<>();
        char op;
        for(int i = 0; i < numLen-1; ++i) {
            idx += nums[i].length();
            op = expression.charAt(idx++);
            opers.add(op);
            operset.add(op);
        }

        opCnt = 0;
        operater = new char[operset.size()];
        for(char oper : operset) {
            operater[opCnt++] = oper;
        }
        oper_order = new char[opCnt];
        max = 0;
        perm(0, 0);

        return max;
    }
    private void perm(int idx, int flag) {

        if(idx == opCnt) {
            max = Math.max(max, calc_exp(numbers, opers));
            return;
        }

        for(int i = 0; i < opCnt; ++i) {
            if((flag & (1 << i)) != 0) continue;
            oper_order[idx] = operater[i];
            perm(idx+1, flag | (1 <<i));
        }

    }
    private long calc_exp(List<Long> numbers, List<Character> opers) {

        List<Long> number = new ArrayList<>(numbers);
        List<Character> oper = new ArrayList<>(opers);

        char op;
        for(int i = 0; i < opCnt; ++i) {
            op = oper_order[i];

            for(int j = 0; j < oper.size(); ++j) {
                if(op == oper.get(j)) {
                    calc(number, op, j);
                    oper.remove(j);
                    --j;
                }
            }
        }

        long res = number.get(0);
        if(res < 0) res *= -1;
        return res;
    }

    private void calc(List<Long> number, char op, int j) {
        long num1 = number.get(j);
        long num2 = number.get(j+1);
        long ans = 0;

        if(op == '+') ans = num1 + num2;
        else if(op == '-') ans = num1 - num2;
        else if(op == '*') ans = num1 * num2;

        for(int i = 0; i < 2; ++i) number.remove(j);
        number.add(j, ans);
    }
}
