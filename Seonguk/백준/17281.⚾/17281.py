#⚾
import sys
from itertools import permutations
input=sys.stdin.readline

N=int(input())
arr=[]
for _ in range(N):
    arr.append(list(map(int, input().split())))
permus=list(permutations([1,2,3,4,5,6,7,8], 8))    # 모든 타순 경우의 수 구하기
answer=-1
for permu in permus:
    permu=list(permu)
    permu.insert(3,0)    # 첫타자는 3번째 고정

    outCnt, inning=0, 0
    b1, b2, b3 = 0, 0, 0
    idx,score=0,0
    while(outCnt < N*3):    # 총 아웃수가 전체 이닝수*3까지 반복
        numRun=arr[inning][permu[idx]]    # 현재 선수의 n루타 기록
        if numRun == 0:
            outCnt+=1
            if outCnt%3 == 0:    # 한 이닝 끝났을 때
                inning+=1
                b1,b2,b3=0,0,0
        elif numRun == 1:
            score+=b3
            b3, b2, b1=b2, b1, 1
        elif numRun == 2:
            score+=(b3+b2)
            b3, b2, b1 = b1, 1, 0
        elif numRun == 3:
            score += (b3 + b2 + b1)
            b3, b2, b1=1,0,0
        elif numRun == 4:
            score+=(b3 + b2 + b1+1)
            b3, b2, b1=0,0,0
        idx=(idx+1)%9    # 다음 타자 설정
    answer=max(answer, score)
print(answer)