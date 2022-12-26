def dfs(visited, computers, v):
    # 방문표시
    visited[v] = 1
    for w in range(len(visited)):
        # v와 w가 연결되어있고 w가 방문되지 않았다면
        if computers[v][w] == 1 and visited[w] == 0:
            dfs(visited, computers, w)

def solution(n, computers):
    visited=[0 for _ in range(n)]
    num=0
    for v in range(n):
        # 방문되지 않은 노드가 있다면 네트워크 개수 + 1
        if visited[v] == 0:
            num+=1
            dfs(visited, computers, v)
            
    return num
