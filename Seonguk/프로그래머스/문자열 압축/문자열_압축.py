def solution(s):
    cut_s=[]
    
    # 입력 문자열 길이가 1이면
    if(len(s)==1):
        return 1
    
    # s의 문자열을 하나씩 쪼개기
    for cut in range(1, (len(s) // 2) + 1):
        cnt=1
        substr=s[:cut]
        result=""
        # 해당 부분 문자열로 압축할 수 있는지 확인
        for i in range(cut, len(s), cut):
            # 압축할 수 있다면 cnt+1
            if s[i:i+cut] == substr:
                cnt+=1
            # 압축할 수 없다면
            else:
                if cnt == 1:
                    cnt=""
                # 이전까지 압축한 개수+부분 문자열를 result에 붙이기
                result=result+str(cnt)+substr
                substr=s[i:i+cut]
                cnt=1
        if cnt == 1:
            cnt=""
        result+=str(cnt)+substr
        cut_s.append(len(result))
        result=""
           
    return min(cut_s)
