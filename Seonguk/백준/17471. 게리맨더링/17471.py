#개리맨더링
import sys
from itertools import combinations
from collections import deque
input=sys.stdin.readline

def bfs(distr):
    q=deque()
    q.append(distr[0])
    visited = [0] * (N + 1)
    visited[distr[0]]=1

    while q:
        cur=q.popleft()
        if cur in dic:  # 인접한 노드가 아예 없는 경우 따로 처리해줌
            for next in dic[cur]:
                # 인접한 노드 중 distr에 포함된 노드만 탑색
                if visited[next] == 0 and next in distr:
                    visited[next]=1
                    q.append(next)

    for n in distr:
        if visited[n] == 0: #distr내에서 하나라도 방문되지 않은 노드가 존재한다면
            return False
    return True

def getDiff(a,b):
    sumA, sumB=0,0
    for i in a:
        sumA+=pplNum[i]
    for i in b:
        sumB+=pplNum[i]

    return abs(sumA-sumB)

N=int(input())
pplNum=[0]+list(map(int, input().split()))
dic={}
# 연결 구역 정보를 딕셔너리에 저장
answer=float('inf')
for i in range(1,N+1):
    arr=list(map(int, input().split()))
    for j in range(arr[0]):
        if i in dic.keys():
            dic[i].append(arr[j+1])
        else:
            dic[i]=[arr[j+1]]

arr=[i for i in range(1,N+1)]
# combination을 사용하여 구역을 a,b 선거구로 나누는 모든 경우의 수 구하기
for pick in range(N-1,N//2-1,-1):
    combs=combinations(arr, pick)
    for a in combs:
        b=[i for i in range(1,N+1) if i not in a]
        if bfs(a) and bfs(b):   # a, b 선거구가 각각 인접해있다면
            answer=min(answer, getDiff(a,b))


if answer == float('inf'):  # 두대의 선거구로 나누지 못하는 경우
    print(-1)
else:
    print(answer)
