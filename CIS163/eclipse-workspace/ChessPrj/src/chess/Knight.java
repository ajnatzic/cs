package chess;

public class Knight extends ChessPiece{
	
	public Knight(Player player)
	{
        super(player);
    }

	@Override
	public String type() {
		return "Knight";
	}
    public boolean isValidMove(Move move,IChessPiece[][] board)
    {
    		if(!super.isValidMove(move, board))
    			return false;
    		if(board[move.fromRow][move.fromColumn].player() == Player.BLACK)
    		{
    			if((move.toRow - move.fromRow) > 0 && (move.toColumn - move.fromColumn) < 0)
    			{
    				if(move.toRow == move.fromRow + 2 && move.toColumn == move.fromColumn - 1)// Checks to see if the move is a valid one when going down to the left
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( F )
    				if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn - 2)// Checks to see if the move is a valid one when going left then down
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( G )
    			}
    			if((move.toRow - move.fromRow) > 0 && (move.toColumn - move.fromColumn) > 0 )
    			{
    				if(move.toRow == move.fromRow + 2 && move.toColumn == move.fromColumn + 1 ) // Checks to see if the move is a valid one when going down to the right
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( E )
    				if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn + 2) // Checks to see if the move is a valid one when going right then down
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( D )
    			}
    			if((move.toRow - move.fromRow) < 0 && (move.toColumn - move.fromColumn) < 0)
    			{
    				if(move.toRow == move.fromRow - 2 && move.toColumn == move.fromColumn - 1) // Checks to see if the move is a valid one when going up to the left
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( A )
    				if(move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn - 2) // Checks to see if the move is a valid one when going left then up
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( H )
    			}
    			if((move.toRow - move.fromRow) < 0 && (move.toColumn - move.fromColumn) > 0)
    			{
    				if(move.toRow == move.fromRow - 2 && move.toColumn == move.fromColumn + 1) // Checks to see if the move is a valid one when going up to the right
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( B )
    				if(move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn + 2) // Checks to see if the move is a valid one when going right then up
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( C )
    			}
    		}
    		else if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
    		{
    			if((move.toRow - move.fromRow) > 0 && (move.toColumn - move.fromColumn) < 0)
    			{
    				if(move.toRow == move.fromRow + 2 && move.toColumn == move.fromColumn - 1)// Checks to see if the move is a valid one when going down to the left
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( F )
    				if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn - 2)// Checks to see if the move is a valid one when going left then down
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( G )
    			}
    			if((move.toRow - move.fromRow) > 0 && (move.toColumn - move.fromColumn) > 0 )
    			{
    				if(move.toRow == move.fromRow + 2 && move.toColumn == move.fromColumn + 1 ) // Checks to see if the move is a valid one when going down to the right
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( E )
    				if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn + 2) // Checks to see if the move is a valid one when going right then down
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( D )
    			}
    			if((move.toRow - move.fromRow) < 0 && (move.toColumn - move.fromColumn) < 0)
    			{
    				if(move.toRow == move.fromRow - 2 && move.toColumn == move.fromColumn - 1) // Checks to see if the move is a valid one when going up to the left
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( A )
    				if(move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn - 2) // Checks to see if the move is a valid one when going left then up
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( H )
    			}
    			if((move.toRow - move.fromRow) < 0 && (move.toColumn - move.fromColumn) > 0)
    			{
    				if(move.toRow == move.fromRow - 2 && move.toColumn == move.fromColumn + 1) // Checks to see if the move is a valid one when going up to the right
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( B )
    				if(move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn + 2) // Checks to see if the move is a valid one when going right then up
    					if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null) // Checks for capture of opponents piece
    						return true; // ( C )
    			}
    		}
    		return false;
    }
}
