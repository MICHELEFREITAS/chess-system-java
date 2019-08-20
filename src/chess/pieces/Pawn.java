package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//Peça peão do xadrez
public class Pawn extends ChessPiece{
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
		
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
			
			//specialmove en passant white
			//testa se a posição da peça está na linha 3
			if(position.getrow()==3) {
				Position left = new Position(position.getrow(), position.getColumn() -1);
				//se a posição da esquerda existe, se tem uma peça lá que é oponente e se a peça que está lá é vunerável a tomar o en passant 
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
					//peão captura peça e move 1 linha após ela
					mat[left.getrow() -1][left.getColumn()] = true;
				}
				Position rigth = new Position(position.getrow(), position.getColumn() +1);
				//se a posição da direita existe, se tem uma peça lá que é oponente e se a peça que está lá é vunerável a tomar o en passant 
				if(getBoard().positionExists(rigth) && isThereOpponentPiece(rigth) && getBoard().piece(rigth) == chessMatch.getEnPassantVunerable()) {
					//peão captura peça e move 1 linha após ela
					mat[rigth.getrow() -1][rigth.getColumn()] = true;
				}
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
			
			//specialmove en passant black
			//testa se a posição da peça está na linha 3
			if(position.getrow()==4) {
				Position left = new Position(position.getrow(), position.getColumn() -1);
				//se a posição da esquerda existe, se tem uma peça lá que é oponente e se a peça que está lá é vunerável a tomar o en passant 
				if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
					//peão captura peça e move 1 linha após ela
					mat[left.getrow() +1][left.getColumn()] = true;
				}
				Position rigth = new Position(position.getrow(), position.getColumn() +1);
				//se a posição da direita existe, se tem uma peça lá que é oponente e se a peça que está lá é vunerável a tomar o en passant 
				if(getBoard().positionExists(rigth) && isThereOpponentPiece(rigth) && getBoard().piece(rigth) == chessMatch.getEnPassantVunerable()) {
					//peão captura peça e move 1 linha após ela
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
