def solution(m, n, board):
    answer=0
    arr=[]
    # 문자열을 리스트로 만들어서 arr에 저장
    for b in board:
        arr.append(list(b))
    # 블록을 내리기 쉽게(열 이동) 하기 위해 90도 회전
    arr=list(zip(*arr))
    arr=[list(i) for i in arr]
    
    while True:
        delPos=set()
        for i in range(len(arr)-1):
            for j in range(len(arr[0])-1):
                # 4개의 블록이 같으면 delPos에 좌표 저장
                if arr[i][j] != 0 and arr[i][j] == arr[i+1][j] == arr[i][j+1] == arr[i+1][j+1]:
                    delPos.add((i,j))
                    delPos.add((i+1,j))
                    delPos.add((i,j+1))
                    delPos.add((i+1,j+1))
        # 더이상 삭제할 블록이 없다면
        if len(delPos) == 0:
            break
        
        answer+=len(delPos)
        # 삭제될 칸 0으로 처리
        for x, y in delPos:
            arr[x][y]=0
        
        # 빈칸이 있으면 블록 내리기
        for i in range(len(arr)):
            for j in range(len(arr[i])-2, -1, -1):
                # 빈칸이면
                if arr[i][j] != 0 and arr[i][j+1] == 0:
                    # 현재 칸에서 위로 올라가면서 한칸씩 내리기
                    for k in range(j+1, len(arr[0])):
                        if k == len(arr[0])-1 or arr[i][k+1] != 0:
                            arr[i][k]=arr[i][j]
                            arr[i][j]=0
                            break
    return answer
