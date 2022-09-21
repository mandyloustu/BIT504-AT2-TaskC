import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameMain extends JPanel implements MouseListener {
	
	// Constants for Tic Tac Toe game 
	// Number of ROWS by COLS cell constants 
	public static final int ROWS = 3;      
	public static final int COLS = 3;   
	// The name of the game (displays at the top of the window)
	public static final String TITLE = "Tic Tac Toe"; 
	// Constants for dimensions - used for drawing
	// Cell width and height
	public static final int CELL_SIZE = 100;
	// Drawing the canvas
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	// The X and O are displayed inside a cell, with padding from border
	public static final int CELL_PADDING = CELL_SIZE / 6;    
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;    
	public static final int SYMBOL_STROKE_WIDTH = 8;

	
	/* Declaration of game object variables */
	// Game board 
	private Board board;
	// The current state of the game (ENUM)
	private GameState currentState; 
	// The current player (ENUM)
	private Player currentPlayer; 
	// Message display (status of the game, winner etc)
	private JLabel statusBar;       
	

	/* Constructor to setup the interface and game components on the panel */
	public GameMain() {
		
		// The JPanel fires a MouseEvent on MouseClicked 
		addMouseListener((MouseListener)this); 
		// Setup of the game's status bar (JLabel)
		statusBar = new JLabel(" ");
		statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		statusBar.setOpaque(true);
		statusBar.setBackground(Color.LIGHT_GRAY);
		// Layout of the panel is in border layout
		setLayout(new BorderLayout());
		add(statusBar, BorderLayout.SOUTH);
		// Account for statusBar height in overall height
		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 30));
		
		// New instance of the game board
		board = new Board(); 
		
		// Calling the method to initialise the game
		initGame();
	}
	
	
	/* The MAIN method */
	public static void main(String[] args) {
		
		//Run GUI code in Event Dispatch thread for thread safety
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				// Create a main window using JFrame to contain the JPanel
				JFrame frame = new JFrame(TITLE);
				// Creating the GameMain panel and adding it to the JFrame
				frame.add(new GameMain()); // The game is started by calling the GameMain() method 
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
	
	/* Custom painting codes on this JPanel */
	public void paintComponent(Graphics g) {
		
		// Filling the background and setting the color
		super.paintComponent(g);
		setBackground(Color.WHITE);
		// Painting the game board
		board.paint(g);
		// Setting the status bar message
		if (currentState == GameState.Playing) {          
			statusBar.setForeground(Color.BLACK);          
			if (currentPlayer == Player.Cross) {   
				// Displaying a message informing the player it is time for X to play
				statusBar.setText("It's your turn: X!"); 
			} else {    
				// Displaying a message informing the player it is time for O to play
				statusBar.setText("It's your turn: O!");
			}       
			} else if (currentState == GameState.Draw) {          
				statusBar.setForeground(Color.RED);          
				// Informing the players it's a draw
				statusBar.setText("Game has ended in a draw. Click to play again.");        
			} else if (currentState == GameState.Cross_won) {          
				statusBar.setForeground(Color.RED);          
				// Informing the players X won the game
				statusBar.setText("X Won! Click to play again.");       
			} else if (currentState == GameState.Nought_won) {          
				statusBar.setForeground(Color.BLUE);          
				// Informing the players O won the game
				statusBar.setText("O Won! Click to play again.");       
			}
		}
	
	
	/* Initialising the game-board contents and the current status of GameState and Player */
	public void initGame() {
			for (int row = 0; row < ROWS; ++row) {          
				for (int col = 0; col < COLS; ++col) {  
					// all cells empty
					board.cells[row][col].content = Player.Empty;           
				}
			}
			currentState = GameState.Playing;
			currentPlayer = Player.Cross;
		}
		
		
	/* After each turn updateGame() checks to see if the current player has won OR if the game is a draw and updates the CurrentState accordingly */
	public void updateGame(Player thePlayer, int row, int col) {
			
			if(board.hasWon(thePlayer, row, col)) {
				if(thePlayer == Player.Cross) {
					currentState = GameState.Cross_won;
				} else {
					currentState = GameState.Nought_won;
				} 
			} else {
				if (board.isDraw()) {
					currentState = GameState.Draw;
				}
			}
		}
		
			
	/* Event handler for mouse clicks on the JPanel */
	public void mouseClicked(MouseEvent event) {
			
			// Determine the coordinates of the click event
			int mouseX = event.getX();
			int mouseY = event.getY();
			// Determine the row and column clicked
			int rowSelected = mouseY / CELL_SIZE;
			int colSelected = mouseX / CELL_SIZE;
				if (currentState == GameState.Playing) {
					if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0 && colSelected < COLS && board.cells[rowSelected][colSelected].content == Player.Empty) {
						// Move
						board.cells[rowSelected][colSelected].content = currentPlayer;
						// Update currentState
						updateGame(currentPlayer, rowSelected, colSelected);
						// Switch the player
						if (currentPlayer == Player.Cross) {
							currentPlayer = Player.Nought;
						} else {
							currentPlayer = Player.Cross;
						}
					}
				} else {
					// The game is over, restart 
					initGame();
				}
				// Repaint the UI
				repaint();
		}
	
	
	// Auto-generated events
	@Override
	public void mousePressed(MouseEvent event) {
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
	}
	
	@Override
	public void mouseEntered(MouseEvent event) {
	}
	
	@Override
	public void mouseExited(MouseEvent event) {
	}
	// End of auto-generated events

	
} // End of GameMain
