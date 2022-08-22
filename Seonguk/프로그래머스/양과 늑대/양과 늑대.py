answer = 0

def dfs(info, tree, idx, sheep, wolf, visitable):
    global answer

    if info[idx] == 0:
        sheep+=1
    else:
        wolf+=1
        
    if wolf >= sheep:
        return
    
    answer=max(answer, sheep)
    
    # cur_vistiable에 다음에 방문할 수 있는 노드를 저장
    cur_visitable=visitable[:]
    cur_visitable.remove(idx)   # 현재 노드는 삭제
    cur_visitable.extend(tree[idx]) # 후보에 자식 노드들 추가

    for v in cur_visitable:
        dfs(info, tree, v, sheep, wolf, cur_visitable)

def solution(info, edges):
    global answer
    tree={i:[] for i in range(len(info))}
    
    for p, c in edges:
        tree[p].append(c)
    
    dfs(info, tree, 0, 0, 0, [0])
    
    return answer
