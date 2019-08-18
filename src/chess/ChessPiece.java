package chess;

import boardgame.Board;
import boardgame.Piece;

//Peça de xadrez. Classe subclasse de peça

public abstract class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	//cor da peça poderá ser somente acessada
	public Color getColor() {
		return color;
	}


}
