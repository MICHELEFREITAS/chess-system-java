package boardgame;
//peças do jogo
public class Piece {
	
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


	
	
	
	
	

}
