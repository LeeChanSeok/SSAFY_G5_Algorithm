#탑보기
import sys
from collections import deque
input=sys.stdin.readline

def countBuilding(arr):
    stack=deque()
    for i in range(len(arr)):
        # 현재 건물의 오른쪽 or 왼쪽을 돌면서 현재 높이보다 오름차순으로 높은 건물 수 구하기
        while len(stack)>0 and stack[-1][1]<=arr[i]:
            stack.pop()
        cnt[i+1] += len(stack)

        # i번째 건물과 가장 가까운 건물중 가장 적은 건물 번호 구하기
        if len(stack) > 0:
            gap=abs(stack[-1][0] - (i+1))
            if gap < nearest[i+1][1]:
                nearest[i+1][0]=stack[-1][0]
                nearest[i+1][1]=gap
            elif gap == nearest[i+1][1] and stack[-1][0] < nearest[i+1][0]:
                nearest[i+1][0]=stack[-1][0]
        stack.append([i+1,arr[i]])

N=int(input())
arr=list(map(int, input().split()))
cnt=[0]*(N+1)
nearest=[[float('inf'),float('inf')] for _ in range(N+1)]
countBuilding(arr)
countBuilding(arr[::-1])

for i in range(1,N+1):
    if cnt[i]>0:
        print(str(cnt[i])+" "+str(nearest[i][0]))
    else:
        print(0)
