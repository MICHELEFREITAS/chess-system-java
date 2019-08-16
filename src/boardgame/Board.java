package boardgame;
//tabuleiro
public class Board {

	//tabuleiro tem linhas, colunas e uma matriz de pe�as
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	
	public Board(int rows, int columns) {
		//quant tabuleiro tem que ser pelo 1
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: There must be at least 1 row and 1 column");
		}
		
		this.rows = rows;
		this.columns = columns;
		
		//matriz ser� instanciada com a quant. de linhas e colunas informadas
		pieces = new Piece[rows][columns];
	}


	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	//m�todo que vai retornar matriz pieces na linha row e coluna column
	public Piece piece(int row, int column) {
		
		//caso posi��o n�o exista lan�a exce��o personalizada
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the Board");
		}
		return pieces[row][column];
	}
	
	//sobrecarga do m�todo anterior. Retornar pe�a pela posi��o
	public Piece piece(Position position) {
		//n�o existe posi��o
		if(!positionExists(position)) {
			throw new BoardException("Position not on the Board");
		}
		
		return pieces[position.getrow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		
		//verificar se j� existe pe�a na posi��o
		if(thereIsAPiece(position)) {
			throw new BoardException("There is a already a piece on position " + position);
		}
		
		//pega matriz instanciada em cima, na posi��o dada e atribui a ela a pe�a informada
		pieces[position.getrow()][position.getColumn()] = piece;
		//acesso diretamente position do Piece que est� protect, pois Board pertence ao mesmo pacote - boardgame
		piece.position = position;
	}
	
	//m�todo retorna pe�a e remove de uma posi��o do tabuleiro
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position does not exist on board");
		}
		//n�o tem pe�a nessa posi��o
		if(piece(position) == null) {
			return null;			
		}
		Piece aux = piece(position);
		
		//pe�a retirada do tabuleiro
		aux.position = null;
		
		//na matriz de pe�a onde foi removida a pe�a agora 
		pieces[position.getrow()][position.getColumn()] = null;
		
		return aux;
	}
	
	
	//m�todo auxiliar do posisitionExists
	private boolean positionExists(int row, int column) {
		//condi��o completa para ver se posi��o existe - rows � altura do tabuleiro e columns quantidade coluna do tabuleiro
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	//ver se existe a posi��o
	public boolean positionExists(Position position) {
		return positionExists(position.getrow(), position.getColumn());
		
	}
	//tem uma pe�a nessa posi��o
	public boolean thereIsAPiece(Position position) {
		
		if(!positionExists(position)) {
			throw new BoardException("Position not on the Board");
		}
		
		return piece(position) != null;
	}
}
