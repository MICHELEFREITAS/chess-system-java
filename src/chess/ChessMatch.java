package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		//converter posi��o xadrez posi��o normal
		Position position = sourcePosition.toPosition();
		//validando posi��o de origem
		validateSourcePosition(position);
		//retorna movimentos poss�veis da pe�a nessa posi��o
		return board.piece(position).possibleMoves();
	}
	
	
	//opera��o para mover pe�a. Recebe a posi��o origem e posi��o destino
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//converter posi��es para posi��o da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		//validar se na posi��o origem tem havia pe�a
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		
		//makeMove oper. mover pe�a
		Piece capturePiece = makeMove(source, target);
		
		//retorna a pe�a capturada
		return (ChessPiece)capturePiece;
		
	}
	
	//realizar movimento
	private Piece makeMove(Position source, Position target) {
		//retira pe�a posi��o origem
		Piece p = board.removePiece(source);
		
		//remover pe�a posi��o destino que ser� a pe�a capturada
		Piece capturePiece = board.removePiece(target);
		
		//colocar posi��o de origem na posi��o de destino
		board.placePiece(p, target);
		
		return capturePiece;
		
		
	}
	
	private void validateSourcePosition(Position position) {
		//se n�o existir pe�a nessa posi��o
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		//a partir do tabalueiro ver se n�o tem movimento poss�vel da pe�a
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece"); 
			
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		//se para pe�a de destino a pe�a de origem n�o � um mov. poss�vel
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
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
