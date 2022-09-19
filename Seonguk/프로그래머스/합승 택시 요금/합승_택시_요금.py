from collections import deque
import heapq

res=float('inf')
total=0

def shortest_path(G, s, dist):
    heap=[]
    heapq.heappush(heap, [0,s])

    while heap:
        cur_dist, cur_node=heapq.heappop(heap)
        for new_dist, new_node in G[cur_node]:
            sum_dist=cur_dist+new_dist
            if sum_dist < dist[s][new_node]:
                dist[s][new_node]=sum_dist
                heapq.heappush(heap, [sum_dist, new_node])

def solution(n, s, a, b, fares):
    global res
    G={}
    dist=[[float('inf')]*(n+1) for i in range(n+1)]
    aTomid=[0]*(n+1)
    bTomid=[0]*(n+1)

    for i in range(1,n+1):
        G[i]=[]
    for u,v,w in fares:
        G[u].append([w,v])
        G[v].append([w,u])
    
    # 다익스트라로 모든 정점간 최소거리 구하기
    for i in range(1, n+1):
        dist[i][i]=0
        shortest_path(G, i, dist)
    
    #s->a도착지점 + s->b도착지점
    answer=dist[s][a]+dist[s][b]
    temp=float('inf')
    
    #모든 중간지점을 돌면서 최솟값 갱신
    for i in range(1, n+1):
        if dist[s][i] == float('inf') or dist[i][a] == float('inf') or dist[i][b] == float('inf'):
            continue
        total_dist=dist[s][i]+dist[i][a]+dist[i][b]
        temp=min(total_dist, temp)
        
    return temp
