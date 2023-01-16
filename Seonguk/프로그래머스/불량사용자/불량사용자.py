answer=[]
ban=[]
idx=0

def dfs(s,sz):
    global idx
    if idx >= sz:
        answer.append(sorted(s[:]))
        return
    for b in ban[idx]:
        if b in s:
            continue
        # ban idx를 돌면서 하나씩 선택하여 s에 넣기
        s.append(b)
        idx+=1
        dfs(s,sz)
        # 백트랙킹
        idx-=1
        s.remove(b)

def solution(user_id, banned_id):

    for b in banned_id:
        b=list(b)
        sz=len(b)
        temp=[]
        same=[]
        for i in range(len(b)):
            # 별인 idx temp에 넣기
            if b[i] == '*':
                temp.append(i)
        for u in user_id:
            if len(u) != sz:
                continue
            u_id=list(u)
            # u_id에 temp에서 저장했던 idx에 * 넣기
            for t in temp:
                u_id[t]='*'
            # 같은지 검사
            if "".join(u_id) == "".join(b):
                same.append(u)
        ban.append(same)
        
    dfs([],len(ban))
    res=list(set([tuple(set(i)) for i in answer]))
    
    return len(res)
