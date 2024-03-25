package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Rei;
import chess.pieces.Torre;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	public Color getCurrentPlayer() {
		return currentPlayer;
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
	
	public boolean[][] possibleMoves(ChessPosition sourceposition) {
		Position position = sourceposition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPsition(source,target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.PlacePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereisAPiece(position)) {
			throw new ChessException("nao tem peca na posicao");
		}
		if(currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("peca do adversario"); 
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("nao tem jogadas possiveis ");
			
		}
	}
	
	private void validateTargetPsition(Position source,Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("a peca nao pode ser movida para esta posicao");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private void placeNewPiece(char column , int row, ChessPiece piece) {
		board.PlacePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		placeNewPiece('c',1, new Torre(board, Color.WHITE));
		placeNewPiece('c',2, new Torre(board, Color.WHITE));
		placeNewPiece('d',2, new Torre(board, Color.WHITE));
		placeNewPiece('e',2, new Torre(board, Color.WHITE));
		placeNewPiece('e',1, new Torre(board, Color.WHITE));
		placeNewPiece('d',1, new Rei(board, Color.WHITE));
		
		placeNewPiece('c',7, new Torre(board, Color.BLACK));
		placeNewPiece('c',8, new Torre(board, Color.BLACK));
		placeNewPiece('d',7, new Torre(board, Color.BLACK));
		placeNewPiece('e',7, new Torre(board, Color.BLACK));
		placeNewPiece('e',8, new Torre(board, Color.BLACK));
		placeNewPiece('d',8, new Rei(board, Color.BLACK));
	}

}
