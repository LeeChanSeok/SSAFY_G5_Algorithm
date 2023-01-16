from collections import defaultdict

def solution(gems):
    answer = [0]*2
    candidates = []
    start, end = 0, 0
    gems_len, gem_kind = len(gems), len(set(gems))
    gems_dict = defaultdict(int)
    
    while True:
        kind = len(gems_dict)
        if start == gems_len:
            break
        # 현재 젬 종류가 다 찼으면
        if kind == gem_kind:
            # candi에 저장
            candidates.append((start, end))
            # 하나 빼기
            gems_dict[gems[start]] -= 1
            if gems_dict[gems[start]] == 0:
                del gems_dict[gems[start]]
            start += 1
            continue
        if end == gems_len:
            break
        # 종류가 덜 찬 경우
        if kind < gem_kind:
            #추가
            gems_dict[gems[end]] += 1
            end += 1
            continue

    length = float('inf')
    for s, e in candidates:
        if length > e-s:
            length = e-s
            answer[0] = s + 1
            answer[1] = e
    return answer
