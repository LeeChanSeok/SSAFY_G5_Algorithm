def check(answer):
    for x,y,a in answer:
        if a == 0:
            # 밑에 칸이 바닥이거나 기둥이거나 보 한쪽 끝이 아니면 False           
            if not (y == 0 or (x,y-1,0) in answer or ( (x-1,y,1) in answer or (x,y,1) in answer )):
                return False
        else:
            # 한쪽 끝 밑에 칸이 기둥이거나 양쪽 끝부분이 보가 아니면 False
            if not ( ((x,y-1,0) in answer or (x+1,y-1,0) in answer) or ((x-1,y,1) in answer and (x+1,y,1) in answer) ):
                return False
    
    return True
    
def solution(n, build_frame):
    answer=set()
    for x,y,a,b in build_frame:
        if b == 0:
            answer.remove((x,y,a))
            if not check(answer):
                answer.add((x,y,a))
        else:
            answer.add((x,y,a))
            if not check(answer):
                answer.remove((x,y,a))
    
    answer=sorted(list(answer))
    
    return answer
