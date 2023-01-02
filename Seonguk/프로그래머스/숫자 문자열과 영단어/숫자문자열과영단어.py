def solution(s):
    str_num=["zero", "one", "two", "three", "four", "five", "six", "seven", 
            "eight", "nine"]
    str_num1=["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
    num_list=[]
    answer = ""
    
    for i in range(len(s)):
        for j in range(i, len(s)):
            if s[i:j+1] in str_num or s[i:j+1] in str_num1:
                num_list.append(s[i:j+1])
    
    for i in num_list:
        if i in str_num:
            answer+=str(str_num.index(i))
        elif i in str_num1:
            answer+=str(str_num1.index(i))
    
    answer=int(answer)
    return answer
