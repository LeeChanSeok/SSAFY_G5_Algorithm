#치즈
import sys
from collections import deque
input=sys.stdin.readline
sys.setrecursionlimit(10**8)

# 외부공기를 -1로 초기화
def dfs(x,y):
    arr[x][y] = -1
    for i in range(4):
        nx, ny=x+dx[i], y+dy[i]
        if 0<=nx<N and 0<=ny<M and arr[nx][ny] == 0:
            dfs(nx,ny)

# 치즈가 남았는지 체크
def check():
    for x in range(N):
        for y in range(M):
            if arr[x][y] == 1:
                return True
    return False

N,M=map(int, input().split())
arr=[list(map(int, input().split())) for _ in range(N)]

dx=[0,1,0,-1]
dy=[1,0,-1,0]

dfs(0,0) # 외부공기 처리
answer=0
while check():
    melt=deque() # 녹은 치즈 좌표 저장
    for x in range(N):
        for y in range(M):
            if arr[x][y] == 1:
                cnt = 0
                for i in range(4):
                    nx, ny=x+dx[i], y+dy[i]
                    if 0<=nx<N and 0<=ny<M and arr[nx][ny] == -1:
                        cnt+=1
                # 외부 공기에 2면이상 맞닿아있으면 melt에 저장
                if cnt>=2:
                    melt.append((x,y))
    #녹은 치즈를 -1로 초기화하고 상하좌우에 내부공기가 있다면 외부공기로 변환
    for x,y in melt:
        dfs(x,y)
    answer+=1

print(answer)
