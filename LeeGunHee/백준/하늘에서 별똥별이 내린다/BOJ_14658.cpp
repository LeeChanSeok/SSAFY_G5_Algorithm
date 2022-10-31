#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false); 
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m, l, k;

    cin >> n >> m >> l >> k;

    int y, x, ny, nx, ans = 0, cnt = 0;

    vector<pair<int,int>> v(k);

    for(int i = 0; i < k; i++){
        cin >> x >> y;
        v[i] = {x, y};
    }
    for(int i = 0; i < k; i++){
        for(int j = 0; j < k; j++){
            cnt = 0;
            x = v[i].first;
            y = v[j].second;
            for(int a = 0; a < k; a++){
                nx = v[a].first;
                ny = v[a].second;

                if(x <= nx && x+l >= nx && y< =ny && y+l >= ny) 
                	cnt++;
            }
            ans = max(ans,cnt);
        }
    }
    cout << k-ans << "\n";
}