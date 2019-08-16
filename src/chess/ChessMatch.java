package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

//Partida de xadrez. Nessa classe define dimens�o do xadrez 8 por 8

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		//tabuleiro 8x8
		board = new Board(8,8);
		//chama para iniciar partica
		initialSetup();
	}
	
	//m�todo retorna matriz pe�as xadrez correspondentes a partida
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int i=0 ; i < board.getRows(); i++) {
			for(int j=0 ; j < board.getColumns(); j++) {
				//para cada posi��o ij do tabuleiro a matriz....Pe�a de xadrez
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		//retorna a matriz de pe�as do xadrez
		return mat;
	}
	
	//recebe coordenadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	//m�todo respons�vel por iniciar a partida colocando pe�as no tabuleiro
	private void initialSetup() {
		//colocar nova pe�a posi��o b6
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
