def solution(n, arr1, arr2):
    binL=[]
    bin1=[]
    bin2=[]
    answer=[]
    for i in arr1:
        binN1=bin(i)
        binN1=list("".join(binN1))
        
        del binN1[0]
        del binN1[0]
    
        if n - len(binN1) != 0:
            for i in range(n - len(binN1)):
                binN1.insert(0, '0')
        bin1.append(binN1)
        
    for i in arr2:
        binN2=bin(i)
        binN2=list("".join(binN2))
        
        del binN2[0]
        del binN2[0]
    
        if n - len(binN2) != 0:
            for i in range(n - len(binN2)):
                binN2.insert(0, '0')
        bin2.append(binN2)
    
    for i, j in zip(bin1, bin2):
        s=''
        for a, b in zip(i, j):
            if a == '1' or b == '1':
                s+='#'
            else:
                s+=' '
        answer.append(s)
    return answer
