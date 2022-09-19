class SudokuSolver {

    int SUDOKU_SIZE = 9;          // Size of the grid.
    int SUDOKU_MIN_NUMBER = 1;    // Minimum digit to be filled in.
    int SUDOKU_MAX_NUMBER = 9;    // Maximum digit to be filled in.
    int SUDOKU_BOX_DIMENSION = 3; // Dimension of the boxes (sub-grids that should contain all digits).

    int[][] lastSolutionGrid = new int[SUDOKU_SIZE][SUDOKU_SIZE];

    int[][] grid = new int[][] {  // The puzzle grid; 0 represents empty.
        { 0, 9, 0,   7, 3, 0,    4, 0, 0 },    // One solution.
        { 0, 0, 0,   0, 0, 0,    5, 0, 0 },
        { 3, 0, 0,   0, 0, 6,    0, 0, 0 },

        { 0, 0, 0,   0, 0, 2,    6, 4, 0 },
        { 0, 0, 0,   6, 5, 1,    0, 0, 0 },
        { 0, 0, 6,   9, 0, 7,    0, 0, 0 },

        { 5, 8, 0,   0, 0, 0,    0, 0, 0 },
        { 9, 0, 0,   0, 0, 3,    0, 2, 5 },
        { 6, 0, 3,   0, 0, 0,    8, 0, 0 },
    };

     int solutionCounter = 0; // Solution counter

     // Find all solutions for the grid, and stores the final solution.
     void solve() {
        int[] cel = findEmptySquare();
        if(cel != null){
            for(int i = SUDOKU_MIN_NUMBER; i <= SUDOKU_MAX_NUMBER; i++){
                if(!givesConflict(cel[0], cel[1], i)) {
                    grid[cel[0]][cel[1]] = i;
                    solve();
                    grid[cel[0]][cel[1]] = 0;
                } 
            } 
        } else {
            if(!asteriskConflict()){
                solutionCounter++;
                lastSolutionGrid = copy(grid);
            }
        }
    }

    int[][] copy(int source[][]){
        int[][] result = new int[SUDOKU_SIZE][SUDOKU_SIZE];
        for(int i = 0;i < SUDOKU_SIZE; i++){
            for(int j = 0;j < SUDOKU_SIZE; j++){
               result[i][j] = source[i][j];
            }
        }
        return result;
    }
    
    // Is there a conflict when we fill in d at position (r, c)?
    boolean givesConflict(int r, int c, int d) {
        return rowConflict(r, d) || columnConflict(c, d) || boxConflict(r, c, d);
    }

    // Is there a conflict when we fill in d in row r?
    boolean rowConflict(int r, int d) {

        for(int j = 0; j < SUDOKU_SIZE; j++){
            if(grid[r][j] == d){
                return true;
            }
        }
        return false;
    }

    // Is there a conflict in column c when we fill in d?
    boolean columnConflict(int c, int d) {

        for(int i = 0; i < SUDOKU_SIZE; i++){
            if(grid[i][c] == d){
                return true;
            }
        }
        return false;
    }

    // Is there a conflict in the box at (r, c) when we fill in d?
    boolean boxConflict(int r, int c, int d) {

        int br = r / 3 * 3;
        int bc=  c / 3 * 3;

        for(int i = br; i < br + SUDOKU_BOX_DIMENSION; i++){
            for(int j = bc; j < bc + SUDOKU_BOX_DIMENSION; j++){
                if(grid[i][j] == d){
                    return true;
                }
            }
        }
        return false;
    }
	
	// Is there a conflict in the asterisk when we fill in d?
	boolean asteriskConflict(){

        int[] valuesOfAsterisk = new int[9];

        valuesOfAsterisk[0] = grid[2][2];
        valuesOfAsterisk[1] = grid[1][4];
        valuesOfAsterisk[2] = grid[2][6];
        valuesOfAsterisk[3] = grid[4][1];
        valuesOfAsterisk[4] = grid[4][4];
        valuesOfAsterisk[5] = grid[4][7];
        valuesOfAsterisk[6] = grid[6][2];
        valuesOfAsterisk[7] = grid[7][4];
        valuesOfAsterisk[8] = grid[6][6];

        for(int i = 0; i < SUDOKU_SIZE; i++){
            for(int j = i + 1; j < SUDOKU_SIZE; j++){
                if(valuesOfAsterisk[i] == valuesOfAsterisk[j]){
                    return true;
                }
            }
        }
        return false;
    }
	
	// Finds the next empty square (in "reading order").
    int[] findEmptySquare(){
        for(int i = 0; i < SUDOKU_SIZE; i++){
            for(int j = 0; j < SUDOKU_SIZE; j++){
               if(grid[i][j] == 0){
                   return new int[]{i, j};
               }
            }
        }
        return null;
    }

    // Print the sudoku grid.
    void print() {
        System.out.println(solutionCounter);
        for(int i = 0; i < SUDOKU_SIZE; i++){
            if(i % 3 == 0) {
                System.out.println("+-----------------+");
            }
            for(int j = 0; j < SUDOKU_SIZE; j++){
               if(j % 3 == 0){
                System.out.print("|" + lastSolutionGrid[i][j]);
                } else {
                    String currentDelimiter = " ";
                    System.out.print(currentDelimiter + lastSolutionGrid[i][j]);
                }
            }
            System.out.println("|");
        }
        System.out.println("+-----------------+");
    }

    // Run the actual solver.
    void solveIt() {
        solve();
        print();
    }

    public static void main(String[] args) {
        (new SudokuSolver()).solveIt();
    }
}