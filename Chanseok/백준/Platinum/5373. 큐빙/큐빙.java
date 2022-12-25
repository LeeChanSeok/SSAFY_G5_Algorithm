import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

  	static int n = 3;
	static final int R = n*4, C = n*3;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		char[][] cuve = new char[R][C];
		
		int N = Integer.parseInt(br.readLine());
		String[] commands;
		int K;
		for (int tc = 1; tc <= N; ++tc) {
			init_cuve(cuve);
			
			K = Integer.parseInt(br.readLine());
			commands = new String[K];
			commands = br.readLine().split(" ");

			for (String command : commands) {
				rotate(command, cuve);
			}

			record(sb, cuve);

		}
		bw.write(sb.toString());
		bw.close();

	}

	private static void rotate(String command, char[][] cuve) {

		if (command.equals("U-"))		Uminus(cuve);
		else if (command.equals("U+"))	Uplus(cuve);
		else if (command.equals("D-"))	Dminus(cuve);
		else if (command.equals("D+"))	Dplus(cuve);
		else if (command.equals("F-"))	Fminus(cuve);
		else if (command.equals("F+"))	Fplus(cuve);
		else if (command.equals("B-"))	Bminus(cuve);
		else if (command.equals("B+"))	Bplus(cuve);
		else if (command.equals("L-"))	Lminus(cuve);
		else if (command.equals("L+"))	Lplus(cuve);
		else if (command.equals("R-"))	Rminus(cuve);
		else if (command.equals("R+"))	Rplus(cuve);
			
	}

  	private static void clock_rotate(char[][] cuve, int r, int c) {

		char[][] rotate = new char[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				rotate[j][n - 1 - i] = cuve[r + i][c + j];
			}
		}
		
		copyArray(cuve, rotate, r, c);

	}
	
	private static void anticlock_rotate(char[][] cuve, int r, int c) {

		char[][] rotate = new char[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				rotate[n - 1 - j][i] = cuve[r + i][c + j];
			}
		}
		
		copyArray(cuve, rotate, r, c);

	}

	private static void copyArray(char[][] cuve, char[][] rotate, int r, int c) {
		
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				cuve[r+i][c+j] = rotate[i][j];
			}
		}
		
	}

	private static void record(StringBuilder sb, char[][] cuve) {
		for (int i = n; i < n*2; ++i) {
			for (int j = n; j < n*2; ++j) {
				sb.append(cuve[i][j]);
			}
			sb.append("\n");
		}
	}

	private static void init_cuve(char[][] cuve) {

		// U : 윗 면(w)
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j)
				cuve[i+n][j+n] = 'w';
		}
		// D : 아랫 면(y)
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j)
				cuve[i+3*n][j+n] = 'y';
		}
		// F : 앞 면(r)
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j)
				cuve[i+2*n][j+n] = 'r';
		}
		// B : 뒷 면(o)
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j)
				cuve[i][j+n] = 'o';
		}
		// L : 왼쪽 면(g)
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j)
				cuve[i+n][j] = 'g';
		}
		// R : 오른쪽 면(b)
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j)
				cuve[i+n][j+2*n] = 'b';
		}
	}
  
  //U-
	private static void Uminus(char[][] cuve) {
		anticlock_rotate(cuve, n, n);
		
		int r = n-1, c = n-1, k = n+2;
		char[][] rotate = new char[k][k];
		for(int i = 0; i < k; ++i) {
			for(int j = 0; j < k; ++j) {
				rotate[k - 1 - j][i] = cuve[r + i][c + j];
			}
		}
		
		for(int i = r; i < r + k; ++i) {
			cuve[r][i] = rotate[0][i - r];
			cuve[r+k-1][i] = rotate[k-1][i - r];
			cuve[i][r] = rotate[i - r][0];
			cuve[i][r+k-1] = rotate[i - r][k-1];
		}
	}
	
  //U+
	private static void Uplus(char[][] cuve) {
		clock_rotate(cuve, n, n);
		
		int r = n-1, c = n-1, k = n+2;
		char[][] rotate = new char[k][k];
		for(int i = 0; i < k; ++i) {
			for(int j = 0; j < k; ++j) {
				rotate[j][k  - 1 - i] = cuve[r + i][c + j];
			}
		}
		
		for(int i = r; i < r + k; ++i) {
			cuve[r][i] = rotate[0][i - r];
			cuve[r+k-1][i] = rotate[k-1][i - r];
			cuve[i][r] = rotate[i - r][0];
			cuve[i][r+k-1] = rotate[i - r][k-1];
		}

	}

  //D-
	private static void Dminus(char[][] cuve) {
		anticlock_rotate(cuve, 3*n, n);
		
		int r = 0, c = 0, k = 3*n;
		char[][] rotate = new char[k][k];

		for(int i = 0; i < k; ++i) {
			for(int j = 0; j < k; ++j) {
				rotate[j][k  - 1 - i] = cuve[r + i][c + j];
			}
		}
		
		for(int i = r; i < r + k; ++i) {
			cuve[r][i] = rotate[0][i - r];
			cuve[r+k-1][i] = rotate[k-1][i - r];
			cuve[i][r] = rotate[i - r][0];
			cuve[i][r+k-1] = rotate[i - r][k-1];
		}
	}
	
  //D+
	private static void Dplus(char[][] cuve) {
		clock_rotate(cuve, 3*n, n);
		
		int r = 0, c = 0, k = 3*n;
		char[][] rotate = new char[k][k];
		for(int i = 0; i < k; ++i) {
			for(int j = 0; j < k; ++j) {
				rotate[k - 1 - j][i] = cuve[r + i][c + j];
			}
		}
		
		for(int i = r; i < r + k; ++i) {
			cuve[r][i] = rotate[0][i - r];
			cuve[r+k-1][i] = rotate[k-1][i - r];
			cuve[i][r] = rotate[i - r][0];
			cuve[i][r+k-1] = rotate[i - r][k-1];
		}

	}
	
  //F-
	private static void Fminus(char[][] cuve) {
		anticlock_rotate(cuve, 2*n, n);
		
		int r1 = 2*n-1, r2 = 3*n;
		
		char[] cArr = new char[n];
		for(int i = 0; i < n; ++i) {
			cArr[i] = cuve[r2][i+n];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[r2][r1-i] = cuve[r1][i];
		}
		
		for(int i = 0; i < 2*n; ++i) {
			cuve[r1][i] = cuve[r1][i+n];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[r1][2*n+i] = cArr[n-1-i];
		}
		
	}
	
  //F+
	private static void Fplus(char[][] cuve) {
		clock_rotate(cuve, 2*n, n);
		
		int r1 = 2*n-1, r2 = 3*n;
		
		char[] cArr = new char[n];
		for(int i = 0; i < n; ++i) {
			cArr[i] = cuve[r2][i+n];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[r2][r1-i] = cuve[r1][2*n+i];
		}
		
		for(int i = r2-1; i >= n; --i) {
			cuve[r1][i] = cuve[r1][i-n];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[r1][i] = cArr[n-1-i];
		}

	}

  //B-
	private static void Bminus(char[][] cuve) {
		anticlock_rotate(cuve, 0, n);
		
		int r1 = n, r2 = 4*n-1;
		
		char[] cArr = new char[n];
		for(int i = 0; i < n; ++i) {
			cArr[i] = cuve[r2][i+n];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[r2][2*n-1 - i] = cuve[r1][2*n+i];
		}
		
		for(int i = 3*n-1; i >= n; --i) {
			cuve[r1][i] = cuve[r1][i-n];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[r1][i] = cArr[n-1-i];
		}
		
	}
  
	//B+
	private static void Bplus(char[][] cuve) {
		clock_rotate(cuve, 0, n);
		
		int r1 = n, r2 = 4*n-1;
		
		char[] cArr = new char[n];
		for(int i = 0; i < n; ++i) {
			cArr[i] = cuve[r2][i+n];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[r2][2*n-1-i] = cuve[r1][i];
		}
		
		for(int i = 0; i < 2*n; ++i) {
			cuve[r1][i] = cuve[r1][i+n];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[r1][2*n+i] = cArr[n-1-i];
		}

	}
	
  //L-
	private static void Lminus(char[][] cuve) {
		anticlock_rotate(cuve, n, 0);
		
		int r1 = n, r2 = 0;
		
		char[] cArr = new char[n];
		for(int i = 0; i < n; ++i) {
			cArr[i] = cuve[i][n];
		}
		
		for(int i = 0; i < 3*n; ++i) {
			cuve[i][r1] = cuve[i+n][r1];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[3*n+i][r1] = cArr[i];
		}
		
	}
	
  //L+
	private static void Lplus(char[][] cuve) {
		clock_rotate(cuve, n, 0);
		
		int r1 = n;
		
		char[] cArr = new char[n];
		for(int i = 0; i < n; ++i) {
			cArr[i] = cuve[i + 3*n][n];
		}
		
		for(int i = 4*n-1; i >= n ; --i) {
			cuve[i][r1] = cuve[i-n][r1];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[i][r1] = cArr[i];
		}

	}
	
  //R-
	private static void Rminus(char[][] cuve) {
		anticlock_rotate(cuve, n, 2*n);
		
		int r1 = 2*n - 1;
		
		char[] cArr = new char[n];
		for(int i = 0; i < n; ++i) {
			cArr[i] = cuve[i + 3*n][r1];
		}
		
		for(int i = 4*n-1; i >= n ; --i) {
			cuve[i][r1] = cuve[i-n][r1];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[i][r1] = cArr[i];
		}
	}
	
	private static void Rplus(char[][] cuve) {
		clock_rotate(cuve, n, 2*n);
		
		int r1 = 2*n - 1;
		
		char[] cArr = new char[n];
		for(int i = 0; i < n; ++i) {
			cArr[i] = cuve[i][r1];
		}
		
		for(int i = 0; i < 3*n; ++i) {
			cuve[i][r1] = cuve[i+n][r1];
		}
		
		for(int i = 0; i < n; ++i) {
			cuve[3*n+i][r1] = cArr[i];
		}
	}
	
}
