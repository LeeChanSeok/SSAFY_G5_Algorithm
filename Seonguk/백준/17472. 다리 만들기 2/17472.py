#다리 만들기2
import sys
from collections import deque
input=sys.stdin.readline

N,M=map(int, input().split())
arr=[]
arr=[list(map(int, input().split())) for _ in range(N)]

#섬인 경우 -1로 표시
for x in range(N):
    for y in range(M):
        if arr[x][y]==1:
            arr[x][y]=-1

dx=[0,1,0,-1]
dy=[1,0,-1,0]

# 섬에 번호붙이기
def Labeling(x,y,num):
    visited=[[0]*M for _ in range(N)]
    q=deque()
    q.append((x,y))
    while q:
        cx,cy=q.popleft()
        arr[cx][cy]=num
        for i in range(4):
            nx,ny=cx+dx[i],cy+dy[i]
            if 0<=nx<N and 0<=ny<M and arr[nx][ny] == -1:
                if visited[nx][ny] == 0:
                    visited[nx][ny]=1
                    q.append((nx,ny))

#상하좌우 섬을 이어주는 다리를 모두 구하여 edge에 저장
edge=set()
def makeBridge(x, y, cur):
    q=deque()
    for i in range(4):
        q.append((x,y,0,(dx[i],dy[i]))) # 모든 방향 큐에 넣고 bfs 시작
    while q:
        cx,cy,dist,dir=q.popleft()
        if arr[cx][cy] > 0 and arr[cx][cy] != cur:
            if dist > 2:
                edge.add((dist-1, cur, arr[cx][cy]))    #(다리길이, 섬1, 섬2)를 저장
            continue
        nx,ny=cx+dir[0], cy+dir[1]  # 같은 방향으로 다음칸 탐색
        if 0<=nx<N and 0<=ny<M and arr[nx][ny] != cur:
            q.append((nx,ny,dist+1,dir))

#크루스칼 MST 알고리즘
def find(parent, x):
    if x != parent[x]:
        parent[x]=find(parent,parent[x])
    return parent[x]

def union(parent, a, b):
    a=find(parent,a)
    b=find(parent,b)
    if a > b:
        parent[b]=parent[a]
    else:
        parent[a]=parent[b]

num=1
for x in range(N):
    for y in range(M):
        if arr[x][y] == -1:
            Labeling(x,y,num)
            num+=1

for x in range(N):
    for y in range(M):
        if arr[x][y] > 0:
            makeBridge(x,y,arr[x][y])

edge=list(edge)
edge.sort()

parent=[i for i in range(num)]
edgeCnt=0
answer=0
for dist, a, b in edge:
    if find(parent,a) != find(parent,b):
        edgeCnt+=1
        union(parent,a,b)
        answer+=dist

# 간선수 != node-1이면 모든 섬이 연결되지 않았음
if answer == 0 or edgeCnt != num-2:
    print(-1)
else:
    print(answer)
