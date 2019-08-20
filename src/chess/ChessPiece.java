package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//Pe�a de xadrez. Classe subclasse de pe�a

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	//cor da pe�a poder� ser somente acessada
	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	//incrementar o moveCount
	public void increaseMoveCount() {
		moveCount++;
	}
	
	//decrementar o moveCount
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {
		//pegar posi��o da pe�a
		return ChessPosition.fromPosition(position);
	}
	
	//verificar se existe pe�a advers�ria nessa posi��o
	protected boolean isThereOpponentPiece(Position position) {
		//pega pega na posi��o
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		
		//testa se o p � a pe�a advers�ria e se a cor � diferente da pe�a(pe�a advers�ria)
		return p != null && p.getColor() != color;
		
	}


}
