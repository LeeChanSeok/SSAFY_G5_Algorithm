import java.util.Arrays;

class Solution {

    public String[] solution(String[] files) {
        int filesLen = files.length;

        String[] answer = new String[filesLen];
        String[][] split = new String[filesLen][3];

        String file;
        String head, number, tail;
        int len, idx;
        char c;
        for(int i = 0; i < filesLen; ++i) {
            file = files[i];

            head = number = tail = "";
            len = file.length();

            // 1. head
            for(idx = 0; idx < len; ++idx) {
                c = file.charAt(idx);
                if(c >= '0' && c <= '9') break;
                head+=c;
            }

            // 2. number
            for(; idx < len; ++idx) {
                c = file.charAt(idx);
                if(c < '0' || c > '9') break;
                number += c;
            }

            // 3. tail
            tail = file.substring(idx);

            split[i][0] = head;
            split[i][1] = number;
            split[i][2] = tail;

        }

        Arrays.sort(split, (f1, f2) -> {
            if(f1[0].compareToIgnoreCase(f2[0]) == 0) {
                return Integer.parseInt(f1[1]) - Integer.parseInt(f2[1]);
            }
            return f1[0].compareToIgnoreCase(f2[0]);
        });

        for(int i = 0; i < filesLen; ++i) {
            answer[i] = split[i][0] + split[i][1] + split[i][2];
        }

        return answer;
    }
}
