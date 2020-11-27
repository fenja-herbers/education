package tictactoe_CleanCode;

	import java.util.Arrays;
	import java.util.InputMismatchException;
	import java.util.Scanner;

	
	public class CCTicTacToe {
		static Scanner in;
		static String[] board;
		static String turn;

		public static void main(String[] args) {
			in = new Scanner(System.in);
			board = new String[9];
			turn = "X";
			String winner = null;
			populateEmptyBoard();

			System.out.println("Dies ist ein 2 Spieler Tic Tac Toe Spiel.");
			System.out.println("*******************************************");
			printBoard();
			System.out.println("Spieler X beginnt. Tippe die Zahl des Feldes ein, in dem ein X eingetragen werden soll:");
			//Aenderung fuer pull request
			while (winner == null) {
				int numInput;
				try {
					numInput = in.nextInt();
					if (!(numInput > 0 && numInput <= 9)) {
						System.out.println("Ungueltige Eingabe; bitte erneut Feldzahl eingeben:");
						continue;
					}
				} catch (InputMismatchException e) {
					System.out.println("Ungueltige Eingabe; bitte erneut Feldzahl eingeben:");
					continue;
				}
				if (board[numInput-1].equals(String.valueOf(numInput))) {
					board[numInput-1] = turn;
					if (turn.equals("X")) {
						turn = "O";
					} else {
						turn = "X";
					}
					printBoard();
					winner = checkWinner();
				} else {
					System.out.println("Das Feld ist bereits belegt; bitte erneut Feldzahl eingeben:");
					continue;
				}
			}
			if (winner.equalsIgnoreCase("draw")) {
				System.out.println("Unentschieden! Danke fuers Spielen.");
			} else {
				System.out.println("Herzlichen Glueckwuensch! " + winner + "'hat gewonnen! Danke fuers Spielen.");
			}
		}

		static String checkWinner() {
			for (int a = 0; a < 8; a++) {
				String line = null;
				switch (a) {
				case 0:
					line = board[0] + board[1] + board[2];
					break;
				case 1:
					line = board[3] + board[4] + board[5];
					break;
				case 2:
					line = board[6] + board[7] + board[8];
					break;
				case 3:
					line = board[0] + board[3] + board[6];
					break;
				case 4:
					line = board[1] + board[4] + board[7];
					break;
				case 5:
					line = board[2] + board[5] + board[8];
					break;
				case 6:
					line = board[0] + board[4] + board[8];
					break;
				case 7:
					line = board[2] + board[4] + board[6];
					break;
				}
				if (line.equals("XXX")) {
					return "X";
				} else if (line.equals("OOO")) {
					return "O";
				}
			}

			for (int a = 0; a < 9; a++) {
				if (Arrays.asList(board).contains(String.valueOf(a+1))) {
					break;
				}
				else if (a == 8) return "draw";
			}

			System.out.println("Spieler " + turn + " ist an der Reihe; bitte gebe eine Feldzahl an:");
			return null;
		}

		static void printBoard() {
			System.out.println("/---|---|---\\");
			System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
			System.out.println("|-----------|");
			System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
			System.out.println("|-----------|");
			System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
			System.out.println("/---|---|---\\");
		}

		static void populateEmptyBoard() {
			for (int a = 0; a < 9; a++) {
				board[a] = String.valueOf(a+1);
			}
		}
	}
