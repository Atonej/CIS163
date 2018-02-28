package project2;

/************************************************
A simple data structure to maintain information
about one tile in the 1024 game:

@auther Hans Dulimarta 
@version Summer 2014
************************************************/
public class Cell implements Comparable<Cell> {
    
    /** row postion of the tile */
    private int row;
    
    /** column postion of the tile */
    private int column;
    
    /** numberical value of the tile 
        a zero represents a blank tile */
    private int value;

/***********************************************
Default constructor invokes the primary
constructor with initial values
***********************************************/    
    public Cell(){
        this(0,0,0);
    }
    
/***********************************************
Constructor sets initial values
@param r row position
@param c column position
@param v numerical value
***********************************************/    
    public Cell (int r, int c, int v){
    row = r;
    column = c;
    value = v;
    }
    
/***********************************************
@return the row position
***********************************************/    
    public int getRow(){
        return this.row;
    }
    
/***********************************************
@return the column position
***********************************************/    
    public int getCol(){
        return this.column;
    }
    
/***********************************************
@return the numerical value
***********************************************/    
    public int getValue(){
        return this.value;
    }

/***********************************************
This overriden method allows ArrayList operations
to maintain sorted order.  Note that this class
'implements' Comparable which requires the following
method to be provided.

@param other a cell to be compared
@return a positive number if 'this' comes
after 'other' in sorted order
@Override
***********************************************/      
    public int compareTo (Cell other) {
        if (this.row < other.row) return -1;
        if (this.row > other.row) return +1;

        // break the tie using column 
        if (this.column < other.column) return -1;
        if (this.column > other.column) return +1;

        // break the tie using the value
        return this.getValue() - other.getValue();
    }

public void setRow(int row) {
	this.row = row;
}


public void setCol(int column) {
	this.column = column;
}

public void setValue(int value) {
	this.value = value;
}


//    public static void main(String[] args){
//    	
//    	Cell c = new Cell (1,2,31);
//    	System.out.println( c.getRow());
//    	System.out.println( c.getCol());
//    	System.out.println( c.getValue());
//    }
    
}
