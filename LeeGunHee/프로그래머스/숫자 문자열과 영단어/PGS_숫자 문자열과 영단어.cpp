#include <string>
#include <vector>
#include <map>

using namespace std;

map<string, int> m;

int solution(string s) {
    string answer = "";
    
    m["zero"] = 0;
    m["one"] = 1;
    m["two"] = 2;
    m["three"] = 3;
    m["four"] = 4;
    m["five"] = 5;
    m["six"] = 6;
    m["seven"] = 7;
    m["eight"] = 8;
    m["nine"] = 9;
    
    string tmp = "";

    for(char c: s) {
        if(isdigit(c)) answer += c;
        else tmp += c;
        
        if(m.find(tmp) != m.end()) {
            answer += to_string(m[tmp]);
            tmp = "";
        }
    }
    
    return stoi(answer);
}