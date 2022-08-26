#벽돌 깨기
from itertools import product
from collections import deque
import copy

def breakWall(x,y):
    q=deque()
    q.append((x,y,copyArr[x][y]))

    while q:
        cx, cy, dist=q.popleft()
        copyArr[cx][cy]=0
        # dist만큼 동서남북 탐색
        for d in range(1, dist):
            for i in range(4):
                nx,ny=cx+(d*dx[i]), cy+(d*dy[i])
                if 0<=nx<H and 0<=ny<W:
                    # 벽둘이 1이하이면 0으로 바꾸고 continue
                    if copyArr[nx][ny] <= 1:
                        copyArr[nx][ny] = 0
                        continue
                    # 벽돌이 1보다 크다면 q에 넣기
                    q.append((nx, ny, copyArr[nx][ny]))

    # 벽돌 밑으로 내리기
    for c in range(W):
        for r in range(H-2,-1,-1):  # H-2 인덱스부터 위로 탐색
            if copyArr[r][c] > 0 and copyArr[r+1][c] == 0:  # 한칸 이상 내려야할 경우
                for nr in range(r+1, H):    # 벽돌을 어디까지 내려야할지 탐색 후 내리기
                    if copyArr[nr][c] > 0:
                        copyArr[nr-1][c]=copyArr[r][c]
                        break
                if nr == H-1 and copyArr[nr][c] == 0:   # 마지막행이 0인 경우, 따로 처리
                    copyArr[nr][c] = copyArr[r][c]
                copyArr[r][c] = 0

TC=int(input())
for tc in range(1,TC+1):
    N, W, H=map(int, input().split())
    arr=[list(map(int, input().split())) for _ in range(H)]

    dx=[-1,0,1,0]
    dy=[0,1,0,-1]

    answer=float('inf')
    temp=[i for i in range(W)]
    # N개 만큼의 중복순열 생성
    for prod in product(temp, repeat=N):
        copyArr=copy.deepcopy(arr)
        for y in prod:
            # 가장 처음 만나는 벽돌 좌표찾기
            for x in range(H):
                if copyArr[x][y] > 0:
                    break

            breakWall(x,y)

        res=0
        for a in copyArr:
            res+=a.count(0)
        answer=min(answer, (H*W)-res)
        if answer == 0:
            break

    print("#{} {}".format(tc, answer))