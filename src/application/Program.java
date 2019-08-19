package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//Instanciando uma partida de xadrez e imprimindo o tabuleiro
		ChessMatch chessMatch = new ChessMatch();
		
		
		while(true) {
			try {
				UI.clearScreen();
				//Recebe matriz peças da partida. Imprimi tabuleiro
				UI.printMatch(chessMatch);
				
				System.out.println();
				
				//informar posição origem
				System.out.println("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source); 
				UI.clearScreen();
				//imprimindo tabuleiro colorindo os moviemntos possíveis da peça
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				//informar posição destino
				System.out.println("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturePiece = chessMatch.performChessMove(source, target);
			}
			catch(ChessException e) {
				System.out.println(e.getMessage());
				//programa aguarda pressionar enter
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				//programa aguarda pressionar enter
				sc.nextLine();
			}
			
		}
	}

}
