def solution(numbers):
    answer = ''
    sortedS=sorted(list(map(str, numbers)), key=lambda x:x*3, reverse=True) 
    #리스트 내 int를 str로 변환하여 정렬, 각 요소들은 1000이하이므로
    #30 3을 정렬해야할 경우 *3을 해주어 303030 333으로 만든뒤 정렬
    
    answer=str(int("".join(sortedS)))
    return answer
