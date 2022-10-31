#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int n, vector<int> weak, vector<int> dist) {
    int answer = 1e9;
    int len = weak.size();
    
    for(size_t i = 0; i < len - 1; i++)
        weak.emplace_back(weak[i] + n);
    
    do{
        for(int i = 0; i < len; i++){
            int idx = 0;
            bool flag = true;
            int cur = dist[idx] + weak[i];
            
            for(size_t j = i + 1; j < len + i; j++){
                if(cur < weak[j]){
                    if(idx + 1 == dist.size()) {
                        flag = false;
                        break;
                    }
                    else
                        cur = weak[j] + dist[++idx];   
                }
            }
            if(flag)
                answer = min(answer, idx + 1);  
        }
    }while(next_permutation(dist.begin(), dist.end()));
    
    if(answer == 99999) 
	return -1;
   
    return answer;
}