# 선방문 해야할 방을 방문하지 않았다면 그 방은 skip
# 이후에 그 방을 만났다면 바로 후 방문할 방을 재귀로 호출

def dfs(node):
    stack = [node]
    visited[node] = True
    while stack:
        cur = stack.pop()
        for next in graph[cur]:
            if n_visit[cur]: #후 방문이 있을 경우 점프
                dfs(n_visit[cur])
            if not visited[p_visit[cur]]: #먼저 방문해야할 노드를 방문하지 않았을 경우
                n_visit[p_visit[cur]] = cur #후 방문 list에 저장
                continue
            if not visited[next]: #방문한 적 없는 노드일경우 stack에 추가
                stack.append(next)
                visited[next] = True
 
def solution(n, path, order):
    global graph, p_visit, n_visit, visited
    graph = [[] for _ in range(n)] # 인접행렬 그래프 
    p_visit = [0 for _ in range(n)] # 노드 방문 순서 리스트
    n_visit = [0 for _ in range(n)]
    visited = [False for _ in range(n)]
    
    for p in path:
        graph[p[0]].append(p[1])
        graph[p[1]].append(p[0])
        
    for p in order:
        p_visit[p[1]] = p[0]
        
    dfs(0) #dfs 실행
    
    if sum(visited) == n: #모두 방문했을 경우 True 출력
        return True
    
    return False
