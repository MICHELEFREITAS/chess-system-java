package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		
		//Instanciando uma partida de xadrez e imprimindo o tabuleiro
		ChessMatch chessMatch = new ChessMatch();
		
		//Recebe matriz peças da partida
		UI.printBoard(chessMatch.getPieces());
	}

}
