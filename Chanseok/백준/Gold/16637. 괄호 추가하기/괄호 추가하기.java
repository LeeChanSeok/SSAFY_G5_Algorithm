import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static int max = -987654321;
	public static long[] num;
	public static char[] op;
	public static int N;

	public static long operator(long a, long b, char op) {
		long res = a;
		switch (op) {
			case '+': res += b; break;
			case '-': res -= b; break;
			case '*': res *= b; break;
		}
		return res;
	}

	public static long calc(boolean[] bracket) {
		//reduce brakcet
		List<Long> num_list = new ArrayList<>();
		List<Character> op_list = new ArrayList<>();
			
		
		int i = 0;
		int j = 0;
		
		while(true){
			if(bracket[i]) {
				num_list.add(operator(num[i], num[i+1], op[j]));
				i += 2;
				j++;
			}else {
				num_list.add(num[i++]);
			}
			
			if(j < op.length) {
				op_list.add(op[j++]);
			}else {
				break;
			}
		}
		
		long res = num_list.get(0);
		i = 1;
		for(;i<num_list.size();i++) {
			res = operator(res, num_list.get(i), op_list.get(i-1));
		}
				
		return res;
	}

	public static void recursive(boolean[] bracket, int idx) {
		if (idx == N / 2) {// 마지막 숫자인 경우
			long ans = calc(bracket); // 괄호없는 식에 대한 계산
			max = Math.max(max, (int) ans); // 최대값
			return;
		}

		if (idx != 0 && bracket[idx - 1]) { // 이전 숫자에 괄호가 있는 경우 괄호를 추가 할 수 없다.
			recursive(bracket, idx + 1);
			return;
		}

		// 현재 위치에서 괄호를 추가하는 경우와 안하는 경우
		recursive(bracket, idx + 1);
		bracket[idx] = true;
		recursive(bracket, idx + 1);
		bracket[idx] = false;
	}

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);

		//int T = sc.nextInt();
		int T = 1;
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 수식의 길이 입력
			String exp = sc.next(); // 수식 입력
			
			num = new long[N / 2 + 1];
			op = new char[N / 2];
			boolean[] bracket = new boolean[N / 2 + 1]; // 괄호 여부

			num[0] = exp.charAt(0) - '0';
			for (int i = 1; i <= N / 2; i++) {
				op[i-1] = exp.charAt(i * 2 - 1);
				num[i] = exp.charAt(i * 2) - '0';
			}

			max = -987654321;
			recursive(bracket, 0);
			System.out.println(max);
		}

	}

}