package chess;

import boardgame.Position;

public class ChessPosition {
	//outro sistema coordenadas
	
	private char column;
	private int row;
	
	
	public ChessPosition(char column, int row) {
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
		}
		
		this.column = column;
		this.row = row;
	}


	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		//nova posi��o 8 - posi��o do xadrez e depois coluna - caracter a
		return new Position(8 - row, column - 'a');
	}
	
	protected static ChessPosition fromPosition(Position position) {
		//f�rmula inversa do Position. A linha caracter 'a' - position...
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getrow());
	}
	
	//imprimir linha coluna na ordem a1, a2, b1...
	@Override
	public String toString() {
		//concatena��o de Strings
		return "" + column + row;
	}


	
	
	
}
