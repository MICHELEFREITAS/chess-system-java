package application;

import chess.ChessPiece;

public class UI {
	
	public static void printBoard(ChessPiece[][] pieces) {
		//imprimir o tabuleiro no formato
		
		for(int i=0; i<pieces.length; i++) {
			
			//8,7...
			System.out.print((8-i) + " ");
			
			for(int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]);
			}
			//quebra de linha da matriz do tabuleiro
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	//imprimir 1 peça
	private static void printPiece(ChessPiece piece) {
		
		//nao tem peça nessa posição
		if(piece == null) {
			System.out.print("-");
		}
		else {
			//imprime a peça
			System.out.print(piece);
		}
		//espaço separar as peças
		System.out.print(" ");
	}
}
