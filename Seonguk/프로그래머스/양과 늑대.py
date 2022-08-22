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
    
    cur_visitable=visitable[:]  # 리스트 복사
    cur_visitable.remove(idx)   # 현재 노드 삭제
    cur_visitable.extend(tree[idx]) # 자식 노드들 추가

    for v in cur_visitable:
        dfs(info, tree, v, sheep, wolf, cur_visitable)

def solution(info, edges):
    global answer
    # 딕셔너리로 트리 구현
    tree={i:[] for i in range(len(info))}
    
    for p, c in edges:
        tree[p].append(c)
    
    dfs(info, tree, 0, 0, 0, [0])
    
    return answer
