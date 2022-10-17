# 보호 필름

def check():
    # 각 열이 k개 이상 연속되는지 검사(투포인터)
    for y in range(W):
        up, down=0,0
        checked=False
        while up <= down and down < D:
            # up과 down이 같을때 down 한칸 내리기
            if arr2[up][y] == arr2[down][y]:
                if down == D-1 and down-up+1 >= K:
                    checked=True
                    break
                down+=1
            # up과 down이 다르면 up은 down 위치에서 다시 탐색
            else:
                if down-up >= K:
                    checked=True
                    break
                up=down
        if not checked:
            return False

    return True

def dfs(row, cnt):
    global answer
    if cnt >= answer:
        return
    if row == D:
        if check():
            answer=min(answer, cnt)
            return
    #모든 경우의 수 다 탐색
    else:
        dfs(row+1, cnt)
        for col in range(W):
            arr2[row][col]=0 # 필름 상태 바꿔주고
        dfs(row+1, cnt+1)
        for col in range(W):
            arr2[row][col]=1
        dfs(row+1, cnt+1)
        
        # 원래대로 되돌리기(백트래킹)
        for col in range(W):
            arr2[row][col]=arr[row][col]

TC=int(input())
for tc in range(1,TC+1):
    D,W,K=map(int, input().split())
    arr=[list(map(int, input().split())) for _ in range(D)]
    arr2=[i[:] for i in arr]
    answer=30
    dfs(0,0)

    print('#{} {}'.format(tc, answer))
