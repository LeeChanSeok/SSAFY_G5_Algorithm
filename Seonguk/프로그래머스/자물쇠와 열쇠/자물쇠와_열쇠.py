#  key가 lock에 맞는지 확인하는 함수
def check(key,lock,x,y,N):
    # 복사한 lock 리스트에 key 리스트 더하기
    copy_lock=[i[:] for i in lock]
    for cx in range(x,x+len(key)):
        for cy in range(y,y+len(key)):
            copy_lock[cx][cy]+=key[cx-x][cy-y]
    
    # 확장된 lock 리스트에서 실제로 lock부분을 돌면서 원소가 1인지 확인
    # 모든 원소가 1이면 lock과 key가 잘 맞음
    sx,sy=len(key)-1, len(key)-1
    for cx in range(sx,sx+N):
        for cy in range(sy,sy+N):
            if copy_lock[cx][cy] != 1:
                return False
    return True

def solution(key, lock):
    addSize=len(key)-1
    sub=[0]*(len(lock[0])+2*addSize)
    N=len(lock)
    
    # lock 리스트의 행, 열을 각각 len(key)-1만큼 확장
    for i in range(len(lock)):
        lock[i]=([0]*addSize)+lock[i]+([0]*addSize)
    for i in range(addSize):
        lock.insert(0,sub)
        lock.append(sub)
    
    # lock 리스트((0,0)부터)에 key 리스트가 맞는지 확인
    answer=False
    for i in range(4):
        for x in range(0, len(lock)-len(key)+1):
            for y in range(0, len(lock[0])-len(key)+1):
                answer=check(key,lock,x,y,N)
                if answer:
                    break
            if answer:
                break
        if answer:
            break
        # key를 90도 회전
        key=list(zip(*key[::-1]))
        
    return answer
