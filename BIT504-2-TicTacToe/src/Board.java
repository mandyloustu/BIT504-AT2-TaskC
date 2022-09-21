import java.awt.*;


public class Board {
	
	// Grid line width
	public static final int GRID_WIDTH = 8;
	// Grid line half width
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	//2D array of ROWS-by-COLS cell instances
	Cell [][] cells;
	
	/* Constructor to create the game board */
	public Board() {
		cells = new Cell[3][3];  
		for (int row = 0; row < GameMain.ROWS; ++ row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col] = new Cell(row, col);
			}
		}
	}
	
	
	/* Method to check whether any of the cells in the grid are empty. If so, the game ends in a draw */
	public boolean isDraw() {
		
		for(int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++ col) {
				if (cells[row][col].content == Player.Empty) {
					return false; 
				}
			}
		}
		// No cells are empty, it's a draw.
		return true;
	}

	
	/* Method to check which player has won the game */
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
		// Checks if the player has 3 in one row
		if (cells[playerRow][0].content == thePlayer && cells[playerRow][1].content == thePlayer && cells[playerRow][2].content == thePlayer) 
			return true;
		// Checks if the player has 3 in one column
		if (cells[0][playerCol].content == thePlayer && cells[1][playerCol].content == thePlayer & cells[2][playerCol].content == thePlayer) 
			return true;
		// Checks if the player has 3 in a diagonal
		if (cells[0][0].content == thePlayer && cells[1][1].content == thePlayer && cells[2][2].content == thePlayer) 
			return true;
		// Checks if the player has 3 in a diagonal (in the other direction)
		if (cells[0][2].content == thePlayer && cells[1][1].content == thePlayer && cells[2][0].content == thePlayer) 
			return true;
		// No winner - continue playing
		return false;
	}
	
	
	/* The paint method creates the grid */ 
	public void paint(Graphics g) {
		// Draw the grid
		g.setColor(Color.gray);
		for (int row = 1; row < GameMain.ROWS; ++row) {
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDTH_HALF, GameMain.CANVAS_WIDTH - 1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
		}
		for (int col = 1; col < GameMain.COLS; ++col) {
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDTH_HALF, 0, GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1, GRID_WIDTH, GRID_WIDTH);
		}
		// Draw the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col].paint(g);
			}
		}
	}
	

} // End of Board
