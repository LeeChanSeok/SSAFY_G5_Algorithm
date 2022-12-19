def solution(files):
    answer = []
    
    for file in files:
        head, number, tail = '', '', ''
        for i in range(len(file)):
            if file[i].isdigit(): 
                head = file[:i]
                number = file[i:]
                for j in range(len(number)):
                    if not number[j].isdigit():
                        tail = number[j:]
                        number = number[:j]
                        break
                # head, number, tail 구분하여 저장
                answer.append([head, number, tail])
                break
    
    # head, number순으로 저장
    answer = sorted(answer, key=lambda x:(x[0].lower(), int(x[1])))
    answer = [''.join(i) for i in answer]
    
    return answer
