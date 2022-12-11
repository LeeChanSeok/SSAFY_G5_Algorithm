class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        int[] res = new int[n];
        for(int i = 0; i < n; ++i)
            res[i] = arr1[i] | arr2[i];

        String str;
        for(int i = 0; i < n; ++i) {
            str = "";
            for(int j = n-1; j >= 0; --j) {
                if((res[i] &(1 << j)) != 0) str+="#";
                else str+=" ";
            }
            answer[i] = str;
        }

        return answer;
    }
}
