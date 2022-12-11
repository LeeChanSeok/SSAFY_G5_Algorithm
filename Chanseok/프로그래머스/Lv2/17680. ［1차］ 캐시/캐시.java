import java.util.LinkedHashSet;	

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        LinkedHashSet<String> cache = new LinkedHashSet<>();

        if(cacheSize == 0) return 5 * cities.length;

        for(String city : cities) {
            city = city.toLowerCase();

            if(cache.contains(city)) {
                ++answer;
                cache.remove(city);
                cache.add(city);
            }else {
                if(cache.size() != cacheSize) {
                    cache.add(city);
                }else {
                    cache.remove(cache.iterator().next());
                    cache.add(city);
                }
                answer += 5;
            }
        }


        return answer;
    }
}
