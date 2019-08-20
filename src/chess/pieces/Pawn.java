package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Pe�a pe�o do xadrez
public class Pawn extends ChessPiece{

	public Pawn(Board board, Color color) {
		super(board, color);
		
	}

	@Override
	public boolean[][] possibleMoves() {
		//matriz e posi��o auxiliar
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0,0);
		
		//se cor pe�o for branca
		if(getColor() == Color.WHITE) {
			//menos 1 posi��o acima
			p.setValues(position.getrow() -1, position.getColumn());
			//se pe�o pode mover para l�. 1 linha para frente. Se n�o n�o existir pe�a l�
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
			//2 linhas a frente
			p.setValues(position.getrow() -2, position.getColumn());
			//ver se a primeira casa est� livre para o pe�o mover duas casas na primeira jogada
			Position p2 = new Position(position.getrow() -1, position.getColumn());
			
			//se posi��o existe, se n�o existe pe�a na posi��o p e depois  p2 e MoveCount for 0 (primeira jogada)
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount()==0) {
				//se tudo for verdade o pe�o pode mover para l�
				mat[p.getrow()][p.getColumn()] = true;
			}
			//verificar diagonal esquerda pe�o branco
			p.setValues(position.getrow() -1, position.getColumn() -1);
			//se a posi��o existe e se tem pe�a advers�ria l�
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
			//verificar diagonal direita pe�o branco
			p.setValues(position.getrow() -1, position.getColumn() +1);
			//se a posi��o existe e se tem pe�a advers�ria l�
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
		}
		//pe�a preta
		else {
			
			//mais 1 posi��o
			p.setValues(position.getrow() +1, position.getColumn());
			//se pe�o pode mover para l�. 1 linha para frente. Se n�o n�o existir pe�a l�
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
			//2 linhas a frente
			p.setValues(position.getrow() +2, position.getColumn());
			//ver se a primeira casa est� livre para o pe�o mover duas casas na primeira jogada
			Position p2 = new Position(position.getrow() +1, position.getColumn());
			
			//se posi��o existe, se n�o existe pe�a na posi��o p e depois  p2 e MoveCount for 0 (primeira jogada)
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount()==0) {
				//se tudo for verdade o pe�o pode mover para l�
				mat[p.getrow()][p.getColumn()] = true;
			}
			//verificar diagonal esquerda pe�o preto
			p.setValues(position.getrow() +1, position.getColumn() -1);
			//se a posi��o existe e se tem pe�a advers�ria l�
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
			//verificar diagonal direita pe�o preto
			p.setValues(position.getrow() +1, position.getColumn() +1);
			//se a posi��o existe e se tem pe�a advers�ria l�
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}
