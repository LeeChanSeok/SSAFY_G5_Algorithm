def toSec(time):
    sec=0
    time=time.split(":")
    sec+=int(time[0])*3600
    sec+=int(time[1])*60
    sec+=int(time[2])
    return sec

def solution(play_time, adv_time, logs):
    
    if play_time == adv_time:
        return "00:00:00"
    
    alltime=[0]*(toSec(play_time)+1)
    adv_time=toSec(adv_time)
    for log in logs:
        start, end=log.split("-")
        start=toSec(start)
        end=toSec(end)
        
        # 누적합 표시
        alltime[start]+=1
        alltime[end]-=1
    
    answer=0
    max_val=0
    for i in range(1, len(alltime)):
        alltime[i]+=alltime[i-1]
    for i in range(1, len(alltime)):
        alltime[i]+=alltime[i-1]
        if i >= adv_time-1: # alltime[i] = i-1초까지의 누적 재생시간
            curAdv=alltime[i]-alltime[i-adv_time] # curAdv=i-adv_time초~i초까지의 총 광고 시간
            startAdv=i-adv_time+1
            if max_val < curAdv:
                max_val=curAdv
                answer=startAdv
    
    # 초를 시간 형식으로 바꿔주기
    hour=str(answer//3600)
    hour='0'+hour if len(hour) == 1 else hour
    minute=str((answer%3600)//60)
    minute='0'+minute if len(minute) == 1 else minute
    second=str(answer%3600%60)
    second='0'+second if len(second) == 1 else second
    
    return hour+':'+minute+':'+second
