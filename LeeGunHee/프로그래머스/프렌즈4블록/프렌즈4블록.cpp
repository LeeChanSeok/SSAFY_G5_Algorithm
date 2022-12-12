#include <string>
#include <vector>

using namespace std;

int solution(int m, int n, vector<string> board) {
    int answer = 0, i = 0, j = 0;
    bool keep = true;
    
    while(keep)
    {
        bool arr[30][30] = {false, };
        keep = false;
        
        for(i = 0; i < m - 1; i++)
        {
            for(j = 0; j < n - 1; j++)
            {
                if(board[i][j] == ' ')
                    continue;
                char target = board[i][j];
                
                if(board[i][j + 1] == target && board[i + 1][j] == target && board[i + 1][j + 1] == target)
                {
                    arr[i][j] = true;
                    arr[i][j + 1] = true;
                    arr[i + 1][j] = true;
                    arr[i + 1][j + 1] = true;
                    keep = true;
                }
            }
        }
        
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(arr[i][j] == true)
                {
                    answer++;
                    
                    for(int k = i - 1; k >= 0; k--)
                    {
                        board[k + 1][j] = board[k][j];
                        board[k][j] = ' ';
                    }
                }
            }
        }
    }
    
    return answer;
}