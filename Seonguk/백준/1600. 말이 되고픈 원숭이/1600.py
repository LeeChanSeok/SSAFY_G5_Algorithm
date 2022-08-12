#말이 되고픈 원숭이
#pypy3
#실행시간 : 600ms
#메모리 : 148272KB

import sys
from collections import deque
input=sys.stdin.readline

K=int(input())
M,N=map(int, input().split())
arr=[list(map(int, input().split())) for _ in range(N)]

visited=[[[0]*M for _ in range(N)] for k in range(K+1)] # 나이트 이동 횟수만큼 3차원 리스트 선언
dx=[-1,0,1,0,-2,-1,1,2,2,1,-1,-2]
dy=[0,1,0,-1,1,2,2,1,-1,-2,-2,-1]

q=deque()
q.append((0,0,0,0))
visited[0][0][0]=1
answer=-1

while q:
    x,y,dist,k=q.popleft()
    if x == N-1 and y == M-1:
        answer=dist
        break
    if k<K: # 나이트 이동 횟수가 남았다면
        for i in range(12):
            nx, ny=x+dx[i], y+dy[i]
            if 3<i<12:  # 나이트 이동
                if 0 <= nx < N and 0 <= ny < M and arr[nx][ny] == 0 and visited[k+1][nx][ny] == 0:
                    visited[k+1][nx][ny] = 1
                    q.append((nx,ny,dist+1,k+1))
            else:   # 상하좌우 이동
                if 0 <= nx < N and 0 <= ny < M and arr[nx][ny] == 0 and visited[k][nx][ny] == 0:
                    visited[k][nx][ny] = 1
                    q.append((nx, ny, dist + 1, k))
    else:   # 더이상 나이트 이동 횟수가 남아있지 않다면
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < N and 0 <= ny < M and arr[nx][ny] == 0 and visited[k][nx][ny] == 0:
                visited[k][nx][ny] = 1
                q.append((nx, ny, dist + 1,k))

print(answer)