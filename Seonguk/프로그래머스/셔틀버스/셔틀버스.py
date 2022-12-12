def solution(n, t, m, timetable):
    timetable.sort()
    # 초단위로 바꿔서 정렬
    timetable=[list(map(int, i.split(':'))) for i in timetable]
    timetable=[i[0]*60+i[1] for i in timetable]
    #셔틀 도착시간을 초단위로 변경
    cur_time=540
    dic={}
    
    #셔틀 도착시간을 key로 하는 딕셔너리 선언
    for i in range(n):
        dic[cur_time]=[]
        cur_time+=t
    
    idx=0
    for dep_time in dic:
        cnt=0
        # 가장 먼저 도착한 크루부터 순서대로 셔틀에 태우기
        while idx < len(timetable):
            # 셔틀에 인원이 다 찼거나 더이상 태울 크루가 없는 경우
            if  cnt >= m or timetable[idx] > dep_time:
                break
            # 셔틀에 태울 수 있는 인원이 있으면 dic에 넣기
            if timetable[idx] <= dep_time:
                dic[dep_time].append(timetable[idx])
                idx+=1
                cnt+=1
    
    # 도착시간이 빠른 셔틀부터 검사
    for dep_time in dic:
        # 현재 셔틀에 자리가 남아있으면 셔틀 도착시간이 답
        if len(dic[dep_time]) < m:
            answer=dep_time
        # 자리가 없으면 (가장 마지막에 탑승한 크루의 도착시간 - 1)
        elif len(dic[dep_time]) == m:
            answer=dic[dep_time][-1]-1
    
    # 시간을 문자열로 변경
    if answer == 0:
        answer="00:00"
    else:
        h=answer//60
        m=answer%60
        if h < 10:
            h='0'+str(h)
        if m < 10:
            m='0'+str(m)
        answer=str(h)+':'+str(m)
            
    return answer
