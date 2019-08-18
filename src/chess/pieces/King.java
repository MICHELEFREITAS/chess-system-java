package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Peça Rei do xadrez
public class King extends ChessPiece{

	//repassar dados para construtor da super classe(tabuleiro e cor)
	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	//ver se o rei pode mover para a posição
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
		
		//posição acima do rei
		p.setValues(position.getrow() -1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//posição abaixo do rei
		p.setValues(position.getrow() +1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
			
		//posição esquerda do rei
		p.setValues(position.getrow(), position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//posição direita do rei
		p.setValues(position.getrow(), position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//posição noroeste - diagonal superior esquerda
		p.setValues(position.getrow() -1, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//posição nordeste - diagonal superior direita
		p.setValues(position.getrow() -1, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}
		
		//posição sudoeste - diagonal inferior esquerda
		p.setValues(position.getrow() +1, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}		
		
		//posição sudeste - diagonal inferior direita
		p.setValues(position.getrow() +1, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getColumn()] = true;
		}		
		
		return mat;
	}

}
