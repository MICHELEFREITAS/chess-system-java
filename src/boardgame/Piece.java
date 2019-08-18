package boardgame;
//peças do jogo
public abstract class Piece {
	
	//position não será visível na camada de xadrez
	protected Position position;
	//tabuleiro
	private Board board;
	
	//na hora de criar peça passa só tabuleiro pq a position inicialmente será nula
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	//somente classe dentro do mesmo pacote e subclasse poderá acessar o tabuleiro da peça
	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	//método concreto utilizando método abstrato
	public boolean possibleMove(Position positon) {
		return possibleMoves()[position.getrow()][position.getColumn()];
		
	}
	//chamar método possibleMoves e varré-lo e ver se tem pelo menos uma posição verdadeiro para mover a peça
	public boolean isThereAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				//se matriz linha i e columna j for verdadeiro
				if(mat[i][j]) {
					//movimento da peça é possível
					return true;
				}
			}
		}
		//movimento da peça não é possível
		return false;
		
	}


	
	
	
	
	

}
