package gitsimulator;

public class Diff extends GitObject{
	

    char[] input1;
    char[] input2;
    int M;  //input1的length;
    int N;  //input2的length
    
    public Diff(String FileName1, String FileName2) throws Exception {
    	input1 = (super.GetValue(FileName1)).toCharArray();
    	input2 = (super.GetValue(FileName2)).toCharArray();
    	M = input1.length;
    	N = input2.length;
    }

    public void run() {
        int[][] opt = lcsLength(input1, input2);
        printDiff(opt, input1, input2, M-1, N-1);
    }

    public int[][] lcsLength(char[] input1, char[] input2) {
        int[][] opt = new int[M][N];
        for (int i = 1; i < input1.length; i++) {
            for (int j = 1; j < input2.length; j++) {
                if (input1[i] == input2[j]) {
                    opt[i][j] = opt[i - 1][j - 1] + 1;
                } else {
                    opt[i][j] = Math.max(opt[i][j - 1], opt[i - 1][j]);
                }
            }
        }
        return opt;
    }

    public void printDiff(int opt[][], char input1[], char input2[], int i,
            int j) {
        if ((i >= 0) && (j >= 0) && (input1[i] == input2[j])) {
            printDiff(opt, input1, input2, i - 1, j - 1);
            System.out.print("  " + input1[i] + "\n");
        } else if ((j > 0) && ((i == 0) || (opt[i][j - 1] >= opt[i - 1][j]))) {
            printDiff(opt, input1, input2, i, j - 1);
            System.out.print(" +" + input2[j] + "\n");
        } else if ((i > 0) && ((j == 0) || (opt[i][j - 1] < opt[i - 1][j]))) {
            printDiff(opt, input1, input2, i - 1, j);
            System.out.print(" -" + input1[i] + "\n");
        } else {
            System.out.print("");
        }
    }
}
