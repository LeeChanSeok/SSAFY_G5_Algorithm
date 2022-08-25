#활주로 건설

def check(row):
    visited=[0]*len(row)    # 활주로 건설 체크 배열

    #내리막 활주로 check
    for i in range(1,len(row)):

        if row[i-1]-row[i] >= 2:
            return False
        if row[i-1]-row[i] == 1:
            for j in range(i,i+X):
                # 범위를 벗어나거나 건설할 땅이 평평하지 않으면
                if j >= N or row[j] != row[i]:
                    return False
                visited[j]=1    # 활주로 건설 표시시

    #르막 활주로 check
    for i in range(len(row)-2, -1, -1):
        if row[i+1]-row[i] >= 2:
            return False
        if row[i+1]-row[i] == 1:
            for j in range(i, i-X, -1):
                # 활주로가 겹쳐지는 경우
                if j < 0 or row[j] != row[i] or visited[j] == 1:
                    return False

    return True

TC=int(input())
for tc in range(1,TC+1):
    N, X=map(int, input().split())
    arr=[list(map(int, input().split())) for _ in range(N)]

    answer=0
    # 행 검사
    for r in arr:
        if check(r):
            answer+=1

    # 배열 90도 회전
    arr=list(zip(*arr[::-1]))
    # 열 검사
    for r in arr:
        if check(r):
            answer+=1

    print("#{} {}".format(tc, answer))