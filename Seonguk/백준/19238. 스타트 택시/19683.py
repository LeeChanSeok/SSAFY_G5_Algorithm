#스타트 택시
import sys
from collections import deque
input=sys.stdin.readline

N,M,F=map(int, input().split())
arr=[list(map(int, input().split())) for _ in range(N)]
sx, sy=map(int, input().split())
sx, sy=sx-1, sy-1
dx=[0,1,0,-1]
dy=[1,0,-1,0]

dest={}
# 출발지는 arr에 id로 표시, 도착지는 dest라는 딕셔너리에 저장
for i in range(2,M+2):
    x,y,ex,ey=map(int, input().split())
    arr[x-1][y-1]=i
    dest[(x-1,y-1)]=(ex-1,ey-1)

for _ in range(M):
    q=deque()
    q.append((sx,sy,0))
    visited=[[False]*N for _ in range(N)]
    candi=[]    # 우선순위를 정하기 위한 배열
    id=0
    while q:
        isArrive=False
        for j in range(len(q)):
            x,y,dist=q.popleft()
            if arr[x][y] > 1:
                isArrive=True
                candi.append((dist,x,y,arr[x][y])) # 거리, x, y, id 정보를 candi에 저장
            for d in range(4):
                nx, ny=x+dx[d], y+dy[d]
                if 0<=nx<N and 0<=ny<N and not visited[nx][ny] and arr[x][y] != 1:
                    visited[nx][ny]=True
                    q.append((nx,ny,dist+1))
        # 최소거리의 승객들에게 도착했다면
        if isArrive:
            candi.sort(key=lambda x:(-x[0], x[1], x[2]))
            use,sx,sy,id=candi[0] # 우선순위가 가장 높은 승객을 빼내기
            arr[sx][sy]=0
            F-=use
            break
    # 연료가 없거나 승객들에게 도달하지 못한다면
    if F <= 0 or not isArrive:
        F=-1
        break

    q = deque()
    q.append((sx, sy, 0))
    ex, ey=dest[(sx,sy)] # 각 승객의 목적지 꺼내오기
    visited = [[False] * N for _ in range(N)]
    isArrive=False
    while q:
        x,y,dist=q.popleft()
        # 목적지에 도달했다면
        if x == ex and y == ey:
            if F-dist < 0:
                break
            isArrive=True
            sx,sy=x,y
            F+=dist
            break
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and arr[x][y] != 1:
                visited[nx][ny] = True
                q.append((nx, ny, dist + 1))

    if not isArrive:
        F=-1
        break

print(F)
