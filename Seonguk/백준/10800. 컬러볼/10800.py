# 컬러볼

import sys
from collections import defaultdict
input=sys.stdin.readline

N=int(input())
arr=[]
for i in range(N):
    C, S=map(int, input().split())
    arr.append((i,C,S))

#크기순으로 정렬
arr.sort(key=lambda x:x[2])
answer={}
colorSize=defaultdict(int) # 색깔별 공의 크기 합

total=0 # 현재까지의 총합
j=0
for i in range(N):
    while arr[j][2] < arr[i][2]:
        total+=arr[j][2]
        colorSize[arr[j][1]] += arr[j][2]
        j+=1
    # 현재 인덱스까지 공 무게 총합 - 현재 색깔 공 무게 누적합
    answer[arr[i][0]]=total-colorSize[arr[i][1]]

for i in range(N):
    print(answer[i])
