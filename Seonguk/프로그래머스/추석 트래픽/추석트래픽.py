def solution(lines):
    answer=0
    start_time=[]
    end_time=[]
    for l in lines:
        day, time, duration_time=l.split()
        # 시간을 ms로 변환
        total_time=(int(time[:2])*3600 + int(time[3:5])*60 + int(time[6:8]))*1000+int(time[9:])
        #처리시간을 ms로 변환
        duration_time=int(float(duration_time[:-1])*1000)
        #시작시간과 끝나는시간을 각각 저장 
        start_time.append(total_time-duration_time+1)
        end_time.append(total_time)
        
    answer=-1
    for i in range(len(lines)):        
        cnt=0
        cur_end_time=end_time[i]
        # 현재 끝나는 시간의 로그 다음부터 하나씩 돌면서 시작시간 확인
        for j in range(i, len(lines)):
            if cur_end_time > start_time[j]-1000:
                cnt+=1
        answer=max(answer, cnt)
        
    return answer
