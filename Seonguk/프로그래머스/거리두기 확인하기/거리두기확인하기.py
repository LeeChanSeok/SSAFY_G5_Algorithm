def solution(places):
    answer=[]
    
    def dfs(r, c, dist):
        nonlocal flag
        
        if dist>2:
            return
        if 0<=r<=4 and 0<=c<=4:
            if p_list[r][c]=='X':
                return
            # 거리가 2이하이면서 응시자가 앉아있으면 거리두기 실패
            if dist != 0 and p_list[r][c]=='P':
                flag=0
                return
            # 현재 방문된 곳은 파티션 처리
            p_list[r][c]='X'
            # 상하좌우로 dist+1해서 dfs 호출 
            dfs(r-1,c, dist+1)
            dfs(r+1,c, dist+1)
            dfs(r,c-1, dist+1)
            dfs(r,c+1, dist+1)
            
    for p in places:
        p_list=[list(i) for i in p]
        flag=1
        for r in range(len(p_list)):
            for c in range(len(p_list[r])):
                # 응시자 위치를 기준으로 dfs
                if p_list[r][c] == 'P':
                    dfs(r, c, 0)
        answer.append(flag)
    
    return answer
