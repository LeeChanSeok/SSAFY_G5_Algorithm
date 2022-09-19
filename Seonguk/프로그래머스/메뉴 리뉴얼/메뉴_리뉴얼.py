from collections import defaultdict
from itertools import combinations

def solution(orders, course):
    dic_list=[]
    answer=[]
    
    # orders에 있는 조합 각각 정렬하기
    for i in range(len(orders)):
        arr=list(orders[i])
        arr.sort()
        s="".join(arr)
        orders[i]=s
    
    for i in course:
        dic=defaultdict(int)
        for j in orders:
            # 현재 메뉴 구성 수만큼 모든 조합 구하기
            combi=list(map(''.join, combinations(j, i)))
            for k in combi:
                dic[k]+=1
        if dic != {}:
            # 가장 많이 나온 메뉴 조합 수(max_val) 구하기
            max_val=max(dic.values())
        
        for key, value in dic.items():
            # max_v과 같고 2이상이면
            if value == max_val and max_val >=2 :
                answer.append(key)
        
        answer.sort()

    return answer
