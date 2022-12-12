def solution(dartResult):
    dartResult+='-'
    eachResult=[]
    def makeList():
        j=0
        for i in range(len(dartResult)):
            if dartResult[i] == 'S' or dartResult[i] == 'D' or dartResult[i] == 'T':
                if dartResult[i+1] == '*' or dartResult[i+1] == '#':
                    eachResult.append(dartResult[j:i+2])
                    j=i+2
                else:
                    eachResult.append(dartResult[j:i+1])
                    j=i+1
    def calculate():
        for i, e in enumerate(eachResult):
            if e[:2] == '10':
                n=10
            else:
                n=int(e[0])
            for j in range(1, len(e)):    
                if e[j] == 'S':
                    n=pow(n, 1)
                    eachResult[i]=n
                elif e[j] == 'D':
                    n=pow(n, 2)
                    eachResult[i]=n
                elif e[j] == 'T':
                    n=pow(n, 3)
                    eachResult[i]=n
                elif e[j] == '#':
                    eachResult[i]*=(-1)
                elif e[j] == '*':
                    if i == 0:
                        eachResult[i]*=2
                    else:
                        eachResult[i]*=2
                        eachResult[i-1]*=2
    makeList()
    calculate()
    answer=sum(eachResult)
    return answer
