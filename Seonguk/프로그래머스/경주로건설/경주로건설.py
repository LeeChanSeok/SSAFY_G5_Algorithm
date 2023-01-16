from collections import deque

def solution(board):
    
    def bfs(x,y,c,d):
        N=len(board)
        result=[[float('inf')]*N for i in range(N)]
        result[0][0]=0
        dx=[0,1,0,-1]
        dy=[1,0,-1,0]
        q=deque()
        q.append((x,y,c,d)) # x,y,현재비용,방향
        while q:
            x, y, cost, d=q.popleft()

            for i in range(4):
                nx=x+dx[i]
                ny=y+dy[i]
                # 방향이 같은 경우, 다른 경우에 따라 cost 더하기
                ncost=cost+600 if d != i else cost+100
                # result보다 cost가 적으면 방문
                if 0<=nx<N and 0<=ny<N and board[nx][ny] == 0 and result[nx][ny] > ncost:
                    result[nx][ny]=ncost
                    q.append((nx,ny,ncost,i))
        return result[-1][-1]
            
    return min(bfs(0,0,0,0), bfs(0,0,0,1))
