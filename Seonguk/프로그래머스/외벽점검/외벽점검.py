from itertools import permutations

def solution(n, weak, dist):
    answer=len(dist)+1
    sz=len(weak)
    
    for i in range(sz):
        weak.append(weak[i]+n)
    
    for i in range(sz):
        subWeak=[weak[k] for k in range(i,i+sz)]
        for permu in permutations(dist, len(dist)):
            result=1
            start=subWeak[0]+permu[0]
            pidx=0
            for j in range(1, sz):
                if start<subWeak[j]:
                    result+=1
                    if result > len(permu):
                        break
                    pidx+=1
                    start=subWeak[j]+permu[pidx]
            answer=min(answer,result)
    
    if answer>len(dist):
        return -1
    
    return answer
