package chess;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

import chess.pieces.King;
import chess.pieces.Rook;

//Partida de xadrez. Nessa classe define dimensão do xadrez 8 por 8

public class ChessMatch {
	
	private int turn;
	//jogador atual
	private Color currentPlayer;
	private Board board;
	private boolean check;
	
	//lista de peças no tabuleiro
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	//lista peças capturadas
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		//tabuleiro 8x8
		board = new Board(8,8);
		turn = 1;
		currentPlayer= Color.WHITE;
		//chama para iniciar partica
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		//converter posição xadrez posição normal
		Position position = sourcePosition.toPosition();
		//validando posição de origem
		validateSourcePosition(position);
		//retorna movimentos possíveis da peça nessa posição
		return board.piece(position).possibleMoves();
	}
	
	
	//operação para mover peça. Recebe a posição origem e posição destino
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//converter posições para posição da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		//validar se na posição origem tem havia peça
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		
		//makeMove oper. mover peça
		Piece capturedPiece = makeMove(source, target);
		
		//se testCheck for verdadeiro o jogador se colocou em check
		if(testCheck(currentPlayer)) {
			//desfazer movimento
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		
		//oponente ficou em check - true, se não (:) não está em check
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		nextTurn();
		
		//retorna a peça capturada
		return (ChessPiece)capturedPiece;
		
	}
	
	//realizar movimento
	private Piece makeMove(Position source, Position target) {
		//retira peça posição origem
		Piece p = board.removePiece(source);
		
		//remover peça posição destino que será a peça capturada
		Piece capturedPiece = board.removePiece(target);
		
		//colocar posição de origem na posição de destino
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			//retira peça da lista de peças do tabuleiro
			piecesOnTheBoard.remove(capturedPiece);
			//adiciona na lista de peças capturadas
			capturedPieces.add(capturedPiece);
			
		}
		
		return capturedPiece;
		
	}
	
	//desfazer o movimento da peça /jogada
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		//tirar peça que foi movida para destino
		Piece p = board.removePiece(target);
		//coloca peça posição origem
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			//retirar peça lista de capturadas
			capturedPieces.remove(capturedPiece);
			//adicionar lista tabuleiro
			piecesOnTheBoard.add(capturedPiece);
		}
		
	}
	
	private void validateSourcePosition(Position position) {
		//se não existir peça nessa posição
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		//pega peça do tabulieiro nessa posição dowcach para ChessPiece e testa cor. Se a cor for diferente da cor do adversário, lança exceção.
		if(currentPlayer != ((ChessPiece) board.piece(position)).getColor())
		{
			throw new ChessException("The chosen piece is not yours");
		}
		//a partir do tabalueiro ver se não tem movimento possível da peça
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece"); 
			
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		//se para peça de destino a peça de origem não é um mov. possível
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	//troca de turn
	private void nextTurn() {
		//incrementar turn
		turn++;
		//mudar jogador atual. Se for branco trocar para o preto, se não ao contrário
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
		
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		//procurar lista qual é o rei dessa cor
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			//se peça for instancia do rei
			if (p instanceof King) {
				return (ChessPiece) p;
			}
			
		}
		throw new IllegalStateException("There is no: " + color + "king on the board.");
	}
	//a cor do rei está em check
	private boolean testCheck(Color color) {
		//pega posição rei formato matriz
		Position kingPosition = king(color).getChessPosition().toPosition();
		//lista de peças do oponente
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		//para cada peça p da lista oponente testar se tem algum movimento possível que leva ao rei
		for(Piece p: opponentPieces) {
			//matriz de peças. Se for true rei está em check
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getrow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		//se esgotar as peças e o rei não estiver em perigo
		return false;
	}
	
	
	//recebe coordenadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		//colocar peça no tabuleiro
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		//colocar peça na lista de peças no tabuleiro
		piecesOnTheBoard.add(piece);
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
