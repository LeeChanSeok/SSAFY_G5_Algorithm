#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;


int solution(string str1, string str2) {
    int answer = 0;
    vector<string> s1;
    vector<string> s2;
    
    // 대문자 변환
    transform(str1.begin(), str1.end(), str1.begin(), ::toupper);
    transform(str2.begin(), str2.end(), str2.begin(), ::toupper);
    
    // str1 처리
    for(int i=0; i<str1.length(); i++){
        // 알파벳이 아닌 경우
        if(str1[i] <65 || str1[i] > 90 || str1[i+1] <65 || str1[i+1] > 90)
            continue;
        string temp = "";
        temp += str1[i];
        temp += str1[i+1];
        s1.push_back(temp);
    }
    
    // str2 처리
    for(int i=0; i<str2.length(); i++){
        if(str2[i] <65 || str2[i] > 90 || str2[i+1] <65 || str2[i+1] > 90)
            continue;
        string temp = "";
        temp += str2[i];
        temp += str2[i+1];
        s2.push_back(temp);
    }
    
    sort(s1.begin(), s1.end());
    sort(s2.begin(), s2.end());
    
    int sumS = s1.size() + s2.size();
    int interS=0;
    
    // 교집합 크기 찾기
    for(int i=0; i<s1.size(); i++){
        auto it = find(s2.begin(), s2.end(), s1[i]);
        //겹치는거 찾은 경우
        if(it != s2.end()){
            interS++;
            s2.erase(it);
        }
    }
    
    // 합집합 크기
    sumS-=interS;
    
    // 둘다 공집합인 경우
    if(sumS == 0)
        answer = 1 * 65536;
    else{
        double result = ((double)interS / (double)sumS) * 65536;
        answer = (int)result;
    }
    
    return answer;
}