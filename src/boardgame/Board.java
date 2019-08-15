package boardgame;
//tabuleiro
public class Board {

	//tabuleiro tem linhas, colunas e uma matriz de peças
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		
		//matriz será instanciada com a quant. de linhas e colunas informadas
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
	
	//método que vai retornar matriz pieces na linha row e coluna column
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	
	//sobrecarga do método anterior. Retornar peça pela posição
	public Piece piece(Position position) {
		return pieces[position.getrow()][position.getColumn()];
	}
	
}
