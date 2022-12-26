from collections import deque

def solution(n, edge):
    answer = 0    
    cnt=0
    adjMat=[[] for _ in range(n+1)]
    visited=[0 for _ in range(n+1)]
    dist=[0 for _ in range(n+1)]
    q=deque()
    
    # 연결된 노드정보를 인접행렬에 저장
    for [e1, e2] in edge:
        adjMat[e1].append(e2)
        adjMat[e2].append(e1)
        
    visited[1]=1
    q.append(1)
    
    while q:
        temp=q.popleft()
        for i in adjMat[temp]:
            if visited[i] == 0:
                q.append(i)
                visited[i]=1
                # 1번 노드부터 시작하여 1 2 ~ n칸 떨어진 노드를 방문하면서 dist에 거리 저장
                dist[i]=dist[temp]+1
                
    max_dist=max(dist)    
    answer=dist.count(max_dist)
    
    return answer
