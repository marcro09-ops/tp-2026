package utils;

public class Position {
    private int col;
    private int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    public Position left() {
        return new Position(this.col-1, this.row);
    }

    public Position right() {
        return new Position(this.col+1, this.row);
    }
   
	public String toString() {
    	return "col: "+col+" row: "+row;
    }

    public boolean isHorizontallyAligned(Position p) {
        if (p == null) return false;
        return this.row == p.getRow();
    }

    public boolean isVerticallyAligned(Position p) {
        if (p == null) return false;
        return this.col == p.getCol();
    }
    public Position offset(int colOffset, int rowOffset) {
        return new Position(this.col + colOffset, this.row + rowOffset);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return this.col == position.col && this.row == position.row;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + col;
        result = prime * result + row;
        return result;
    }
    
    public boolean isInsideBoard(int numCols, int numRows) {
        return this.col >= 0 && this.col < numCols && 
               this.row >= 0 && this.row < numRows;
    }
}
