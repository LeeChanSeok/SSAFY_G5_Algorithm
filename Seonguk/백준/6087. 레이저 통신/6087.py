#레이저 통신
import sys
from collections import deque
input=sys.stdin.readline

M, N=map(int, input().split())
arr=[list(str(input().rstrip())) for _ in range(N)]
pos=[]
for x in range(N):
    for y in range(M):
        if arr[x][y] == 'C':
            pos.append((x,y))
            arr[x][y]='.'
#시작위치, 도착위치 저장
sx,sy=pos[0]
ex,ey=pos[1]
dx=[-1,0,1,0]
dy=[0,1,0,-1]
#초기에 모든 칸에 거울 갯수를 inf로 초기화
mirrorNum=[[float('inf')]*M for _ in range(N)]

#출발점의 동서남북을 살피고 탐색 가능한 위치 정보 저장
q=deque()
for i in range(4):
    nx, ny=sx+dx[i], sy+dy[i]
    if 0<=nx<N and 0<=ny<M and arr[nx][ny] == '.':
        q.append((nx,ny,i,0))   #x, y, 방향, 거울갯수

while q:
    x,y,dir,cnt=q.popleft()
    for i in range(4):
        nx, ny=x+dx[i], y+dy[i]
        ncnt=cnt+1 if dir != i else cnt #이전 방향과 탐색하려는 방향이 일치하는지 체크

        #재방문하는 경우, nx,ny의 거울 갯수보다 작거나 같은 경우에만 방문
        if 0<=nx<N and 0<=ny<M and arr[nx][ny] == '.' and mirrorNum[nx][ny] >= ncnt:
            mirrorNum[nx][ny]=ncnt
            q.append((nx,ny,i,ncnt))

print(mirrorNum[ex][ey])

# while q:
#     x,y,dir,cnt=q.popleft()
#     if (x, y) == end:
#         answer = min(answer, cnt)
#     for i in range(4):
#         nx, ny=x+d[i][0], y+d[i][1]
#         if 0<=nx<N and 0<=ny<M and arr[nx][ny] == '.':
#             curDir=d.index((nx-x, ny-y))
#             if visited[curDir][nx][ny] == 0:
#                 visited[curDir][nx][ny]=1
#                 if dir != curDir:
#                     q.append((nx, ny, curDir, cnt+1))
#                 else:
#                     q.append((nx, ny, dir, cnt))