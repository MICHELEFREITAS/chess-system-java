package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Pe�a Bispo do xadrez
public class Bishop extends ChessPiece {
	
	//repassar dados para construtor da super classe(tabuleiro e cor)
	public Bishop(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		//LETRA correspondente ao bispo
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		//boolean mat inicia como falso
		//matriz com mesmo n�mero linhas e colunas do tabuleiro 
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//Marcar verdadeiro posi��es noroeste (diagonal sup esquerda)
		//verificar linha noroeste da pe�a(-1) e coluna -1 da pe�a. Acessando Piece(posi��o da pe�a)
		p.setValues(position.getrow() - 1, position.getColumn() -1);
		
		//enquanto a posi��o p existir e n�o tiver uma pe�a no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
			//linha andar mais uma posi��o para cima
			p.setValues(p.getrow() - 1, p.getColumn() -1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//Marcar verdadeiro posi��es a nordeste(diagonal sup direit)
		//verificar linha -1 da pe�a e coluna incrementar +1. Acessando Piece(posi��o da pe�a)
		p.setValues(position.getrow() -1, position.getColumn() +1);
		
		//enquanto a posi��o p existir e n�o tiver uma pe�a no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
			p.setValues(p.getrow() -1, p.getColumn() +1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//Marcar verdadeiro posi��es sudeste (diagonal direita)
		//verificar linha +1 da pe�a e coluna +1. Acessando Piece(posi��o da pe�a)
		p.setValues(position.getrow() +1, position.getColumn() +1);
		
		//enquanto a posi��o p existir e n�o tiver uma pe�a no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
			p.setValues(p.getrow() +1, p.getColumn() +1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		
		//Marcar verdadeiro posi��es sudoeste da pe�a
		//verificar linha +1 e coluna -1. Acessando Piece(posi��o da pe�a)
		p.setValues(position.getrow() + 1, position.getColumn() -1);
		
		//enquanto a posi��o p existir e n�o tiver uma pe�a no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
			//linha andar mais uma posi��o para baixo e coluna -1
			p.setValues(p.getrow() + 1, p.getColumn() -1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
