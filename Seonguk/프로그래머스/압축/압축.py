def dfs(msg, idx, answer, dic):
    for i in range(idx+1, len(msg)+1):
        if msg[idx:i] not in dic: # 찾는 단어가 없으면 
            dic.append(msg[idx:i]) # 등록하고
            next_idx=i-1 # 다음 탐색 위치 저장
            break
        if i == len(msg):
            next_idx=i
            
    if next_idx == len(msg): # i가 끝에 도달했으면
        answer.append(dic.index(msg[idx:])) # 사전에 등록하고 종료
        return
    else:
        answer.append(dic.index(msg[idx:next_idx]))
    
    dfs(msg, next_idx, answer, dic)
    
def solution(msg):
    answer=[]
    dic=['','A','B','C','D','E','F','G','H','I','J','K','L','M','N',
         'O','P','Q','R','S','T','U','V','W','X','Y','Z']
    dfs(msg, 0, answer, dic)
    
    return answer
