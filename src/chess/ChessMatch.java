package chess;

import boardgame.Board;

//Partida de xadrez. Nessa classe define dimensão do xadrez 8 por 8

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
	}
	
	//método retorna matriz peças xadrez correspondentes a partida
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int i=0 ; i < board.getRows(); i++) {
			for(int j=0 ; j < board.getColumns(); j++) {
				//para cada posição ij do tabuleiro a matriz....Peça de xadrez
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		//retorna a matriz de peças do xadrez
		return mat;
	}

}
