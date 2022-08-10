#움직이는 미로 탈출
from collections import deque

def moveWall():
    for x in range(7,-1,-1):
        for y in range(8):
            if arr[x][y] == '#':
                if x== 7:   #벽이 가장 아래에 있다면
                    arr[x][y]='.'
                else:       #한칸 밑으로 내려주고 원래 위치는 빈칸으로 초기화
                    arr[x][y]='.'
                    arr[x+1][y]='#'

def bfs():
    q=deque()
    q.append((7,0))
    visited = [[0] * 8 for _ in range(8)]
    while q:
        #현재 큐에 들어있는 칸들만 탐색하기(1초에 한칸씩 이동)
        for i in range(len(q)):
            x,y=q.popleft()
            if arr[x][y] == '#':
                continue
            elif x == 0 and y == 7:
                return 1
            for i in range(len(dx)):
                nx, ny=x+dx[i], y+dy[i]
                if 0<=nx<8 and 0<=ny<8 and arr[nx][ny] == '.':
                    if visited[nx][ny] == 0:
                        visited[nx][ny]=1
                        q.append((nx,ny))
        #1초가 지날때마다 벽 이동
        moveWall()
        #1초가 지날때마다 방문 배열 초기화
        visited = [[0] * 8 for _ in range(8)]

    return 0

arr=[list(str(input().rstrip())) for _ in range(8)]
dx=[0,-1,-1,0,1,1,1,0,-1]   #9가지 방향 정의
dy=[0,0,1,1,1,0,-1,-1,-1]

print(bfs())