from collections import defaultdict

def solution(clothes):
    dic=defaultdict(int)
    # 각 의상마다 몇개있는지 저장
    for i in range(len(clothes)):
        dic[clothes[i][1]]+=1
    
    answer=1
    for val in dic.values():
        answer*=(val+1) # 아무것도 안입는 경우의 수 + 1
    
    return answer-1 # 최소 하나의 의상을 입어야하므로 - 1
