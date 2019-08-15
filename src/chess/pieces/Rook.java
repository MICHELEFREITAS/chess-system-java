package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//Peça Torre do xadrez
public class Rook extends ChessPiece {
	
	//repassar dados para construtor da super classe(tabuleiro e cor)
	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		//LETRA correspondente a torre
		return "R";
	}

}
