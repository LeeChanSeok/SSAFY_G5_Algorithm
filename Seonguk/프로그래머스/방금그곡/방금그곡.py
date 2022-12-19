def solution(m, musicinfos):
    candi =[]
    dic = {'C#':'H','D#':'I','F#':'J','G#':'K','A#':'L'}
    # #붙은 음 치환하기
    for key, val in dic.items():
        m = m.replace(key,val)
    
    for i,musicinfo in enumerate(musicinfos):
        start,end,name,music = musicinfo.split(',')
        for key, val in dic.items():
            music = music.replace(key, val) # 악보 치환
            
        start, end = list(map(int,start.split(':'))),list(map(int,end.split(':')))
        time = (end[0]-start[0])*60 + end[1]-start[1] # 재생시간을 총 분으로 계산
        
        if time < len(music):
            music = music[:time] # 처음부터 재생된 경우
        else:
            # time만큼 반복 재생
            music = music*(time//len(music)) + music[:(time%len(music))]
        
        # 멜로디가 악보에 있으면 
        if m in music:
            candi.append([name,time,i]) # 후보에 이름, 시간, idx 정보 저장
    
    # 순서, 제목 길이 순으로 정렬
    candi.sort(key=lambda x: (-x[1],x[0]))
    if candi:
        return candi[0][0]
    else:
        return "(None)"
