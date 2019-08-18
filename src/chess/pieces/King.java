package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Pe�a Rei do xadrez
public class King extends ChessPiece{

	//repassar dados para construtor da super classe(tabuleiro e cor)
	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	//ver se o rei pode mover para a posi��o
	private boolean canMove(Position position){
		//pega pe�a p na posi��o
		ChessPiece p = (ChessPiece) getBoard().piece(position); 
		//pe�a � nula ou pe�a advers�ria (cor diferente)
		return p == null || p.getColor() != getColor();
	} 

	@Override
	public boolean[][] possibleMoves() {
		//boolean inicia como falso
		//matriz com mesmo n�mero linhas e colunas do tabuleiro 
		boolean[][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//posi��o acima do rei
		p.setValues(position.getrow() -1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//posi��o abaixo do rei
		p.setValues(position.getrow() +1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
			
		//posi��o esquerda do rei
		p.setValues(position.getrow(), position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//posi��o direita do rei
		p.setValues(position.getrow(), position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//posi��o noroeste - diagonal superior esquerda
		p.setValues(position.getrow() -1, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//posi��o nordeste - diagonal superior direita
		p.setValues(position.getrow() -1, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//posi��o sudoeste - diagonal inferior esquerda
		p.setValues(position.getrow() +1, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}		
		
		//posi��o sudeste - diagonal inferior direita
		p.setValues(position.getrow() +1, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}		
		
		return mat;
	}

}
