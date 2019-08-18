package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Peça Torre do xadrez
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
		//matriz com mesmo número linhas e colunas do tabuleiro 
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//Marcar verdadeiro posições acima da peça
		//verificar linha acima da peça(-1) e a mesma coluna da peça. Acessando Piece(posição da peça)
		p.setValues(position.getrow() - 1, position.getColumn());
		
		//enquanto a posição p existir e não tiver uma peça no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
			//linha andar mais uma posição para cima
			p.setrow(p.getrow() - 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//Marcar verdadeiro posições a esquerda
		//verificar linha esquerda da peça e coluna decrementar -1. Acessando Piece(posição da peça)
		p.setValues(position.getrow(), position.getColumn() -1);
		
		//enquanto a posição p existir e não tiver uma peça no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
			//coluna andar mais uma posição para esquerda
			p.setColumn(p.getColumn() -1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		
		//Marcar verdadeiro posições a direita
		//verificar linha direita da peça e coluna direita aumenta +1. Acessando Piece(posição da peça)
		p.setValues(position.getrow(), position.getColumn() +1);
		
		//enquanto a posição p existir e não tiver uma peça no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
			//coluna andar mais uma posição para direita
			p.setColumn(p.getColumn() +1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		
		//Marcar verdadeiro posições abaixo da peça
		//verificar linha abaixo da peça(+1) e a mesma coluna da peça. Acessando Piece(posição da peça)
		p.setValues(position.getrow() + 1, position.getColumn());
		
		//enquanto a posição p existir e não tiver uma peça no lugar(estiver vazia)
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
			//linha andar mais uma posição para baixo
			p.setrow(p.getrow() + 1);
		}
		
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			//recebe verdadeiro posição matriz indicando que a peça pode mover para lá
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
