from collections import deque

def solution(queue1, queue2):
    answer = 0
    queue1=deque(queue1)
    queue2=deque(queue2)
    
    sum1, sum2=sum(queue1), sum(queue2)
    sz=len(queue1)
    
    while True:
        # 아래 과정을 큐사이즈*2+2만큼 반복
        if len(queue1) == 0 or len(queue2) == 0 or answer > sz*2+2:
            answer=-1
            break
        # 두 큐의 각 합을 비교하여 작은 큐에 값 넣기
        if sum1 > sum2: 
            temp=queue1.popleft()
            queue2.append(temp)
            sum1, sum2=sum1-temp, sum2+temp
            answer+=1;
        elif sum1 < sum2:
            temp=queue2.popleft()
            queue1.append(temp)
            sum1, sum2=sum1+temp, sum2-temp
            answer+=1;
        # 같으면 종료
        else:
            break
            
    return answer
