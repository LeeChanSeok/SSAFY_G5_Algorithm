#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(){
    ios_base::sync_with_stdio(0); 
    cin.tie(0); 
    cout.tie(0); 

    vector<int> vec;

    int n, input;
    int start, end;
    int ans = 0;

    cin >> n;

    for(int i = 0 ; i < n ; i++) {
        cin >> input;
        vec.push_back(input);
    }

    if(n <= 2) {
        cout << 0;
        return 0;
    }

    sort(vec.begin(), vec.end());

    for(int i = 0 ; i < n ; i++) { 
        vector<int> cp = vec;
        cp.erase(cp.begin() + i);
        
        start = 0; end = n - 2;

        while(start < end) {
            int sum = cp[start] + cp[end];

            if(sum == vec[i]) {
                ans++;
                break;
            }
            if(sum < vec[i]) 
                start++;
            else 
                end--;
        }
    }
    cout << ans;

    return 0;
}