#우주 탐사선

def check():
    for v in visited:
        if not v:
            return False
    return True

# 최단거리 배열로 K부터 끝노드까지 방문하면서 가장 작은 total 구하기
def dfs(cur, total):
    global answer
    if check(): # 모든 노드를 방문했다면
        answer=min(answer, total)
        return

    for node in range(N):
        if node != cur and not visited[node]:
            visited[node]=True
            dfs(node, total+dist[cur][node])
            visited[node] = False

# 플로이드 와샬 최단거리
def floyd():
    for k in range(N):
        for s in range(N):
            for e in range(N):
                if dist[s][k]+dist[k][e] < dist[s][e]:
                    dist[s][e]=dist[s][k]+dist[k][e]

N,K=map(int, input().split())
dist=[list(map(int, input().split())) for _ in range(N)]
visited=[False]*N
visited[K]=True
answer=float('inf')

floyd()
dfs(K, 0)
print(answer)
