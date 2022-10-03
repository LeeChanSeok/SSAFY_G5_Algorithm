#include <iostream>
#include <string>
#include <algorithm>
#include <stack>
#include <vector>

using namespace std;

// 정상 괄호 체크
bool check(string str) {
	stack<char> s;

	for (auto c : str) {
		if (c == '(') {
			s.push(c);
		}
		else {
			if (s.empty()) {
				return false;
			}
			s.pop();
		}
	}

	return s.empty();
}

string solution(string p) {
	if (p == "") 
		return p;

	int cnt = 0;

	string u, v;

	for (int i = 0; i < p.size(); i++) {
		if (p[i] == '(') 
			cnt++;
		else 
			cnt--;

		if (cnt == 0) {
			u = p.substr(0, i + 1);
			v = p.substr(i + 1);

			break;
		}
	}

	if (check(u)) return u + solution(v);

	string ret = "(" + solution(v) + ")";
	for (int i = 1; i < u.size() - 1; i++)
		ret += (u[i] == '(' ? ")" : "(");

	return ret;
}