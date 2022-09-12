#소가 길을 건너간 이유 6
import sys
from collections import deque
input=sys.stdin.readline

def bfs(cx,cy, candi):
    global answer
    visited=[[False]*N for _ in range(N)]
    visited[cx][cy]=True
    arr[cx][cy]=0

    q=deque()
    q.append((cx,cy))
    while q:
        x,y=q.popleft()
        if arr[x][y] == 1:  # 다리를 건너서 다른 소를 만났다면 후보에서 제외
            candi-=1
        for i in range(4):
            nx, ny=x+dx[i], y+dy[i]
            # 현재칸(x,y)와 방문칸(nx,ny)이 다리로 이어져있다면
            if (x,y) in bridges and (nx,ny) in bridges[(x,y)]:
                continue
            if 0<=nx<N and 0<=ny<N and not visited[nx][ny]:
                visited[nx][ny]=True
                q.append((nx,ny))

    answer+=candi

N, K, R=map(int, input().split())
arr=[[0]*N for _ in range(N)]
dx=[0,1,-1,0]
dy=[1,0,0,-1]

bridges={}
for _ in range(R):
    r1,c1,r2,c2=map(int, input().split())
    r1, c1, r2, c2=r1-1, c1-1, r2-1, c2-1
    # dictionary로 다리1, 다리2 연결 표현
    if (r1,c1) in bridges:
        bridges[(r1,c1)].append((r2,c2))
    else:
        bridges[(r1,c1)]=[(r2,c2)]
    if (r2,c2) in bridges:
        bridges[(r2,c2)].append((r1,c1))
    else:
        bridges[(r2,c2)]=[(r1,c1)]

cows=[]
for _ in range(K):
    r,c=map(int, input().split())
    r, c=r-1, c-1
    arr[r][c]=1 #소 1로 표시
    cows.append((r,c))

answer=0
candiPair=K
for x,y in cows:
    candiPair-=1    # 현재 소(x,y)와 쌍을 이룰 수 있는 후보 소들의 수
    if candiPair > 0:
        bfs(x,y,candiPair)

print(answer)
