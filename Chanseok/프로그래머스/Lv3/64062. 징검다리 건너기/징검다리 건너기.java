class Solution {
    public int solution(int[] stones, int k) {
        int Len = stones.length;
        int max = 0;
        for(int stone : stones) {
            if(stone > max) max = stone;
        }

        int l = 0, r = max;
        int mid = 0;
        while(l <= r) {
            mid = (l + r) / 2;
            if(isPass(Len, stones.clone(), k, mid)) {
                l = mid+1;
            }else {
                r = mid-1;
            }

        }

        return l;
    }

    private boolean isPass(int len, int[] stones, int k, int mid) {

        for(int i = 0; i < len; ++i) {
            stones[i] -= mid;
        }

        int zeros = 0;
        for(int stone : stones) {
            if(stone <= 0) ++zeros;
            else {
                zeros = 0;
            }
            if(zeros >= k) return false;
        }

        return true;
    }
}
