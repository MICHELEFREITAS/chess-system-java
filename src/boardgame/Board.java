package boardgame;
//tabuleiro
public class Board {

	//tabuleiro tem linhas, colunas e uma matriz de pe�as
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		
		//matriz ser� instanciada com a quant. de linhas e colunas informadas
		pieces = new Piece[rows][columns];
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getColumns() {
		return columns;
	}


	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	//m�todo que vai retornar matriz pieces na linha row e coluna column
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	
	//sobrecarga do m�todo anterior. Retornar pe�a pela posi��o
	public Piece piece(Position position) {
		return pieces[position.getrow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		//pega matriz instanciada em cima, na posi��o dada e atribui a ela a pe�a informada
		pieces[position.getrow()][position.getColumn()] = piece;
		//acesso diretamente position do Piece que est� protect, pois Board pertence ao mesmo pacote - boardgame
		piece.position = position;
	}
	
}
