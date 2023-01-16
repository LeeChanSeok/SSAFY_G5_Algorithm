#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool bs(vector<int> stones, int mid, int k){
    int count = 0;
    
    for(int i = 0; i < stones.size(); i++)    {
        if(stones[i] - mid <= 0)
            count++;
        else
            count = 0;
        
        if(count >= k)
            return true;
    }
    return false;
}

int solution(vector<int> stones, int k) {
    int answer = 0;
    int front = 1, back = *max_element(stones.begin(), stones.end());
    
    while(front <= back)    {
        int mid = (front + back) / 2;
        
        if(bs(stones, mid, k))
            back = mid - 1;
        else
            front = mid + 1;
    }
    return front;
}