package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//Pe�a Rei do xadrez
public class King extends ChessPiece{

	//repassar dados para construtor da super classe(tabuleiro e cor)
	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}

}
