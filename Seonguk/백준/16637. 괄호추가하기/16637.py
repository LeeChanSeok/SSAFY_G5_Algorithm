#괄호 추가하기
import sys
input=sys.stdin.readline

N=int(input())
exp=str(input())[:-1]

operator=[]
operand=[]
#연산자 피연산자 따로 저장
for e in exp:
    if e.isdigit():
        operand.append(int(e))
    else:
        operator.append(e)

def cal(n1,n2,op):
    if op == '+':
        return n1+n2
    elif op == '-':
        return n1-n2
    elif op == '*':
        return n1*n2

def calcul(oper):
    idx=0
    cur=operand[idx]
    while idx < len(oper):
        if idx == len(oper)-1:
            break
        #다음 idx가 괄호인 경우, idx+1을 먼저 처리하고 idx번째 연산자 처리
        if oper[idx+1] == 1:
            temp=cal(operand[idx+1], operand[idx+2], operator[idx+1])
            cur=cal(cur,temp,operator[idx])
            idx+=2
        else:
            cur=cal(cur, operand[idx+1], operator[idx])
            idx+=1
    return cur

answer=float('-inf')
def dfs(idx, oper):
    global answer
    if idx >= len(operator)-1:
        answer=max(answer, calcul(oper))
        return

    # 해당 연산자에 괄호가 있는 경우, 1로 표시하여 모든 경우의 수 탐색.
    # ex) (1+2)*3-(4+5) => [+,*,-,+] => [1,0,0,1]
    if oper[idx] == 0:
        oper[idx+1]=1
        dfs(idx+1, oper)
        oper[idx+1]=0
    dfs(idx+1, oper)

if N == 1:
    answer=int(exp[0])
else:
    dfs(0, [0] * len(operand))              # 처음 연산자에 괄호가 있는 경우 
    dfs(0, [1] + [0] * (len(operand) - 1))  # 없는 경우 

print(answer)