package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Peça Bispo do xadrez
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
		//matriz com mesmo número linhas e colunas do tabuleiro 
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//Marcar verdadeiro posições noroeste (diagonal sup esquerda)
		//verificar linha noroeste da peça(-1) e coluna -1 da peça. Acessando Piece(posição da peça)
		p.setValues(position.getrow() - 1, position.getColumn() -1);
		
		//enquanto a posição p existir e não tiver uma peça no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
			//linha andar mais uma posição para cima
			p.setValues(p.getrow() - 1, p.getColumn() -1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//Marcar verdadeiro posições a nordeste(diagonal sup direit)
		//verificar linha -1 da peça e coluna incrementar +1. Acessando Piece(posição da peça)
		p.setValues(position.getrow() -1, position.getColumn() +1);
		
		//enquanto a posição p existir e não tiver uma peça no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
			p.setValues(p.getrow() -1, p.getColumn() +1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//Marcar verdadeiro posições sudeste (diagonal direita)
		//verificar linha +1 da peça e coluna +1. Acessando Piece(posição da peça)
		p.setValues(position.getrow() +1, position.getColumn() +1);
		
		//enquanto a posição p existir e não tiver uma peça no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
			p.setValues(p.getrow() +1, p.getColumn() +1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		
		//Marcar verdadeiro posições sudoeste da peça
		//verificar linha +1 e coluna -1. Acessando Piece(posição da peça)
		p.setValues(position.getrow() + 1, position.getColumn() -1);
		
		//enquanto a posição p existir e não tiver uma peça no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
			//linha andar mais uma posição para baixo e coluna -1
			p.setValues(p.getrow() + 1, p.getColumn() -1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
