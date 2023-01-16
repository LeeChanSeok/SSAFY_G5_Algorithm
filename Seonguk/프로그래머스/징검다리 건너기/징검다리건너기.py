def solution(stones, k):
    
    def check(stones, k, num):
        cnt=0
        for s in stones:
            # num명이 현재 징검다리를 못 건너는 경우
            if s < num:
                cnt+=1
            # 건널 수 있는 경우
            else:
                cnt=0
            # 만약 못 건너는 징검다리가 연속으로 k이상인 경우, 못건넘
            if cnt == k:
                return False
        return True
    
    answer=0
    left=1
    right=200000001
    
    while left<=right:
        mid=(left+right)//2
        
        # 건널 수 있다면 left 조정
        if check(stones, k, mid):
            left=mid+1
            answer=max(answer,mid)
        # 못 건넌다면 right 조정
        else:
            right=mid-1
            
    return answer
