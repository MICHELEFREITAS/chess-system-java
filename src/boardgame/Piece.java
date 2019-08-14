package boardgame;
//pe�as do jogo
public class Piece {
	
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


	
	
	
	
	

}
