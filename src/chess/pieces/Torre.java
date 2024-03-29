package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Torre extends ChessPiece{

	public Torre(Board board, Color color) {
		super(board, color);
	}
	@Override
	public String toString() {
		return"T";
	}
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColums()];
		
		Position p = new Position(0, 0);
		
		//cima
		p.setValues(position.getRow() - 1, position.getColumn());
		while(getBoard().positionExists(p) && !getBoard().thereisAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if(getBoard().positionExists(p) && pecaOponentePosicao(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//esquerda
				p.setValues(position.getRow(), position.getColumn() - 1);
				while(getBoard().positionExists(p) && !getBoard().thereisAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setColumn(p.getColumn() - 1);
				}
				if(getBoard().positionExists(p) && pecaOponentePosicao(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
		//direita
				p.setValues(position.getRow(), position.getColumn() + 1);
				while(getBoard().positionExists(p) && !getBoard().thereisAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setColumn(p.getColumn() + 1);
				}
				if(getBoard().positionExists(p) && pecaOponentePosicao(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
		//baixo
				p.setValues(position.getRow() + 1, position.getColumn());
				while(getBoard().positionExists(p) && !getBoard().thereisAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setRow(p.getRow() + 1);
				}
				if(getBoard().positionExists(p) && pecaOponentePosicao(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
		
		return mat;
	}
		
}
