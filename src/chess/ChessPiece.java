package chess;

import boardgame.Board;
import boardgame.Piece;

//Pe�a de xadrez. Classe subclasse de pe�a

public class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	//cor da pe�a poder� ser somente acessada
	public Color getColor() {
		return color;
	}


}
