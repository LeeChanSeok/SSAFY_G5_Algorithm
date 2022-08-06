#색종이 붙이기
import sys
input=sys.stdin.readline
sys.setrecursionlimit(10**8)

arr=[list(map(int, input().split())) for _ in range(10)]
numPaper=[5]*6

#flag == 0 이면 색종이 붙이고 flag == 1이면 색종이 제거
def attach(x,y,size,flag):
    for r in range(x,x+size):
        for c in range(y,y+size):
            arr[r][c]=flag

#arr[x][y]~arr[x+size][y+size]까지 색종이가 붙여지는지 확인
def isAttAble(x,y,size):
    for r in range(x,x+size):
        for c in range(y,y+size):
            if arr[r][c] == 0:
                return False
    return True

answer=float('inf')
def dfs(x,cnt):
    global answer
    nx, ny=-1,-1
    temp=0

    #arr에서 1인 위치 찾기
    for r in range(x, 10):
        for c in range(10):
            if arr[r][c] == 1:
                nx, ny = r, c
                temp=1
                break
        if temp == 1:
            break

    #더이상 1이 없으므로 answer에 최소값 저장하고 끝내기
    if nx == -1 and ny == -1:
        answer=min(answer, cnt)
        return

    #5,4,3,2,1순으로 붙일 수 있는 사이즈가 있는지 확인
    for sz in range(5,0,-1):
        if nx+sz > 10 or ny+sz > 10 or numPaper[sz] == 0:
            continue
        #해당 sz 색종이 붙이고 dfs로 탐색
        if isAttAble(nx,ny,sz):
            numPaper[sz] -= 1
            attach(nx,ny,sz,0)

            dfs(nx, cnt+1)

            # 모든 sz를 완전탐색해야하므로 백트래킹
            numPaper[sz] += 1
            attach(nx, ny, sz, 1)

dfs(0,0)
if answer == float('inf'):
    print(-1)
else:
    print(answer)