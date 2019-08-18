package boardgame;
//pe�as do jogo
public abstract class Piece {
	
	//position n�o ser� vis�vel na camada de xadrez
	protected Position position;
	//tabuleiro
	private Board board;
	
	//na hora de criar pe�a passa s� tabuleiro pq a position inicialmente ser� nula
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	//somente classe dentro do mesmo pacote e subclasse poder� acessar o tabuleiro da pe�a
	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	//m�todo concreto utilizando m�todo abstrato
	public boolean possibleMove(Position positon) {
		return possibleMoves()[position.getrow()][position.getColumn()];
		
	}
	//chamar m�todo possibleMoves e varr�-lo e ver se tem pelo menos uma posi��o verdadeiro para mover a pe�a
	public boolean isThereAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				//se matriz linha i e columna j for verdadeiro
				if(mat[i][j]) {
					//movimento da pe�a � poss�vel
					return true;
				}
			}
		}
		//movimento da pe�a n�o � poss�vel
		return false;
		
	}


	
	
	
	
	

}
