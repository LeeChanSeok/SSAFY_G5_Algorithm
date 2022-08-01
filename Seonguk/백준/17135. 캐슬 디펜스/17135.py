# 캐슬 디펜스
import sys, copy
from itertools import combinations

input = sys.stdin.readline

N, M, D = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
comb = [i for i in range(M)]
# 적을 배치하는 모든 경우의 수 찾기(열값을 저장)
comb = list(combinations(comb, 3))
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def atack(team):
    global cnt
    del_list = set()
    for t in team:
        candi = []
        tx, ty = N, t
        for x in range(N):
            for y in range(M):
                # 적이 있고 공격할 수 있는 거리이면
                if copy_arr[x][y] == 1 and D >= abs(tx - x) + abs(ty - y):
                    # 공격할 적들의 좌표값, 거리를 candi에 저장
                    candi.append((x, y, abs(tx - x) + abs(ty - y)))
        # 그 중 우선순위가 가장 높은 적을 선택하여 del_list에 저장
        if candi:
            candi.sort(key=lambda x: (x[2], x[1]))
            del_list.add((candi[0][0], candi[0][1]))

    # del_list에 있는 적들을 arr에서 삭제
    for rx, ry in del_list:
        copy_arr[rx][ry] = 0
        cnt += 1
    # 적들이 한줄씩 밑으로 이동
    for x in range(N - 2, -1, -1):
        copy_arr[x + 1] = copy_arr[x]
    copy_arr[0] = [0] * M


# 모든 적들이 처리되었는지 검사
def check():
    for x in range(N):
        for y in range(M):
            if copy_arr[x][y] == 1:
                return False
    return True


answer = 0
for team in comb:
    cnt = 0
    copy_arr = copy.deepcopy(arr)
    while True:
        atack(team)
        answer = max(answer, cnt)
        if check():
            break

print(answer)