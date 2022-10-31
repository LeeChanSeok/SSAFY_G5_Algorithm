def solution(record):
    answer = []
    recordSp=[[] for i in range(len(record))]
    dic_uid={}
    for i, r in enumerate(record):
        recordSp[i]=r.split()
    
    # 닉네임이 변경된 경우, 아이디별 닉네임 저장
    for i in recordSp:
        if i[0] =="Enter" or i[0] =="Change":
            dic_uid[i[1]]=i[2]
    
    for i in recordSp:
        if i[0] == "Enter":
            temp=dic_uid[i[1]]+"님이 들어왔습니다."
            answer.append(temp)
        elif i[0] == "Leave":
            temp=dic_uid[i[1]]+"님이 나갔습니다."
            answer.append(temp)
    
    return answer
