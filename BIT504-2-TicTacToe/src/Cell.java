import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Cell {
    
	//Content of this cell (Empty, Cross, Nought)
	Player content;
	// Row and column of this cell
	int row, col;
	
	/* Constructor to initialise this cell with the specified row and col */
	public Cell(int row, int col) {
		
		this.row = row;
		this.col = col;
		
		// Sets the cell content to empty
		clear();
	}
	
	
	/* The Paint method paints the symbol (X, O) in the cell */
	public void paint(Graphics g) {
		
		// Graphics2D allows the setting of the pen's stroke size
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setStroke(new BasicStroke(GameMain.SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		// Draw the symbol in the position
		int x1 = col * GameMain.CELL_SIZE + GameMain.CELL_PADDING;
		int y1 = row * GameMain.CELL_SIZE + GameMain.CELL_PADDING;
		if (content == Player.Cross) {
			graphics2D.setColor(Color.RED);
			int x2 = (col + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING;
			int y2 = (row + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING;
			graphics2D.drawLine(x1, y1, x2, y2);
			graphics2D.drawLine(x2, y1, x1, y2);
		} else if (content == Player.Nought) {
			graphics2D.setColor(Color.BLUE);
			graphics2D.drawOval(x1, y1, GameMain.SYMBOL_SIZE, GameMain.SYMBOL_SIZE);
		}
	}
	
	
	/* The Clear method */
	public void clear() {
		content = Player.Empty;
	}


} // End of Cell
