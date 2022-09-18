import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        int orderSize = orders.length;
        int[] Iorders = new int[orderSize];

        // 'A' 를 0 번쨰 'Z'를 26번째 비트로 생각하여 string -> int 값으로 변환
        for(int i = 0; i < orderSize; i++) {
            String order = orders[i];
            int temp = 0;
            for(int j = 0, size = order.length(); j < size; j++) {
                char c = order.charAt(j);
                temp |= 1 << (c - 'A');
            }
            Iorders[i] = temp;
        }

        // course개수 마다 최대값을 저장하기 위한 배열
        int[] maxCnt = new int[11];

        // course개수 마다 만들 수 있는 메뉴 조합들
        List<Integer>[] menu = new ArrayList[11];
        for(int i = 0; i < 11; i++)
            menu[i] = new ArrayList<>();

        // 0 ~ 26 비트에 해당하는 모든 값에 대해서
        for(int flag = 3, size = 1 << ('Z' - 'A' + 1); flag < size; flag++) {
            // flag가 가지는 1의 개수(선택한 메뉴의 수)
            int count = subsetCount(flag);
            if(count < 2 || count > 10 || !isCourse(count, course)) continue;

            // 선택한 메뉴 조합을 몇명의 사용자가 선택하였는지 확인
            int cnt = 0;
            for(int i = 0; i < orderSize; i++) {
                if((Iorders[i] & flag) == flag) cnt++;
            }

            // 해당 course 개수에서 선택한 메뉴를 더 많은 손님이 포함하고 있는 경우 max값 및 메뉴 조합 갱신
            if(cnt >= maxCnt[count]) {
                if(cnt > maxCnt[count]) {
                    maxCnt[count] = cnt;
                    menu[count].clear();
                }
                menu[count].add(flag);
            }

        }

        // 선택된 메뉴 조합들을 알파벳으로 치환
        StringBuilder sb;
        int size = 'Z' - 'A' + 1;
        for(int num : course) {

            if(maxCnt[num] < 2) continue;
            for(int cs : menu[num]) {
                sb = new StringBuilder();
                for(int i = 0; i < size; i++) {
                    if((cs & 1 << i) != 0) sb.append((char)('A' + i));
                }
                answer.add(sb.toString());
            }
        }
        Collections.sort(answer);
        return  answer.toArray(new String[answer.size()]);
    }

    private static boolean isCourse(int count, int[] course) {
        for(int num : course)
            if(num == count) return true;
        return false;
    }

    private static int subsetCount(int flag) {
        int cnt = 0;
        int size = 'Z' - 'A' + 1;
        for(int i = 0; i < size; i++) {
            if((flag & 1 << i) !=  0) cnt++;
            if(cnt > 10) break;
        }
        return cnt;
    }
}