package application;

	
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		
		List<ChessPiece> captured = new ArrayList<>(); 
		
		//enquanto partida n�o estiver com CheckMate
		while(!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				//Recebe matriz pe�as da partida. Imprimi tabuleiro
				UI.printMatch(chessMatch, captured);
				
				System.out.println();
				
				//informar posi��o origem
				System.out.println("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source); 
				UI.clearScreen();
				//imprimindo tabuleiro colorindo os moviemntos poss�veis da pe�a
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				//informar posi��o destino
				System.out.println("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				//executar mov xadrez e retornar pe�a capturada
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if(capturedPiece != null) {
					//adiciona pe�a na lista pe�as caputuradas
					captured.add(capturedPiece);
				}
				
				if(chessMatch.getPromoted() != null) {
					//usu�rio escolhe a pe�a promo��o
					System.out.println("Enter peace for promotion(B/N/R/Q): ");
					String type = sc.nextLine();
					chessMatch.replacePromotedPiece(type);
					
				}
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
		//limpa tela
		UI.clearScreen();
		//imprimir partida finalizada
		UI.printMatch(chessMatch, captured);
	}

}
