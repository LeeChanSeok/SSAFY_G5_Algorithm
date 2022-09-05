#스티커 붙이기
import sys
input=sys.stdin.readline

# 2차원 배열 복사
def copy_arr(arr):
    rota_arr=[a[:] for a in arr]
    return rota_arr

# 노트북 x,y에 스티커를 붙일 수 있는지 검사
def isAttatch(x,y,rota, rotaArr):
    for i in range(rota): # rota : 0 1 2 3 => 0 90 180 270 회전
        rotaArr=list(zip(*rotaArr[::-1]))

    for rx in range(Ri):
        for ry in range(Ci):
            if arr[rx+x][ry+y] + rotaArr[rx][ry] == 2: # 스티커 못붙이는 경우
                return False

    # 해당 위치에 스티커 붙이기
    for rx in range(Ri):
        for ry in range(Ci):
            arr[rx+x][ry+y]+=rotaArr[rx][ry]

    return True

N,M,K=map(int, input().split())
arr=[[0]*M for _ in range(N)]

for i in range(K):
    Ri, Ci=map(int, input().split())
    arri=[list(map(int, input().split())) for _ in range(Ri)]

    rotaArr = copy_arr(arri)
    att_flag=False
    for rota in range(4):
        if rota>=1: # 90도 이상일 때, 행과 열 서로 바꾸기
            Ri, Ci=Ci, Ri
        if N < Ri or M < Ci: # 회전한 배열의 행 or 열이 노트북 배열보다 크다면
            continue
        for x in range(N-Ri+1):
            for y in range(M-Ci+1):
                if isAttatch(x,y,rota, rotaArr):
                    att_flag=True
                    break
            if att_flag:
                break
        if att_flag:
            break

answer=0
for a in arr:
    answer+=sum(a)

print(answer)