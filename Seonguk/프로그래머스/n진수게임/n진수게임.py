def getndigit(n, num): # num을 n진수로 리턴
    alpha=['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F']
    ndigit=""
    if num == 0:
        return '0'
    while num > 0:
        div=num//n
        mod=num%n
        num=div
        ndigit+=alpha[mod]
    return ndigit[::-1]

def solution(n, t, m, p):
    num = 0
    totaldigit=""
    answer=""
    
    while len(totaldigit) <= t*m: # totaldigit이 t*m이 될때까지 반복
        totaldigit+=getndigit(n, num) # 모든사람이 말한 숫자 구하기
        num+=1
    
    for i in range(t*m):
        if i % m == p-1: # 튜브 순서의 숫자만 뽑기
            answer+=totaldigit[i]
    
    return answer
