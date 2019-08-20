package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//Pe�a pe�o do xadrez
public class Pawn extends ChessPiece{
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
		
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
			
			//specialmove en passant white
			//testa se a posi��o da pe�a est� na linha 3
			if(position.getrow()==3) {
				Position left = new Position(position.getrow(), position.getColumn() -1);
				//se a posi��o da esquerda existe, se tem uma pe�a l� que � oponente e se a pe�a que est� l� � vuner�vel a tomar o en passant 
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
					//pe�o captura pe�a e move 1 linha ap�s ela
					mat[left.getrow() -1][left.getColumn()] = true;
				}
				Position rigth = new Position(position.getrow(), position.getColumn() +1);
				//se a posi��o da direita existe, se tem uma pe�a l� que � oponente e se a pe�a que est� l� � vuner�vel a tomar o en passant 
				if(getBoard().positionExists(rigth) && isThereOpponentPiece(rigth) && getBoard().piece(rigth) == chessMatch.getEnPassantVunerable()) {
					//pe�o captura pe�a e move 1 linha ap�s ela
					mat[rigth.getrow() -1][rigth.getColumn()] = true;
				}
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
			
			//specialmove en passant black
			//testa se a posi��o da pe�a est� na linha 3
			if(position.getrow()==4) {
				Position left = new Position(position.getrow(), position.getColumn() -1);
				//se a posi��o da esquerda existe, se tem uma pe�a l� que � oponente e se a pe�a que est� l� � vuner�vel a tomar o en passant 
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
					//pe�o captura pe�a e move 1 linha ap�s ela
					mat[left.getrow() +1][left.getColumn()] = true;
				}
				Position rigth = new Position(position.getrow(), position.getColumn() +1);
				//se a posi��o da direita existe, se tem uma pe�a l� que � oponente e se a pe�a que est� l� � vuner�vel a tomar o en passant 
				if(getBoard().positionExists(rigth) && isThereOpponentPiece(rigth) && getBoard().piece(rigth) == chessMatch.getEnPassantVunerable()) {
					//pe�o captura pe�a e move 1 linha ap�s ela
					mat[rigth.getrow() +1][rigth.getColumn()] = true;
				}
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}
