import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        String[] answer = {};
        
        Map<String, String> nickName = new HashMap<>();
        int recordSize = record.length;
        String[][] EL = new String[recordSize][2];
        
        int recordCnt = 0;
        String[] rec;
        char c;
        for(int i = 0; i < recordSize; ++i){
            rec = record[i].split(" ");
            c = rec[0].charAt(0);
            if(c == 'E'){
                EL[recordCnt][0] = "E";
                EL[recordCnt][1] = rec[1];
                ++recordCnt;
                nickName.put(rec[1], rec[2]);
            }else if(c == 'L'){
                EL[recordCnt][0] = "L";
                EL[recordCnt][1] = rec[1];
                ++recordCnt;
            }else{
                nickName.put(rec[1], rec[2]);    
            }
            
        }
        
        String nick;
        answer = new String[recordCnt];
        for(int i = 0; i < recordCnt; ++i){
            nick = nickName.get(EL[i][1]);
            if(EL[i][0] == "E"){
                answer[i] = nick +"님이 들어왔습니다.";
            }else{
                answer[i] = nick +"님이 나갔습니다.";
            }
        }
    
        return answer;
    }
}
