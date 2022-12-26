#큐빙

def uTurn(dir):
    temp = cube['L'][0][:]
    if dir == '-':
        cube['L'][0]=cube['B'][0]
        cube['B'][0] = cube['R'][0]
        cube['R'][0] = cube['F'][0]
        cube['F'][0] = temp
        cube['U'] = list(map(list, zip(*cube['U'])))[::-1]
    else:
        cube['L'][0] = cube['F'][0]
        cube['F'][0] = cube['R'][0]
        cube['R'][0] = cube['B'][0]
        cube['B'][0] = temp
        cube['U']=list(map(list, zip(*cube['U'][::-1])))

def dTurn(dir):
    temp = cube['L'][2][:]
    if dir == '-':
        cube['L'][2] = cube['F'][2]
        cube['F'][2] = cube['R'][2]
        cube['R'][2] = cube['B'][2]
        cube['B'][2] = temp
        cube['D'] = list(map(list, zip(*cube['D'])))[::-1]
    else:
        cube['L'][2] = cube['B'][2]
        cube['B'][2] = cube['R'][2]
        cube['R'][2] = cube['F'][2]
        cube['F'][2] = temp
        cube['D'] = list(map(list, zip(*cube['D'][::-1])))

def lTurn(dir):
    temp=[cube['U'][i][0] for i in range(3)]
    if dir == '-':
        for i in range(3):
            cube['U'][i][0]=cube['F'][i][0]
        for i in range(3):
            cube['F'][i][0]=cube['D'][i][0]
        for i in range(3):
            cube['D'][i][0]=cube['B'][2-i][2]
        for i in range(3):
            cube['B'][i][2]=temp[2-i]
        cube['L'] = list(map(list, zip(*cube['L'])))[::-1]
    else:
        for i in range(3):
            cube['U'][i][0]=cube['B'][2-i][2]
        for i in range(3):
            cube['B'][i][2]=cube['D'][2-i][0]
        for i in range(3):
            cube['D'][i][0]=cube['F'][i][0]
        for i in range(3):
            cube['F'][i][0]=temp[i]
        cube['L'] = list(map(list, zip(*cube['L'][::-1])))

def rTurn(dir):
    temp=[cube['U'][i][2] for i in range(3)]
    if dir == '-':
        for i in range(3):
            cube['U'][i][2] = cube['B'][2 - i][0]
        for i in range(3):
            cube['B'][i][0] = cube['D'][2 - i][2]
        for i in range(3):
            cube['D'][i][2] = cube['F'][i][2]
        for i in range(3):
            cube['F'][i][2] = temp[i]
        cube['R'] = list(map(list, zip(*cube['R'])))[::-1]
    else:
        for i in range(3):
            cube['U'][i][2] = cube['F'][i][2]
        for i in range(3):
            cube['F'][i][2] = cube['D'][i][2]
        for i in range(3):
            cube['D'][i][2] = cube['B'][2 - i][0]
        for i in range(3):
            cube['B'][i][0] = temp[2 - i]
        cube['R'] = list(map(list, zip(*cube['R'][::-1])))

def fTurn(dir):
    temp=cube['U'][2][:]
    if dir == '-':
        for i in range(3):
            cube['U'][2][i]=cube['R'][i][0]
        for i in range(3):
            cube['R'][i][0]=cube['D'][0][2-i]
        for i in range(3):
            cube['D'][0][i]=cube['L'][i][2]
        for i in range(3):
            cube['L'][i][2]=temp[2-i]
        cube['F'] = list(map(list, zip(*cube['F'])))[::-1]
    else:
        for i in range(3):
            cube['U'][2][i]=cube['L'][2-i][2]
        for i in range(3):
            cube['L'][i][2]=cube['D'][0][i]
        for i in range(3):
            cube['D'][0][i]=cube['R'][2-i][0]
        for i in range(3):
            cube['R'][i][0]=temp[i]
        cube['F'] = list(map(list, zip(*cube['F'][::-1])))

def bTurn(dir):
    temp = cube['U'][0][:]
    if dir == '-':
        for i in range(3):
            cube['U'][0][i] = cube['L'][2 - i][0]
        for i in range(3):
            cube['L'][i][0] = cube['D'][2][i]
        for i in range(3):
            cube['D'][2][i] = cube['R'][2 - i][2]
        for i in range(3):
            cube['R'][i][2] = temp[i]
        cube['B'] = list(map(list, zip(*cube['B'])))[::-1]
    else:
        for i in range(3):
            cube['U'][0][i]=cube['R'][i][2]
        for i in range(3):
            cube['R'][i][2]=cube['D'][2][2-i]
        for i in range(3):
            cube['D'][2][i]=cube['L'][i][0]
        for i in range(3):
            cube['L'][i][0]=temp[2-i]
        cube['B'] = list(map(list, zip(*cube['B'][::-1])))

T=int(input())
for tc in range(T):
    cube = {}
    cube['U'] = [['w'] * 3 for _ in range(3)]
    cube['D'] = [['y'] * 3 for _ in range(3)]
    cube['L'] = [['g'] * 3 for _ in range(3)]
    cube['R'] = [['b'] * 3 for _ in range(3)]
    cube['F'] = [['r'] * 3 for _ in range(3)]
    cube['B'] = [['o'] * 3 for _ in range(3)]

    num=int(input())
    dir_info=map(str, input().split())

    for d in dir_info:
        if d[0] == 'U':
            uTurn(d[1])
        elif d[0] == 'D':
            dTurn(d[1])
        elif d[0] == 'F':
            fTurn(d[1])
        elif d[0] == 'B':
            bTurn(d[1])
        elif d[0] == 'L':
            lTurn(d[1])
        elif d[0] == 'R':
            rTurn(d[1])

    for i in range(3):
        print("".join(cube['U'][i]))
