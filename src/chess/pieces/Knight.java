package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Peça Cavalo do xadrez
public class Knight extends ChessPiece{

	//repassar dados para construtor da super classe(tabuleiro e cor)
	public Knight(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		//letra cavalo
		return "N";
	}
	
	//ver se o cavalo pode mover para a posição
	private boolean canMove(Position position){
		//pega peça p na posição
		ChessPiece p = (ChessPiece) getBoard().piece(position); 
		//peça é nula ou peça adversária (cor diferente)
		return p == null || p.getColor() != getColor();
	} 

	@Override
	public boolean[][] possibleMoves() {
		//boolean inicia como falso
		//matriz com mesmo número linhas e colunas do tabuleiro 
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//todas possibilidades mover esquema 2|1 do cavalo
		
		p.setValues(position.getrow() -1, position.getColumn() -2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getrow() -2, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
			
		p.setValues(position.getrow() -2, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getrow() -1, position.getColumn() +2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getrow() +1, position.getColumn() +2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}

		p.setValues(position.getrow() +2, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getrow() +2, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}		
		
		p.setValues(position.getrow() +1, position.getColumn() -2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}		
		
		return mat;
	}

}
