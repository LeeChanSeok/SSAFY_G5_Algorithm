# 원자 소멸 시뮬레이션
from collections import defaultdict

TC=int(input())
for tc in range(1,TC+1):
    atoms=[]
    N=int(input())
    for _ in range(N):
        atoms.append(list(map(int, input().split())))

    dic=defaultdict(set)
    for i in range(len(atoms)):
        for j in range(i+1, len(atoms)):
            if atoms[i][0] == atoms[j][0]:
                if atoms[i][1] > atoms[j][1] and atoms[i][2] == 1 and atoms[j][2] == 0:
                    dic[abs(atoms[i][1]-atoms[j][1])].add(i)
                    dic[abs(atoms[i][1] - atoms[j][1])].add(j)
                elif atoms[i][1] < atoms[j][1] and atoms[i][2] == 0 and atoms[j][2] == 1:
                    dic[abs(atoms[i][1] - atoms[j][1])].add(i)
                    dic[abs(atoms[i][1] - atoms[j][1])].add(j)
            if atoms[i][1] == atoms[j][1]:
                if atoms[i][0] > atoms[j][0] and atoms[i][2] == 2 and atoms[j][2] == 3:
                    dic[abs(atoms[i][0] - atoms[j][0])].add(i)
                    dic[abs(atoms[i][0] - atoms[j][0])].add(j)
                elif atoms[i][0] < atoms[j][0] and atoms[i][2] == 3 and atoms[j][2] == 2:
                    dic[abs(atoms[i][0] - atoms[j][0])].add(i)
                    dic[abs(atoms[i][0] - atoms[j][0])].add(j)
            if abs(atoms[i][0] - atoms[j][0]) == abs(atoms[i][1] - atoms[j][1]):
                if atoms[i][0] > atoms[j][0] and atoms[i][1] < atoms[j][1]:
                    if (atoms[i][2] == 0 and atoms[j][2] == 3) or (atoms[i][2] == 2 and atoms[j][2] == 1):
                        dic[abs(atoms[i][1] - atoms[j][1])].add(i)
                        dic[abs(atoms[i][1] - atoms[j][1])].add(j)
                elif atoms[i][0] > atoms[j][0] and atoms[i][1] > atoms[j][1]:
                    if (atoms[i][2] == 1 and atoms[j][2] == 3) or (atoms[i][2] == 2 and atoms[j][2] == 0):
                        dic[abs(atoms[i][1] - atoms[j][1])].add(i)
                        dic[abs(atoms[i][1] - atoms[j][1])].add(j)
                elif atoms[i][0] < atoms[j][0] and atoms[i][1] < atoms[j][1]:
                    if (atoms[i][2] == 0 and atoms[j][2] == 2) or (atoms[i][2] == 3 and atoms[j][2] == 1):
                        dic[abs(atoms[i][1] - atoms[j][1])].add(i)
                        dic[abs(atoms[i][1] - atoms[j][1])].add(j)
                elif atoms[i][0] < atoms[j][0] and atoms[i][1] > atoms[j][1]:
                    if (atoms[i][2] == 3 and atoms[j][2] == 0) or (atoms[i][2] == 1 and atoms[j][2] == 2):
                        dic[abs(atoms[i][1] - atoms[j][1])].add(i)
                        dic[abs(atoms[i][1] - atoms[j][1])].add(j)
    sdic=sorted(dic.items(), key=lambda x:x)

    visited=[False]*N
    answer=0
    for d, s in sdic:
        temp=0
        for val in s:
            if visited[val]:
                temp=0
                break
            visited[val]=True
            temp+=atoms[val][3]
        answer+=temp

    print('#{} {}'.format(tc, answer))
