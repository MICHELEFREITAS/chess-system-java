package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//Pe�a Rei do xadrez
public class King extends ChessPiece{
	
	//depend�ncia para partida
	private ChessMatch chessMatch;

	//repassar dados para construtor da super classe(tabuleiro e cor)
	//incluir ref para partida com terceiro argumento
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
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
	
	//m�todo aux testar condi��o de roque. Se torre est� apta para roque
	private boolean testRookCasting(Position position) {
		//pega pe�a nessa posi��o
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		//se tudo isso acontecer torre apta para jogada roque
		return p!= null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
		
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
		
		//movimento especial roque
		//se n�o teve nenhum movimento do rei e se ele n�o estiver em cheque
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			
			//movimento especial roque torre do rei - kingside rock
			//posi��o onde deve estar torre do rei
			Position posT1 = new Position(position.getrow(), position.getColumn() + 3);
			
			if(testRookCasting(posT1)) {
				Position p1 = new Position(position.getrow(), position.getColumn() + 1);
				Position p2 = new Position(position.getrow(), position.getColumn() + 2);
				
				if(getBoard().piece(p1)==null && getBoard().piece(p2)==null) {
					
					mat[position.getrow()][position.getColumn() + 2] = true;
				}
				
 			}
			//roque grande - queenside rock. pegar coluna rei -4 na esquerda
			Position posT2 = new Position(position.getrow(), position.getColumn() -4);
			//se essa torre est� apta para roque
			if(testRookCasting(posT2)) {
				//verifica se essas posi��es est�o vagas
				Position p1 = new Position(position.getrow(), position.getColumn() - 1);
				Position p2 = new Position(position.getrow(), position.getColumn() - 2);
				Position p3 = new Position(position.getrow(), position.getColumn() - 3);
				
				if(getBoard().piece(p1)==null && getBoard().piece(p2)==null && getBoard().piece(p3)==null) {
					//rei pode mover duas casa para esquerda
					mat[position.getrow()][position.getColumn() -2] = true;
				}
			}
		}
		return mat;
	}
}
