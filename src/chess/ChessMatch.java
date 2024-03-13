package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() {
		
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColums()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColums(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j);
			}
			
		}
		return mat;
	}
	
	private void initialSetup() {
		board.PlacePiece(new Torre(board, Color.WHITE), new Position(2, 1));
		board.PlacePiece(new Rei(board, Color.BLACK), new Position(0, 4));
		board.PlacePiece(new Torre(board, Color.BLACK), new Position(7, 4));
	}

}
