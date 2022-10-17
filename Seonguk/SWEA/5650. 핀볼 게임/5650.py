# 핀볼 게임

def pinballMove(x,y,dir):
    cx, cy, curDir=x,y,dir
    score=0
    while True:
        cx, cy = cx + dx[curDir], cy + dy[curDir]
        # 범위 벗어나는 경우 
        if cx < 0 or cx >= N or cy < 0 or cy >= N:
            curDir=blockDir[5][curDir]
            score+=1
            continue
        
        # 블랙홀 만나면 종료
        if arr[cx][cy] == -1 or (cx == x and cy == y):
           break
        
        # 블록 만나면
        if 1<=arr[cx][cy]<=5:
            curDir=blockDir[arr[cx][cy]][curDir]
            score+=1
        
        # 웜홀 만나면
        elif 6<=arr[cx][cy]<=10:
            if wormhole[arr[cx][cy]][0] == (cx,cy):
                cx,cy=wormhole[arr[cx][cy]][1]
            else:
                cx, cy = wormhole[arr[cx][cy]][0]
            continue

    return score

dx=[-1,0,1,0]
dy=[0,1,0,-1]
# 각 블록마다 핀볼이 오는 방향에 따라 반사되는 방향 저장 
blockDir={1:(2,3,1,0), 2:(1,3,0,2), 3:(3,2,0,1), 4:(2,0,3,1), 5:(2,3,0,1)}
TC=int(input())
for tc in range(1, TC+1):
    N=int(input())
    arr=[list(map(int, input().split())) for _ in range(N)]
    
    #원홈 따로 저장
    wormhole={}
    for x in range(N):
        for y in range(N):
            if 6<=arr[x][y]<=10:
                if arr[x][y] in wormhole:
                    wormhole[arr[x][y]].append((x,y))
                else:
                    wormhole[arr[x][y]]=[(x,y)]

    # 모든 위치에서 모든 방향 탐색
    answer=0
    for x in range(N):
        for y in range(N):
            if arr[x][y] == 0:
                for dir in range(4):
                    answer=max(answer, pinballMove(x,y,dir))

    print('#{} {}'.format(tc, answer))
