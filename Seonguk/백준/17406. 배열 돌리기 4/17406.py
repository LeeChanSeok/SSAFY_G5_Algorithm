#배열 돌리기 4

import sys
from itertools import permutations
import copy
input=sys.stdin.readline

N,M,K=map(int, input().split())
arr=[list(map(int, input().split())) for _ in range(N)]
rotaInfo=[list(map(int, input().split())) for _ in range(K)]
permus=list(permutations(rotaInfo, K))  #회전 연산 정보들의 모든 순열구하기
rotaDir=[(1,0),(0,1),(-1,0),(0,-1)]

def rotate(r,c,s,arr):
    #반시계 방향으로 회전하면서 만나는 꼭지점 순서대로 저장
    boundary=[(r+s,c-s),(r+s,c+s),(r-s,c+s),(r-s,c-s)]
    x,y,i=r-s,c-s,0

    #(x,y):현재칸, (nx,ny):다음칸
    #arr[x][y]=arr[nx][ny]를 하면서 반시계 방향으로 배열 탐색
    for _ in range(s):
        first=arr[x][y] #회전하기전 시작점 값을
        while True:
            nx=x+rotaDir[i][0]
            ny=y+rotaDir[i][1]
            arr[x][y] = arr[nx][ny]
            x,y=nx,ny

            if (nx,ny) == boundary[i]:  #경계지점에 도달하면 다음 방향과 경계점으로 변경
                i+=1

            if (nx,ny) == boundary[-1]: #한바퀴 다 돌면 둘레를 줄여서 다시 돌기
                arr[boundary[-1][0]][boundary[-1][1]+1]=first #끝으로 바꿔줘야함
                boundary[0] = (boundary[0][0] - 1, boundary[0][1] + 1)
                boundary[1] = (boundary[1][0] - 1, boundary[1][1] - 1)
                boundary[2] = (boundary[2][0] + 1, boundary[2][1] - 1)
                boundary[3] = (boundary[3][0] + 1, boundary[3][1] + 1)
                x,y = nx+1, ny+1
                i=0
                break

def getSum(copyArr):
    res=float('inf')
    for row in copyArr:
        res=min(res,sum(row))
    return res

answer=float('inf')
for permu in permus:
    copyArr=copy.deepcopy(arr)
    for r,c,s in permu:
        rotate(r-1,c-1,s,copyArr)
    answer=min(answer,getSum(copyArr))

print(answer)