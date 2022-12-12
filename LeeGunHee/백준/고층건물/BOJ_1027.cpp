#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    double building[50];
    int n;

    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> building[i];
    }

    int ans = 0;

    for (int i = 0; i < n; i++) {
        int num = 0;
        double ra = -9999999999;
        
        // 오른쪽
        for (int j = i + 1; j < n; j++) {
            double minH = building[i] + ra * (j - i);
            if (building[j] > minH) {
                num++;
                ra = (building[j] - building[i]) / (j - i);
            }
        }
        ra = -9999999999;
        // 왼쪽
        for (int j = i - 1; j >= 0; j--) {
            double minH = building[i] + ra * (i - j);
            if (building[j] > minH) {
                num++;
                ra = (building[j] - building[i]) / (i - j);
            }
        }
        ans = max(ans, num);
    }
    cout << ans << endl;

    return 0;
}