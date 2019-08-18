package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Pe�a Torre do xadrez
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

	@Override
	public boolean[][] possibleMoves() {
		//boolean mat inicia como falso
		//matriz com mesmo n�mero linhas e colunas do tabuleiro 
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//Marcar verdadeiro posi��es acima da pe�a
		//verificar linha acima da pe�a(-1) e a mesma coluna da pe�a. Acessando Piece(posi��o da pe�a)
		p.setValues(position.getrow() - 1, position.getColumn());
		
		//enquanto a posi��o p existir e n�o tiver uma pe�a no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
			//linha andar mais uma posi��o para cima
			p.setrow(p.getrow() - 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//Marcar verdadeiro posi��es a esquerda
		//verificar linha esquerda da pe�a e coluna decrementar -1. Acessando Piece(posi��o da pe�a)
		p.setValues(position.getrow(), position.getColumn() -1);
		
		//enquanto a posi��o p existir e n�o tiver uma pe�a no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
			//coluna andar mais uma posi��o para esquerda
			p.setColumn(p.getColumn() -1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		
		//Marcar verdadeiro posi��es a direita
		//verificar linha direita da pe�a e coluna direita aumenta +1. Acessando Piece(posi��o da pe�a)
		p.setValues(position.getrow(), position.getColumn() +1);
		
		//enquanto a posi��o p existir e n�o tiver uma pe�a no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
			//coluna andar mais uma posi��o para direita
			p.setColumn(p.getColumn() +1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		
		//Marcar verdadeiro posi��es abaixo da pe�a
		//verificar linha abaixo da pe�a(+1) e a mesma coluna da pe�a. Acessando Piece(posi��o da pe�a)
		p.setValues(position.getrow() + 1, position.getColumn());
		
		//enquanto a posi��o p existir e n�o tiver uma pe�a no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
			//linha andar mais uma posi��o para baixo
			p.setrow(p.getrow() + 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posi��o matriz indicando que a pe�a pode mover para l�
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
