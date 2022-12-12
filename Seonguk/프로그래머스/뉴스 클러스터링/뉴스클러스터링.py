from collections import defaultdict

def solution(str1, str2):
    twoAlph1=defaultdict(int)
    twoAlph2=defaultdict(int)
    
    str1=str1.upper()
    str2=str2.upper()
    
    # str을 두글자씩 끊어서 towAlph에 각각 저장 또한 빈도수도 저장
    for i in range(len(str1)-1):
        if 65<=ord(str1[i])<=90 and 65<=ord(str1[i+1])<=90:
            twoAlph1[str1[i:i+2]]+=1
            
    for i in range(len(str2)-1):
        if 65<=ord(str2[i])<=90 and 65<=ord(str2[i+1])<=90:
            twoAlph2[str2[i:i+2]]+=1
    
    # 두다 빈문자열이면
    if not twoAlph1 and not twoAlph2:
        return 65536
    
    minVal=0
    maxVal=0
    # 자카드 유사도 계산
    for s in twoAlph1:
        if s in twoAlph2:
            minVal+=min(twoAlph1[s], twoAlph2[s])
            maxVal+=max(twoAlph1[s], twoAlph2[s])
            twoAlph1[s]=0
            twoAlph2[s]=0
        else:
            maxVal+=twoAlph1[s]
            
    for s in twoAlph2:
        if s in twoAlph1:
            minVal+=min(twoAlph1[s], twoAlph2[s])
            maxVal+=max(twoAlph1[s], twoAlph2[s])
            twoAlph1[s]=0
            twoAlph2[s]=0
        else:
            maxVal+=twoAlph2[s]
    
    answer=int(minVal/maxVal*65536)
                 
    return answer
