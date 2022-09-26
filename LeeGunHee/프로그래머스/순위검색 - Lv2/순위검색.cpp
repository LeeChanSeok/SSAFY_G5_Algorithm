#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <map>
#include <sstream>

using namespace std;

vector<int> solution(vector<string> info, vector<string> query) {
    vector<int> answer(query.size(), 0);
    map<string, vector<int>> m;

    for (int i = 0; i < info.size(); i++) {
        string token;
        stringstream ss(info[i]);
        vector<vector<string>> str(4, vector<string>(2, "-"));
        int index = 0, score = 0;

        while (ss >> token) {
            if (index < 4) {
                str[index++][0] = token;
            }
            else {
                score = stoi(token);
            }
        }

        for (int i = 0; i < 2; i++) {
            string t1, t2, t3, t4;
            t1 = str[0][i];
            for (int j = 0; j < 2; j++) {
                t2 = str[1][j];
                for (int k = 0; k < 2; k++) {
                    t3 = str[2][k];
                    for (int l = 0; l < 2; l++) {
                        t4 = str[3][l];
                        m[t1 + t2 + t3 + t4].push_back(score);
                    }
                }
            }
        }
    }

    for (auto itr = m.begin(); itr != m.end(); itr++) {
        sort(itr->second.begin(), itr->second.end());
    }

    for (int i = 0; i < query.size(); i++) {
        string str = "", token;
        stringstream ss(query[i]);
        int index = 0, score = 0;

        while (ss >> token) {
            if (token == "and") {
                continue;
            }

            if (index++ < 4) {
                str += token;
            }
            else {
                score = stoi(token);
            }
        }

        auto itr = lower_bound(m[str].begin(), m[str].end(), score);

        answer[i] = m[str].size() - (itr - m[str].begin());
    }

    return answer;
}