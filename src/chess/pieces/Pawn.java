package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Peça peão do xadrez
public class Pawn extends ChessPiece{

	public Pawn(Board board, Color color) {
		super(board, color);
		
	}

	@Override
	public boolean[][] possibleMoves() {
		//matriz e posição auxiliar
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0,0);
		
		//se cor peão for branca
		if(getColor() == Color.WHITE) {
			//menos 1 posição acima
			p.setValues(position.getrow() -1, position.getColumn());
			//se peão pode mover para lá. 1 linha para frente. Se não não existir peça lá
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
			//2 linhas a frente
			p.setValues(position.getrow() -2, position.getColumn());
			//ver se a primeira casa está livre para o peão mover duas casas na primeira jogada
			Position p2 = new Position(position.getrow() -1, position.getColumn());
			
			//se posição existe, se não existe peça na posição p e depois  p2 e MoveCount for 0 (primeira jogada)
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount()==0) {
				//se tudo for verdade o peão pode mover para lá
				mat[p.getrow()][p.getColumn()] = true;
			}
			//verificar diagonal esquerda peão branco
			p.setValues(position.getrow() -1, position.getColumn() -1);
			//se a posição existe e se tem peça adversária lá
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
			//verificar diagonal direita peão branco
			p.setValues(position.getrow() -1, position.getColumn() +1);
			//se a posição existe e se tem peça adversária lá
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
		}
		//peça preta
		else {
			
			//mais 1 posição
			p.setValues(position.getrow() +1, position.getColumn());
			//se peão pode mover para lá. 1 linha para frente. Se não não existir peça lá
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
			//2 linhas a frente
			p.setValues(position.getrow() +2, position.getColumn());
			//ver se a primeira casa está livre para o peão mover duas casas na primeira jogada
			Position p2 = new Position(position.getrow() +1, position.getColumn());
			
			//se posição existe, se não existe peça na posição p e depois  p2 e MoveCount for 0 (primeira jogada)
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount()==0) {
				//se tudo for verdade o peão pode mover para lá
				mat[p.getrow()][p.getColumn()] = true;
			}
			//verificar diagonal esquerda peão preto
			p.setValues(position.getrow() +1, position.getColumn() -1);
			//se a posição existe e se tem peça adversária lá
			if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getColumn()] = true;
			}
			//verificar diagonal direita peão preto
			p.setValues(position.getrow() +1, position.getColumn() +1);
			//se a posição existe e se tem peça adversária lá
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
