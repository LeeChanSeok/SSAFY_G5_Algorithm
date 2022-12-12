#include <string>
#include <vector>
#include <iostream>

using namespace std;
 
int n1[16]={0, };
int n2[16]={0, };
 
 
vector<string> solution(int n, vector<int> arr1, vector<int> arr2) {
    vector<string> answer;

    for(int i = 0; i < n; i++){ 1        
        int idx = n - 1;
        while(arr1[i] != 0) {
            n1[idx--] = (arr1[i] % 2);
            arr1[i] /= 2;
        }
        idx = n-1;
        while(arr2[i] != 0){
            n2[idx--] = (arr2[i] % 2);
            arr2[i] /= 2;
        }
        
        string str;

        for(int i = 0; i < n; i++) {
            if(n1[i] || n2[i]) str += "#";
            else str += " ";
        }
        for(int i = 0; i < n; i++){
            n1[i] = 0;
            n2[i] = 0;
        }
        answer.push_back(str);
    }
    
    return answer;
}