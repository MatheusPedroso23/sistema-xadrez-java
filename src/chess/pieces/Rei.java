package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Rei extends ChessPiece {
	
	private ChessMatch chessMattch;

	public Rei(Board board, Color color, ChessMatch chessMattch) {
		super(board, color);
		this.chessMattch = chessMattch;
	}
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Torre && p.getColor() == getColor() && p.getMoveCount() == 0;
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColums()];
		
		Position p = new Position(0, 0);
		
		//cima
		p.setValues(position.getRow() - 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//baixo
		p.setValues(position.getRow() + 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//esquerda
		p.setValues(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//noroeste
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//nordeste
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//sudoeste
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//sudeste
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// specialemove castling
		if(getMoveCount() == 0 && !chessMattch.getCheck()) {
			// castling roque pequeno
			Position posT1 = new Position(position.getRow(), position.getColumn()+3);
			if(testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColumn()+1);
				Position p2 = new Position(position.getRow(), position.getColumn()+2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn()+2] = true;
				}
			}
			// castling roque grande
						Position posT2 = new Position(position.getRow(), position.getColumn()-4);
						if(testRookCastling(posT2)) {
							Position p1 = new Position(position.getRow(), position.getColumn()-1);
							Position p2 = new Position(position.getRow(), position.getColumn()-2);
							Position p3 = new Position(position.getRow(), position.getColumn()-3);
							if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
								mat[position.getRow()][position.getColumn()-2] = true;
							}
					}
		}
		
		return mat;
	}

}
