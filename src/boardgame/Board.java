package boardgame;
//tabuleiro
public class Board {

	//tabuleiro tem linhas, colunas e uma matriz de peças
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
		
		//matriz será instanciada com a quant. de linhas e colunas informadas
		pieces = new Piece[rows][columns];
	}


	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	//método que vai retornar matriz pieces na linha row e coluna column
	public Piece piece(int row, int column) {
		
		//caso posição não exista lança exceção personalizada
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the Board");
		}
		return pieces[row][column];
	}
	
	//sobrecarga do método anterior. Retornar peça pela posição
	public Piece piece(Position position) {
		//não existe posição
		if(!positionExists(position)) {
			throw new BoardException("Position not on the Board");
		}
		
		return pieces[position.getrow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		
		//verificar se já existe peça na posição
		if(thereIsAPiece(position)) {
			throw new BoardException("There is a already a piece on position " + position);
		}
		
		//pega matriz instanciada em cima, na posição dada e atribui a ela a peça informada
		pieces[position.getrow()][position.getColumn()] = piece;
		//acesso diretamente position do Piece que está protect, pois Board pertence ao mesmo pacote - boardgame
		piece.position = position;
	}
	
	//método retorna peça e remove de uma posição do tabuleiro
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position does not exist on board");
		}
		//não tem peça nessa posição
		if(piece(position) == null) {
			return null;			
		}
		Piece aux = piece(position);
		
		//peça retirada do tabuleiro
		aux.position = null;
		
		//na matriz de peça onde foi removida a peça agora 
		pieces[position.getrow()][position.getColumn()] = null;
		
		return aux;
	}
	
	
	//método auxiliar do posisitionExists
	private boolean positionExists(int row, int column) {
		//condição completa para ver se posição existe - rows é altura do tabuleiro e columns quantidade coluna do tabuleiro
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	//ver se existe a posição
	public boolean positionExists(Position position) {
		return positionExists(position.getrow(), position.getColumn());
		
	}
	//tem uma peça nessa posição
	public boolean thereIsAPiece(Position position) {
		
		if(!positionExists(position)) {
			throw new BoardException("Position not on the Board");
		}
		
		return piece(position) != null;
	}
}
