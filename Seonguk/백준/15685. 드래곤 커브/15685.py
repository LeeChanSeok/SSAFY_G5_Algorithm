# 드래곤 커브

N = int(input())
arr = []
for i in range(N):
    arr.append(list(map(int, input().split())))
dx = [0, -1, 0, 1]
dy = [1, 0, -1, 0]

def make_dragon(dir, gen):
    dragon_pos.append(dir) # 시작 방향 넣기
    for i in range(gen):
        rev = reversed(dragon_pos) 
        for j in rev: # 현재 리스트에 들어간 방향을 거꾸로 돌면서
            dragon_pos.append((j + 1) % 4) # 현재 방향의 반시계 방향을 리스트에 추가

mark = [[False] * 101 for i in range(101)]
for x, y, d, g in arr:
    dragon_pos = []
    make_dragon(d, g)
    nx, ny = y, x
    mark[nx][ny] = True
    for j in dragon_pos:
        nx = nx + dx[j]
        ny = ny + dy[j]
        mark[nx][ny] = True

answer = 0
for i in range(100):
    for j in range(100):
        # 1x1 정사각형 세기
        if mark[i][j] and mark[i + 1][j] and mark[i][j + 1] and mark[i + 1][j + 1]:
            answer += 1
print(answer)
