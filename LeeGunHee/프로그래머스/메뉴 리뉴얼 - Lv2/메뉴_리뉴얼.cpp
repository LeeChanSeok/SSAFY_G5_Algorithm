#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_map>

using namespace std;

// 음식, 횟수
unordered_map<string, int> combination[11];

// 중복값
int maxCnt[11];

// 조합
void comb(string str, int cnt, string result) {
	
	// 종료 조건
	if (cnt == str.size()) {
		// 메뉴 개수, result : 완성된 메뉴
		int num = result.size();
		combination[num][result]++;

		// 과연 메뉴의 최대값이 최대값인지
		maxCnt[num] = max(maxCnt[num], combination[num][result]);
		return;
	}

	// 조합
	// selected
	comb(str, cnt + 1, result + str[cnt]);
	// un selected
	comb(str, cnt + 1, result);
}

// 솔루션 - 벡터 리턴
vector<string> solution(vector <string> orders, vector<int> course) {
	vector<string> answer;
	
	// 조합 생성
	for (auto order : orders) {
		// 조합을 오름차순으로 해야하니까 정렬 ex : ABCD
		sort(order.begin(), order.end());
		// 조합 생성
		comb(order, 0, "");
	}

	// 코스에 선택할만한지
	for (auto num : course) {
		for (auto target : combination[num]) {
			// 2명이상의 손님이 주문했는지
			if (target.second == maxCnt[num] && target.second >= 2) {
				answer.push_back(target.first);
			}
		}
	}

	// 결과도 오름차순
	sort(answer.begin(), answer.end());

	return answer;
}