#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_map>

using namespace std;

// ����, Ƚ��
unordered_map<string, int> combination[11];

// �ߺ���
int maxCnt[11];

// ����
void comb(string str, int cnt, string result) {
	
	// ���� ����
	if (cnt == str.size()) {
		// �޴� ����, result : �ϼ��� �޴�
		int num = result.size();
		combination[num][result]++;

		// ���� �޴��� �ִ밪�� �ִ밪����
		maxCnt[num] = max(maxCnt[num], combination[num][result]);
		return;
	}

	// ����
	// selected
	comb(str, cnt + 1, result + str[cnt]);
	// un selected
	comb(str, cnt + 1, result);
}

// �ַ�� - ���� ����
vector<string> solution(vector <string> orders, vector<int> course) {
	vector<string> answer;
	
	// ���� ����
	for (auto order : orders) {
		// ������ ������������ �ؾ��ϴϱ� ���� ex : ABCD
		sort(order.begin(), order.end());
		// ���� ����
		comb(order, 0, "");
	}

	// �ڽ��� �����Ҹ�����
	for (auto num : course) {
		for (auto target : combination[num]) {
			// 2���̻��� �մ��� �ֹ��ߴ���
			if (target.second == maxCnt[num] && target.second >= 2) {
				answer.push_back(target.first);
			}
		}
	}

	// ����� ��������
	sort(answer.begin(), answer.end());

	return answer;
}