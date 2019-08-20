package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

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
	
	public ChessPosition getChessPosition() {
		//pegar posição da peça
		return ChessPosition.fromPosition(position);
	}
	
	//verificar se existe peça adversária nessa posição
	protected boolean isThereOpponentPiece(Position position) {
		//pega pega na posição
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		
		//testa se o p é a peça adversária e se a cor é diferente da peça(peça adversária)
		return p != null && p.getColor() != color;
		
	}


}
