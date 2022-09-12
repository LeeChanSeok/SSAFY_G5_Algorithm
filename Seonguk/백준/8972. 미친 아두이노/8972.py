#미친 아두이노
from collections import deque
import heapq

def bfs(q):
    qsz=len(q)
    delPos = set()
    nextQ=deque()
    for j in range(qsz):
        x, y = q.popleft()
        heap = []
        for i in range(1, 10):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < R and 0 <= ny < C:
                dist = abs(cx - nx) + abs(cy - ny)
                heapq.heappush(heap, (dist, nx, ny))
        fdist, fx, fy=heapq.heappop(heap)
        if arr[fx][fy] == 'I':
            return True

        arr[x][y] = '.'
        if (fx, fy) in nextQ:
            delPos.add((fx, fy))
        else:
            nextQ.append((fx,fy))

    for x, y in delPos:
        nextQ.remove((x,y))

    for nx,ny in nextQ:
        q.append((nx,ny))
        arr[nx][ny]='R'

    return False

R,C=map(int, input().split())
arr=[list(str(input().rstrip())) for _ in range(R)]
move=list(str(input()))
move=[int(i) for i in move]

dx=[0,1,1,1,0,0,0,-1,-1,-1]
dy=[0,-1,0,1,-1,0,1,-1,0,1]

cx, cy=0, 0
q=deque()
for x in range(R):
    for y in range(C):
        if arr[x][y] == 'I':
            cx,cy=x,y
        elif arr[x][y] == 'R':
            q.append((x,y))
answer=0
flag=False
for d in move:
    answer+=1
    arr[cx][cy] = '.'
    cx, cy=cx+dx[d], cy+dy[d]
    arr[cx][cy]='I'
    if bfs(q):
        print("kraj {}".format(answer))
        flag=True
        break

if not flag:
    for i in range(R):
        for j in range(C):
            print(arr[i][j], end=" ")
        print()
