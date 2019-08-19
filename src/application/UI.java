package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	
	//códigos das cores para imprimir no console
	//cores do texto
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	//cores do fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	//limpar tela
	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	
	//fazer leitura da posição utilizando o Scanner
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			
			String s = sc.nextLine();
			//a1 coluna é o primeiro caracter 
			char column = s.charAt(0);
			//recortar o string a partir da posição 1 e converte para inteiro
			int row = Integer.parseInt(s.substring(1));
			
			return new ChessPosition(column, row);
			
		}catch(RuntimeException e){
			//erro entrada dados
			throw new InputMismatchException("Error reading chessposition. Valid values a1 to h8.");
		}	
	}
	//imprimir a partida
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		//imprimir tabuleiro
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		//aguardando jogador da cor...
		System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
		
	}
	
	
	public static void printBoard(ChessPiece[][] pieces) {
		//imprimir o tabuleiro no formato
		
		for(int i=0; i<pieces.length; i++) {
			
			//8,7...
			System.out.print((8-i) + " ");
			
			for(int j=0; j<pieces.length; j++) {
				//false -> peça sem fundo colorido
				printPiece(pieces[i][j], false);
			}
			//quebra de linha da matriz do tabuleiro
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		//imprimir o tabuleiro no formato
		
		for(int i=0; i<pieces.length; i++) {
			
			//8,7...
			System.out.print((8-i) + " ");
			
			for(int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			//quebra de linha da matriz do tabuleiro
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	//imprimir 1 peça. background variável que indica se devo ou não colorir a peça
	private static void printPiece(ChessPiece piece, boolean background) {
		//true colore com blue
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		} 
		
		//nao tem peça nessa posição
    	if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
            	//ANSI_RESET vai resetar cor da peça
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
    	//espaço separar as peças
        System.out.print(" ");
	}
	
	//imprimir lista de peças capturadas do jogo todo
	private static void printCapturedPieces(List<ChessPiece> captured) {
		//filtra da lista todas peças cuja cor é branca
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
	
		System.out.println("Captured pieces:");
		System.out.print("White: ");
		System.out.println(ANSI_WHITE);
		//padrão imprimir array 
		System.out.print(Arrays.toString(white.toArray()));
		System.out.println(ANSI_RESET);
		
		System.out.print("Black: ");
		System.out.println(ANSI_YELLOW);
		//padrão imprimir array 
		System.out.print(Arrays.toString(black.toArray()));
		System.out.println(ANSI_RESET);

	}
}
