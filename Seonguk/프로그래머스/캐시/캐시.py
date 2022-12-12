def solution(cacheSize, cities):
    cities=[cities[i].lower() for i in range(len(cities))]
    cache=[]
    time=0
    
    if cacheSize==0:
        return 5*len(cities)
    
    for c in cities:
        if len(cache) < cacheSize: # 캐시에 공간이 남은 경우
            if c not in cache: # miss일 경우
                cache.append(c)
                time+=5
            else: # hit일 경우
                cache.append(cache.pop(cache.index(c)))
                time+=1
        elif len(cache) == cacheSize: # 캐시에 공간이 없는 경우
            if c not in cache: # 가장 마지막에 들어간 도시를 빼고 넣기(miss)
                cache.pop(0)
                cache.append(c)
                time+=5
            else: # 기존에 있던 도시를 빼고 끝자리에 넣기
                cache.append(cache.pop(cache.index(c)))
                time+=1
                
    answer = time
    return answer
