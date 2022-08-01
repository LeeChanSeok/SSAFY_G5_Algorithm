#파이프 옮기기1

import sys
input=sys.stdin.readline
sys.setrecursionlimit(10**8)

N=int(input())
arr=[list(map(int, input().split())) for i in range(N)]
#대각선일 경우 탐색할 위치
diagDir=[(0,1), (1,0), (1,1)]

#대각선으로 놓을 수 있는지 확인
def check_diag(x,y):
    for dx, dy in diagDir:
        nx, ny = x + dx, y + dy
        if nx >= N or ny >= N or arr[nx][ny] == 1:
            return False
    return True

answer=0
def dfs(cur_dir, x, y):
    global answer
    if x == N-1 and y == N-1:
        answer+=1
        return
    #대각 or 가로인 경우, 둘다 가로로 이동 가능
    if cur_dir == "horizon" or cur_dir == "diagonal":
        if y+1 < N and arr[x][y+1] == 0:
            dfs("horizon", x,y+1)
    #대각 or 세로인 경우, 둘다 세로로 이동 가능
    if cur_dir == "vertical" or cur_dir == "diagonal":
        if x+1 < N and arr[x+1][y] == 0:
            dfs("vertical", x+1, y)
    #대각 or 가로 or 세로인 경우, 모두 대각으로 이동 가능
    if check_diag(x, y):
        dfs("diagonal", x + 1, y + 1)

dfs("horizon",0,1)
print(answer)