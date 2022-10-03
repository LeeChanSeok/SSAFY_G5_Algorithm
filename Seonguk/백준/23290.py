#마법사 상어와 복제
import sys
from collections import deque
from itertools import product
import copy
input=sys.stdin.readline

M,S=map(int, input().split())
arr=[[deque()*4 for _ in range(4)] for _ in range(4)]
for i in range(M):
    x,y,d=map(int, input().split())
    arr[x-1][y-1].append(d-1)
sx,sy=map(int, input().split())
sx, sy=sx-1, sy-1

#냄새 배열
smells=[[0]*4 for _ in range(4)]
# 물고기 이동방향
dx=[0,-1,-1,-1,0,1,1,1]
dy=[-1,-1,0,1,1,1,0,-1]

#상어 이동방향
sdx=[-1,0,1,0]
sdy=[0,-1,0,1]
#상어 이동을 위한 모든 경우의 수
combs=list(product([0,1,2,3], repeat=3))
for s in range(S):
    copy_arr=copy.deepcopy(arr)

    # 1.물고기 이동
    # move에 물고기의 다음 이동칸 넣기
    move=deque()
    for x in range(4):
        for y in range(4):
            for i in range(len(arr[x][y])):
                dir=arr[x][y].popleft()
                canMove=False
                for d in range(dir, dir-8, -1):
                    d=d%8
                    nx, ny=x+dx[d], y+dy[d]
                    if 0<=nx<4 and 0<=ny<4 and (nx != sx or ny != sy) and smells[nx][ny] == 0:
                        move.append((nx,ny,d))
                        canMove=True
                        break
                if not canMove:
                    arr[x][y].append(dir)
    # move에 있는 위치를 arr에 반영
    while move:
        x,y,dir=move.popleft()
        arr[x][y].append(dir)

    # 2.상어 이동
    # dirCandi라는 배열에 [연속이동방향, 먹은 물고기 수] 넣기
    dirCandi=[]
    for comb in combs:
        fishNum=0
        canMove=True
        nx, ny = sx, sy
        visited=[]
        for dir in comb:
            nx, ny=nx+sdx[dir], ny+sdy[dir]
            if nx < 0 or nx >= 4 or ny < 0 or ny >= 4:
                canMove=False
                break
            # (0,1,0)같은 경우, 중간칸에 물고기가 존재하는 경우, 두번 카운트되기때문에 따로 처리
            if (nx,ny) not in visited:
                fishNum+=len(arr[nx][ny])
            visited.append((nx, ny))
        if canMove:
            temp=["".join(map(str, comb)), fishNum]
            dirCandi.append(temp)

    # 먹은 물고기 수, 이동방향 사전순으로 정렬
    dirCandi.sort(key=lambda x:(-x[1], x[0]))

    # 우선순위가 높은 이동뱡향 꺼내서 상어 이동
    dir, num=dirCandi[0]
    for d in dir:
        sx, sy=sx+sdx[int(d)], sy+sdy[int(d)]
        if arr[sx][sy]:
            smells[sx][sy] = 3
        arr[sx][sy]=deque()

    # 3.냄새 처리
    for x in range(4):
        for y in range(4):
            if smells[x][y] > 0:
                smells[x][y]-=1

    # 4.물고기 복제
    for x in range(4):
        for y in range(4):
            for dir in copy_arr[x][y]:
                arr[x][y].append(dir)

answer = 0
for x in range(4):
    for y in range(4):
        answer+=len(arr[x][y])

print(answer)
