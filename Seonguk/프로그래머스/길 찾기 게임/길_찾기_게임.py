import sys
sys.setrecursionlimit(10**6)

# x좌표값을 기준으로 이진트리 구성
def insert(dic, root, key):
    while True:
        if dic[key][0] < dic[root][0]:
            if dic[root][2] == 0:
                dic[root][2]=key
                break
            else:
                root=dic[root][2]
        elif dic[key][0] > dic[root][0]:
            if dic[root][3] == 0:
                dic[root][3]=key
                break
            else:
                root=dic[root][3]
        else:
            break
# 전위 순회
def preorder(dic, root, pre):
    if root  == 0:
        return
    pre.append(root)
    preorder(dic, dic[root][2], pre)
    preorder(dic, dic[root][3], pre)
# 후위 순회
def postorder(dic, root, post):
    if root == 0:
        return
    postorder(dic, dic[root][2], post)
    postorder(dic, dic[root][3], post)
    post.append(root)
    
def solution(nodeinfo):
    dic={}
    for i in range(len(nodeinfo)):
        nodeinfo[i].append(i+1) # node[id]=[x,y,key]
        dic[i+1]=[nodeinfo[i][0], nodeinfo[i][1], 0, 0] # dic[key] = [x,y,left,right]
    
    # 높이(y좌표)가 큰순으로 정렬
    nodeinfo=sorted(nodeinfo, key=lambda x: -x[1])
    root=nodeinfo[0][2] # 가장 높이 있는게 루트노트
    for x,y,key in nodeinfo:
        insert(dic, root, key)
    pre, post=[], []
    preorder(dic, root, pre)
    postorder(dic, root, post)
    
    answer=[pre,post]
    return answer
