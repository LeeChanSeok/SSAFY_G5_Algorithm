#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <map>

using namespace std;

vector<int> solution(vector<string> gems) {
    vector<int> answer = {0, 0};
    queue<string> q;
    map<string, int> m;
    int gems_size = 0, start = 0, end = 1000001, tmp = 0;
    
    for(auto i : gems)
        m[i] = 1;
    
    gems_size = m.size();
    m.clear();
    
    for(int i = 0; i < gems.size(); i++)    {
        if(m[gems[i]] == 0)
            m[gems[i]] = 1;
        else
            m[gems[i]] += 1;
        q.push(gems[i]);
        
        while(1)        {
            if(m[q.front()] > 1)            {
                m[q.front()] -= 1;
                q.pop();
                tmp++;
            }
            else
                break;
        }
        
        if(m.size() == gems_size && end > q.size())        {
            end = q.size();
            start = tmp;
        }
    }

    answer[0] = start + 1;
    answer[1] = start + end;
    return answer;
}