package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

//Partida de xadrez. Nessa classe define dimens�o do xadrez 8 por 8

public class ChessMatch {
	
	private int turn;
	//jogador atual
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	//lista de pe�as no tabuleiro
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	//lista pe�as capturadas
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
	
	public boolean getCheckMate() {
		return checkMate;
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
		Piece capturedPiece = makeMove(source, target);
		
		//se testCheck for verdadeiro o jogador se colocou em check
		if(testCheck(currentPlayer)) {
			//desfazer movimento
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		
		//oponente ficou em check - true, se n�o (:) n�o est� em check
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		//se a jogada deixou o oponente em check mate o jogo tem que acabar
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		//se n�o continua partida e chama pr�ximo turno.
		else {
			nextTurn();
		}
				
		
		//retorna a pe�a capturada
		return (ChessPiece)capturedPiece;
		
	}
	
	//realizar movimento
	private Piece makeMove(Position source, Position target) {
		//retira pe�a posi��o origem
		ChessPiece p = (ChessPiece)board.removePiece(source);
		
		//incrementar quant. movimento da pe�a
		p.increaseMoveCount();
		
		//remover pe�a posi��o destino que ser� a pe�a capturada
		Piece capturedPiece = board.removePiece(target);
		
		//colocar posi��o de origem na posi��o de destino
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			//retira pe�a da lista de pe�as do tabuleiro
			piecesOnTheBoard.remove(capturedPiece);
			//adiciona na lista de pe�as capturadas
			capturedPieces.add(capturedPiece);
			
		}
		
		return capturedPiece;
		
	}
	
	//desfazer o movimento da pe�a /jogada
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		//tirar pe�a que foi movida para destino
		ChessPiece p = (ChessPiece)board.removePiece(target);
		
		//decrementar quant. mov. dessa pe�a
		p.decreaseMoveCount();
		
		//coloca pe�a posi��o origem
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			//retirar pe�a lista de capturadas
			capturedPieces.remove(capturedPiece);
			//adicionar lista tabuleiro
			piecesOnTheBoard.add(capturedPiece);
		}
		
	}
	
	private void validateSourcePosition(Position position) {
		//se n�o existir pe�a nessa posi��o
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		//pega pe�a do tabulieiro nessa posi��o dowcach para ChessPiece e testa cor. Se a cor for diferente da cor do advers�rio, lan�a exce��o.
		if(currentPlayer != ((ChessPiece) board.piece(position)).getColor())
		{
			throw new ChessException("The chosen piece is not yours");
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
	//troca de turn
	private void nextTurn() {
		//incrementar turn
		turn++;
		//mudar jogador atual. Se for branco trocar para o preto, se n�o ao contr�rio
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
		
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		//procurar lista qual � o rei dessa cor
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			//se pe�a for instancia do rei
			if (p instanceof King) {
				return (ChessPiece) p;
			}
			
		}
		throw new IllegalStateException("There is no: " + color + "king on the board.");
	}
	//a cor do rei est� em check
	private boolean testCheck(Color color) {
		//pega posi��o rei formato matriz
		Position kingPosition = king(color).getChessPosition().toPosition();
		//lista de pe�as do oponente
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		//para cada pe�a p da lista oponente testar se tem algum movimento poss�vel que leva ao rei
		for(Piece p: opponentPieces) {
			//matriz de pe�as. Se for true rei est� em check
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getrow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		//se esgotar as pe�as e o rei n�o estiver em perigo
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		//se cor n�o estiver check
		if(!testCheck(color)) {
			return false;			
		}
		//pegar todas pe�as da cor passada como par�metro
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		
		for (Piece p : list) {
			//mov. poss�vel pe�a p
			boolean[][] mat = p.possibleMoves();
			for(int i=0; i<board.getRows(); i++) {
				for(int j=0; j<board.getColumns(); j++) {
					//se posi��o da matriz ij � um movimento poss�vel e se tira do check
					if(mat[i][j]) {
						//mover pe�a para para mov poss�vel da mat
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						//testar se o rei da cor ainda est� em check
						boolean testCheck = testCheck(color);
						undoMove (source, target, capturedPiece);
						//se n�o est� em check
						if(!testCheck) {
							return false;
						}
					}
				}
				
			}
			
		}
		//est� em checkMate
		return true;
	}
	
	//recebe coordenadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		//colocar pe�a no tabuleiro
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		//colocar pe�a na lista de pe�as no tabuleiro
		piecesOnTheBoard.add(piece);
	}
	
	//m�todo respons�vel por iniciar a partida colocando pe�as no tabuleiro
	private void initialSetup() {
		//colocar nova pe�a posi��o b6
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
	}
}
