package boardgame;

public class Board {
	
	private int rows;
	private int colums;
	private Piece[][] pieces;
	
	public Board(int rows, int colums) {
		if(rows < 1 || colums < 1) {
			throw new BoardException("Erro ao criar tabuleiro: necessario que tenha pelo menos 1 linha e 1 coluna. ");
		}
		this.rows = rows;
		this.colums = colums;
		pieces = new Piece[rows][colums];
		
	}

	public int getRows() {
		return rows;
	}

	public int getColums() {
		return colums;
	}

	public Piece piece(int row, int column) {
		if(!positionExists(row, column)) {
			
			throw new BoardException("Posicao não contem no tabuleiro");
			
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			
			throw new BoardException("Posicao não contem no tabuleiro");
			
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void PlacePiece(Piece piece, Position position) {
		if(thereisAPiece(position)) {
			throw new BoardException("Ja existe uma peca nessa posicao" + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < colums;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereisAPiece(Position position) {
		
		if(!positionExists(position)) {
			throw new BoardException("Posicao não contem no tabuleiro");
			
		}
		return piece(position) != null;
	}
	

}
