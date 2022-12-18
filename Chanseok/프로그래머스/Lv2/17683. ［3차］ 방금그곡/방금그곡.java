class Solution {

    public String solution(String m, String[] musicinfos) {
        String answer = "";

        String heard = changeMusic(m);

        String[] musicinfo;
        String start, end, title, music;
        int time, LongTime = 0, musicLen;
        for(int i = 0, len= musicinfos.length; i < len; ++i) {
            musicinfo = musicinfos[i].split(",");
            start = musicinfo[0];
            end = musicinfo[1];
            title = musicinfo[2];
            music = changeMusic(musicinfo[3]);
            musicLen = music.length();
            
            time = time2min(end) - time2min(start);
            
            if(music.length() > time) music = music.substring(0, time);
            else {
                 
                int repeat = time / musicLen-1;
                int rest = time % musicLen;
                while(repeat-- > 0) {
                    music += music.substring(0, musicLen);
                }
                music += music.substring(0, rest);
            }
            if(music.contains(heard)) {
                if(time > LongTime) {
                    LongTime = time;
                    answer = title;
                }
            }
        }
        if(answer == "") answer = "(None)";
        return answer;
    }

    private boolean isMatch(String heard, String music, int time) {

        int heardLen = heard.length();
        int musicLen = music.length();

        int j, k;
        for (int i = 0; i < musicLen; ++i) {
            k = i;

            for (j = 0; j < heardLen; ++j) {
                if (music.charAt(k) != heard.charAt(j))
                    break;
                k = (++k) % musicLen;
            }

            if(j == heardLen) return true;

        }

        return false;
    }

    private String changeMusic(String m) {
        int delta = 'a' - 'A';
        String n = "";
        char c;
        for(int i = 0, len = m.length(); i < len; ++i) {
            c = m.charAt(i);
            if(i != len-1 && m.charAt(i+1) == '#') {
                n += (char)(delta + c);
                ++i;
            }else {
                n += c;
            }
        }
        return n;
    }


    public int time2min(String time) {
        String[] HHMM = time.split(":");
        return Integer.parseInt(HHMM[0]) * 60 + Integer.parseInt(HHMM[1]);
    }
}
