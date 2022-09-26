import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x, y;

    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Point point = (Point)obj;
        if(this.x == point.x && this.y == point.y) return true;
        return false;
    }

}

class Card{
    Point point1, point2;

    public Card() {
        super();
    }

}

class Solution {
    static int min, cardCnt;
    static int[] order;
    static Card[] cards;
    static int[][] Board;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board, int r, int c) {
        int INF = Integer.MAX_VALUE;
        int answer = INF;
        Board = board;

        // 카드의 번호가 1번부터 6번까지 최대 6개 이므로  Card 타입 배열 선언
        cards = new Card[7];
        for(int i = 0; i < 7; i++) cards[i] = new Card();

        // 카드 정보 저장
        cardCnt = 0;
        int num;
        for(int i = 0; i <4; i++) {
            for(int j = 0; j < 4; j++) {
                num = board[i][j];
                if(num == 0) continue;
                if(cards[num].point1 == null) cards[num].point1 = new Point(i, j);
                else cards[num].point2 = new Point(i, j);
                ++cardCnt;
            }
        }

        cardCnt = cardCnt>>1;

        // 탐색할 카드 순서를 저장
        order = new int[cardCnt];
        for(int i = 0; i < cardCnt; ++i) order[i] = i+1;

        min = INF;
        // 모든 순열에 대해서 탐색하는데 걸리는 최소 횟수 구하기
        do {
            dfs(0, new Point(r, c), 0);
        }while(nextPerm(order, cardCnt));

        return min + 2 * cardCnt;
    }

    private void dfs(int cnt, Point point, int length) {
        if(length >= min) return;

        if(cnt == cardCnt) {
            min = length;
            return;
        }

        Card card = cards[order[cnt]];

			// 1. 두 점 사이의 최단 거리를 구한다.
			int dist = calcMinDist(point, card.point1) + calcMinDist(card.point1, card.point2);
			int dist2 = calcMinDist(point, card.point2) + calcMinDist(card.point2, card.point1);
			
			Board[card.point1.x][card.point1.y] = Board[card.point2.x][card.point2.y] = 0;
			
			// 2-1. 현재 위치와 point1과의 최단 거리를 구한다.			
			dfs(cnt + 1, card.point2, length + dist);
			
			// 2-2. 현재 위치와 point2의 최단 거리를 구한다.
			dfs(cnt + 1, card.point1, length + dist2);
			
			Board[card.point1.x][card.point1.y] = Board[card.point2.x][card.point2.y] = order[cnt];
    }


    private int calcMinDist(Point from, Point to) {

        int[][] board = Board;

        int qSize;
        int cnt = 0;
        int x, y, nx, ny;
        Point point;

        int visit = 0;
        int next = (from.x*4 + from.y);
        visit |= 1 << next;

        Queue<Point> q = new LinkedList<>();
        q.offer(from);

        while(!q.isEmpty()) {

            qSize = q.size();
            while(qSize-- > 0) {
                point = q.poll();
                if(point.equals(to)) return cnt;

                x = point.x; 
                y = point.y;

                // cntl 이동 - 상하좌우
                for(int d = 0; d < 4; ++d) {
                    nx = x ; ny =  y;
                    do{
                        nx += dx[d];
                        ny += dy[d];

                        if(nx < 0 || nx >=4 || ny < 0 || ny >=4) {
                            nx -= dx[d];
                            ny -= dy[d];
                            break;
                        }

                        if(board[nx][ny] != 0) break;

                    }while(true);

                    next = 1 << (nx*4 + ny);
                    // 방문 여부 확인
                    if((visit & next) != 0) continue;

                    q.offer(new Point(nx, ny));
                    visit |= next;
                }

                // 기본 이동 - 상하좌우
                for(int d = 0; d < 4; ++d) {
                    nx = x + dx[d];
                    ny = y + dy[d];

                    // 경계 밖 확인
                    if(nx < 0 || nx >= 4 || ny < 0 || ny >=4) continue;
                    next = 1 << (nx*4 + ny);
                    // 방문 여부 확인
                    if((visit & next) != 0) continue;

                    q.offer(new Point(nx, ny));
                    visit |= next;
                }

            }

            ++cnt;
        }

        return 0;
    }

    public boolean nextPerm(int[] order, int N) {

        int i = N-1;
        while(i > 0 && order[i-1] >= order[i]) --i;
        if(i == 0) return false;


        int j = N - 1;
        while(order[i-1] >= order[j]) --j;
        swap(order, i-1, j);

        int k = N - 1;
        while(i < k) {
            swap(order, i, k);
            i++; k--;
        }

        return true;
    }

    private void swap(int[] order, int i, int j) {
        int temp = order[i];
        order[i] = order[j];
        order[j] = temp;
    }
}
