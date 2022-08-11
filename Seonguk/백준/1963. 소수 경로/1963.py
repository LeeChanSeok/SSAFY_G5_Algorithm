#수소 경로

#실행시간 : 328ms
#메모리 : 32452KB

import sys
from collections import deque
input=sys.stdin.readline

#소수인지 확인
def isPrime(num):
    for i in range(2,int(num**0.5)+1):
        if num%i ==0:
            return False
    return True

answer=[]
tc=int(input())
for t in range(tc):
    start, end=map(str, input().split())
    visited = [0] * 10000
    visited[int(start)]=1

    q=deque()
    q.append((start,0))

    res=-1
    while q:
        cur, dist=q.popleft()
        if cur == end:
            res=dist
            break
        for i in range(4):
            for j in range(10):
                ncur=int(cur[:i]+str(j)+cur[i+1:])  # i번째 자리 숫자를 j로 바꾸기
                if visited[ncur] == 0 and isPrime(ncur) and ncur >= 1000:
                    visited[ncur]=1
                    q.append((str(ncur),dist+1))

    answer.append(res)

for ans in answer:
    print(ans)
