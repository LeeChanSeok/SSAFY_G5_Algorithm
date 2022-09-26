from bisect import bisect_left

def solution(information, queries):
    arr=[]
    for info in information:
        temp=info.split()
        temp[-1]=int(temp[-1])
        arr.append(temp)
    
    # 점수순으로 오름차순 정렬
    arr=sorted(arr, key=lambda x:x[-1])
    score=[i[-1] for i in arr]
    
    answer=[]
    info_size=len(information)
    for query in queries:
        curQuery=query.replace("and ","").split(" ")
        # 점수를 이분탐색으로 탐색할 위치 구함
        left=bisect_left(score, int(curQuery[-1]))
        num=curQuery.count('-') #쿼리에서 -의 개수
        # 모든 조건이 다 '-'인 경우
        if num == 4:
            answer.append(info_size-left)
            continue
        querySet=set(curQuery[:-1])
        cnt=0
        for i in range(left, info_size):
            infoSet=set(arr[i][:-1])
            # 두개의 집합의 교집합 수가 4-'-'라면
            if len(querySet & infoSet) == 4-num:
                cnt+=1
        answer.append(cnt)
        
    return answer
