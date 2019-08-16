package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//Partida de xadrez. Nessa classe define dimensão do xadrez 8 por 8

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		//tabuleiro 8x8
		board = new Board(8,8);
		//chama para iniciar partica
		initialSetup();
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
	
	//operação para mover peça. Recebe a posição origem e posição destino
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//converter posições para posição da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		//validar se na posição origem tem havia peça
		validateSourcePositon(source);
		
		//makeMove oper. mover peça
		Piece capturePiece = makeMove(source, target);
		
		//retorna a peça capturada
		return (ChessPiece)capturePiece;
		
	}
	
	//realizar movimento
	private Piece makeMove(Position source, Position target) {
		//retira peça posição origem
		Piece p = board.removePiece(source);
		
		//remover peça posição destino que será a peça capturada
		Piece capturePiece = board.removePiece(target);
		
		//colocar posição de origem na posição de destino
		board.placePiece(p, target);
		
		return capturePiece;
		
		
	}
	
	private void validateSourcePositon(Position position) {
		//se não existir peça nessa posição
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
	}
	
	//recebe coordenadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	//método responsável por iniciar a partida colocando peças no tabuleiro
	private void initialSetup() {
		//colocar nova peça posição b6
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
