package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Peao extends ChessPiece{
	
	private ChessMatch chessMatch;

	public Peao(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColums()];	
		Position p = new Position(0, 0);
		if(getColor() == Color.WHITE) {
			p.setValues(position.getRow()-1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereisAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; 
			}
			p.setValues(position.getRow()-2, position.getColumn());
			Position p2 = new Position(position.getRow()-1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereisAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereisAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true; 
			}
			p.setValues(position.getRow()-1, position.getColumn()-1);
			if(getBoard().positionExists(p) && pecaOponentePosicao(p)) {
				mat[p.getRow()][p.getColumn()] = true; 	
			}
			p.setValues(position.getRow()-1, position.getColumn()+1);
			if(getBoard().positionExists(p) && pecaOponentePosicao(p)) {
				mat[p.getRow()][p.getColumn()] = true; 
			}
			
			//enpassant white
			if(position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if(getBoard().positionExists(left) && pecaOponentePosicao(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				} 
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if(getBoard().positionExists(right) && pecaOponentePosicao(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				} 
			}
		}
		else {
			p.setValues(position.getRow()+1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereisAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true; 
			}
			p.setValues(position.getRow()+2, position.getColumn());
			Position p2 = new Position(position.getRow()+1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereisAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereisAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true; 
			}
			p.setValues(position.getRow()+1, position.getColumn()-1);
			if(getBoard().positionExists(p) && pecaOponentePosicao(p)) {   
				mat[p.getRow()][p.getColumn()] = true; 	
			}
			p.setValues(position.getRow()+1, position.getColumn()+1);
			if(getBoard().positionExists(p) && pecaOponentePosicao(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//enpassant black
			if(position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if(getBoard().positionExists(left) && pecaOponentePosicao(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				} 
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if(getBoard().positionExists(right) && pecaOponentePosicao(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				} 
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	

}
