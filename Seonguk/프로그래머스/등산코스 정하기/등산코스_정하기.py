import sys
sys.setrecursionlimit(10**8)

graph = {}
dic = {}
visited = []

def dfs(cur, end, intensity, gates):
    global graph, dic, visited
    
    if cur != end and cur in dic:
        return
    
    if cur == end:
        if dic[end] > intensity:
            dic[end] = intensity
        return

    for node, weight in graph[cur]:
        if node in gates:
            continue
        if dic[end] > weight and not visited[node]:
            visited[node] = True
            dfs(node, end, max(weight, intensity), gates)
            visited[node] = False


def solution(n, paths, gates, summits):
    global graph, dic, visited

    graph = {i: [] for i in range(1, n + 1)}
    for v, w, dist in paths:
        graph[v].append((w, dist))
        graph[w].append((v, dist))

    for s in summits:
        dic[s] = 50001
    
    for summit in summits:
        visited = [False] * (n + 1)
        for gate in gates:
            visited[gate] = True
            temp_gates=gates[:]
            temp_gates.remove(gate)
            dfs(gate, summit, 0, temp_gates)
            visited[gate] = False
    
    dic=sorted(dic.items(), key = lambda x: (x[1], x[0]))
    
    answer=[dic[0][0], dic[0][1]]
    
    return answer
