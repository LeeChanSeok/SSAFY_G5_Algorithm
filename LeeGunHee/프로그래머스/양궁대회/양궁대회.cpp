#include <string>
#include <vector>
#include <algorithm>
 
using namespace std;
 
int arrows, maxDiff, maxs = 11;

vector<int> apeachs;
vector<int> scores = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
vector<vector<int>> answers;
 
// ºñ±³ ÇÔ¼ö
bool compare(vector<int> a, vector<int> b) {
	for (int i = a.size() - 1; i >= 0; i--) {
		if (a[i] > b[i])
			return true;
		else if (a[i] < b[i])
			return false;
	}
}
 
// ±íÀÌ Å½»ö
void dfs(int n, int pos, vector<int> ryans) {
	if (n == arrows) {
		int apeach = 0, ryan = 0;
		for (int i = 0; i < maxs; i++) {
			if (apeachs[i] == 0 && ryans[i] == 0)
				continue;
 
			if (apeachs[i] < ryans[i]) {
				ryan += scores[i];
			}
			else {
				apeach += scores[i];
			}
		}
 
		if (ryan <= apeach)
			return;
 
		if (maxDiff < ryan - apeach) {
			answers.clear();
			answers.push_back(ryans);
 
			maxDiff = ryan - apeach;
		}
		else if (ryan - apeach == maxDiff) {
			answers.push_back(ryans);
		}
 
		return;
	}
 
	for (int i = pos; i < maxs; i++) {
		ryans[i]++;
		dfs(n + 1, i, ryans);
		ryans[i]--;
	}
}
 
vector<int> solution(int n, vector<int> info) {
	vector<int> ryans(11);
	arrows = n;
	apeachs = info;
	dfs(0, 0, ryans);
 
	if (answers.size() == 0) {
		answers.push_back({ -1 });
	}
 
	if (answers.size() != 1) {
		sort(answers.begin(), answers.end(), compare);
	}
 
	return answers.front();
}