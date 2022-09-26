#성곽
import sys
input=sys.stdin.readline

# 방 개수와 방의 최대 넓이 구하는 함수
def getRoomNum(x,y,roomNum):
    global cnt
    checked[x][y]=roomNum   # 연결된 방에 id 표시
    cnt+=1
    for i in range(4):
        isWall=arr[x][y] & (1 << i) # 현재 방향이 벽인지 확인
        if isWall == 0:
            nx, ny=x+dx[i], y+dy[i]
            if checked[nx][ny] == 0: # 한번도 방문된적 없다면
                getRoomNum(nx,ny,roomNum)


M, N = map(int, input().split())
arr=[list(map(int, input().split())) for _ in range(N)]
checked=[[0]*M for _ in range(N)]
dx=[0,-1,0,1]
dy=[-1,0,1,0]
eachRoomNum={}

roomNum=1
maxRoomArea=0
cnt=0
for x in range(N):
    for y in range(M):
        if checked[x][y] == 0:
            cnt=0
            getRoomNum(x,y,roomNum)
            roomNum+=1
        eachRoomNum[roomNum-1]=cnt  # 각 방의 id에 방 개수 저장
        maxRoomArea=max(maxRoomArea, cnt)

# 두개의 방 사이에 벽이 존재하는지 확인하고 unityCheck에 넣기
unityCheck=set()
for x in range(N):
    for y in range(M):
        for i in range(4):
            nx, ny=x+dx[i], y+dy[i]
            if 0<=nx<N and 0<=ny<M and checked[x][y] != checked[nx][ny]:
                # 작은 id의 방을 먼저 넣기
                if checked[x][y] < checked[nx][ny]:
                    unityCheck.add((checked[x][y], checked[nx][ny]))
                else:
                    unityCheck.add((checked[nx][ny], checked[x][y]))

# 두개의 방 사이에 존재하는 벽을 없애서 가장 넣은 방의 크기 구하기
wallRoomNum=0
for r1, r2 in unityCheck:
    wallRoomNum=max(wallRoomNum, eachRoomNum[r1]+eachRoomNum[r2])

print(roomNum-1)
print(maxRoomArea)
print(wallRoomNum)
