#등산로 조성
from collections import deque

def dfs(x,y,cnt,k,isCut):
    global answer

    answer=max(answer, cnt)

    for i in range(4):
        nx, ny=x+dx[i], y+dy[i]
        if 0<=nx<N and 0<=ny<N and not visited[nx][ny]:
            if arr[nx][ny] < arr[x][y]: # 다음 위치가 현재 높이보다 낮은 경우
                visited[nx][ny] = True
                dfs(nx,ny,cnt+1,k,isCut)
                visited[nx][ny] = False
            else:
                # 깍을 수 있고 깍았을때 현재 높이보다 더 낮은 경우
                if isCut == 0 and arr[nx][ny]-k < arr[x][y]:
                    arr[nx][ny] -= k
                    visited[nx][ny] = True
                    dfs(nx,ny,cnt+1,k,1)
                    visited[nx][ny] = False
                    arr[nx][ny] += k

dx=[0,-1,0,1]
dy=[1,0,-1,0]
TC=int(input())

for tc in range(1,TC+1):
    N, K=map(int, input().split())
    arr=[list(map(int, input().split())) for _ in range(N)]

    maxHeight=0
    for row in arr:
        temp=max(row)
        maxHeight=max(maxHeight, temp)

    startPos=[]
    for x in range(N):
        for y in range(N):
            if arr[x][y] == maxHeight:
                startPos.append((x,y))

    answer = 1
    # 1 ~ K까지 다 깍아보기
    for k in range(1,K+1):
        for sx,sy in startPos:
            visited = [[False] * N for _ in range(N)]
            visited[sx][sy]=True
            dfs(sx,sy,1,k,0)

    print("#{} {}".format(tc, answer))