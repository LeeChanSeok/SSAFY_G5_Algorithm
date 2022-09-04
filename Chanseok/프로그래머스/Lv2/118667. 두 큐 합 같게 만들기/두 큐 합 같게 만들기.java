class Solution {
    static long TotalElementSum, HalfElementSum;
    static int qSize;
    static long min;

    public long solution(int[] queue1, int[] queue2) {

        qSize = queue1.length;
        TotalElementSum = calc_TotalSum(queue1, queue2);	// 전체 원소의 합
        HalfElementSum = TotalElementSum/2;	// 전체 원소의 합의 절반

        if(TotalElementSum % 2 != 0) return -1;
        if(isOver(queue1, queue2)) return -1;

        min = Integer.MAX_VALUE;	
        // 두 큐를 앞, 뒤 순서를 다르게 해서 하나의 큐로 생성
        simulation(queue1, queue2);	
        simulation(queue2, queue1);

        return (min == Integer.MAX_VALUE) ? -1 : min;
    }

    private static boolean isOver(int[] queue1, int[] queue2) {
        for(int i = 0; i < qSize; i++) {
            if(queue1[i] > HalfElementSum || queue2[i] > HalfElementSum) return true;
        }
        return false;
    }

    private static void simulation(int[] queue1, int[] queue2) {
        int[] queue = new int[qSize * 2];

        // 두개의 큐를 하나로 합침
        for(int i = 0; i < qSize; i++) {
            queue[i] = queue1[i];
            queue[i+qSize] = queue2[i];
        }

        // 구간 합 구하기
        // 주어진 큐에서 구간의 합이 전체 원소의 합의 절반이 되는 시작위치와 끝 위치 찾기
        long[] subSum = new long[queue.length];
        subSum[0] = queue[0];
        for(int i = 1, Size = queue.length; i < Size; i++) subSum[i] = subSum[i-1] + queue[i];

        int s = 0, e = 0;
        boolean isRight = false;
        while(s <= e) {
            if(s >= queue.length || e>= queue.length) return;
            long res = calc_subSum(subSum, s, e);
            if(res == HalfElementSum) {isRight = true; break;}
            else if(res < HalfElementSum) e++;
            else s++;

        }
        if(!isRight) return; // 조건에 만족하는 구간 합이 없는경우

        // s, e 위치에 따른  개수
        long ans = 0;
        if(s < qSize && e < qSize) {
            if(e ==  qSize - 1) ans = s;
            else ans = e + 1 + s + qSize ;
        }else if (s < qSize && e >= qSize){
            ans = s + (e - qSize + 1);
        }else if(s >= qSize && e >= qSize) {
            if(e ==  queue.length - 1) ans = s - qSize;
            else ans = e + 1 + s - qSize;
        }

        min = Math.min(min, ans);
    }

    private static long calc_subSum(long[] subSum, int s, int e) {
        if(s == 0) return subSum[e];
        return subSum[e] - subSum[s-1];

    }

    private static long calc_TotalSum(int[] queue1, int[] queue2) {
        long sum = 0;
        for(int i = 0; i < qSize; i++) {
            sum += queue1[i] + queue2[i];
        }
        return sum;
    }
}