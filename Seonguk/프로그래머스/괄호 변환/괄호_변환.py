# 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙임
def modify(string):
    if string =="":
        return ""
    else:
        res=""
        for s in string[1:-1]:
            if s =='(':
                res+=')'
            else:
                res+='('
        return res
# 올바른 문자열인지 체크
def isRightstr(string):
    temp=0
    for i in range(len(string)):
        if string[i] == '(':
            temp+=1
        else:
            temp-=1
        if temp < 0:
            return False
    return True
# 문자열을 u, v로 나누기
def split(string):
    temp=0
    for i in range(len(string)):
        if string[i] == '(':
            temp+=1
        else:
            temp-=1
        if temp == 0:
            return string[:i+1], string[i+1:]
    return string, ""

def dfs(string):
    global answer
    # 1. 문자열을 u, v로 나누기
    if string == "":
        return ""
    # 2. 두 "균형잡힌 괄호 문자열"로 분리
    u, v=split(string)
    if isRightstr(u): # 3. u가 올바른 문자열이면
        return u+dfs(v) # 3-1. 수행한 결과 문자열을 u에 붙여 리턴
    else:
        # 4. u가 "올바른 괄호 문자열"이 아니라면
        # 4.1 ~ 4.5
        return '('+dfs(v)+')'+modify(u) 

def solution(p):
    answer=dfs(p)
    
    return answer
