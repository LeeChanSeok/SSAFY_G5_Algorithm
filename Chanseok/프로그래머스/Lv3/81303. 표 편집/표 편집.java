import java.util.Stack;

class Solution {
    class Node{
        boolean isDel;
        int idx, up, down;

        public Node(int idx, int up, int down) {
            super();
            this.idx = idx;
            this.up = up;
            this.down = down;
        }

    }
		
    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();

        Node[] table = new Node[n];
        for(int i = 0; i < n ; ++i) {
            table[i] = new Node(i, i-1, i+1);
        }
        table[n-1].down = -1; 

        Stack<Integer> history = new Stack<>();
        char c; 
        int number, idx, up, down;
        for(String command : cmd) {
            c = command.charAt(0);

            if(c == 'U') {
                number = Integer.parseInt(command.split(" ")[1]);
                for(int i = 0; i < number; ++i)
                    k = table[k].up;
            }else if(c == 'D') {
                number = Integer.parseInt(command.split(" ")[1]);
                for(int i = 0; i < number; ++i)
                    k = table[k].down;
            }else if(c == 'C') {

                history.add(k);
                table[k].isDel = true;

                up = table[k].up; down = table[k].down;

                if(table[k].up == -1) {
                    table[down].up = table[k].up;
                    k = table[k].down;
                }
                else if(table[k].down == -1) {
                    table[up].down = table[k].down;

                    k = table[k].up;
                } else {
                    table[up].down = table[k].down;
                    table[down].up = table[k].up;	
                    k = table[k].down;		        		
                }


            }else if(c == 'Z') {
                idx = history.pop();
                table[idx].isDel = false;
                up = table[idx].up; down = table[idx].down;

                if(table[idx].up == -1) {
                    table[down].up = idx;
                }
                else if(table[idx].down == -1) {
                    table[up].down = idx;
                } else {
                    table[up].down = idx;
                    table[down].up = idx;	
                }

                if(idx <= k) {
                    k = table[k].idx;
                }

            }
        }


        for(int i = 0; i < n; ++i) {
            if(table[i].isDel) sb.append("X");
            else sb.append("O");
        }

        return sb.toString();
    }
}
