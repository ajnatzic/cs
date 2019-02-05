package chess;

public class Rook extends ChessPiece
{
	public Rook(Player player)
	{
        super(player);
    }

	public String type() {
		return "Rook";
	}
    public boolean isValidMove(Move move,IChessPiece[][] board)
    {
    		if(!super.isValidMove(move, board))
    			return false;// This will call the main method of the parent class and check its conditions
    		
    		// Checks to make sure the piece is only moved up/down || left/right
    		if( (move.toRow != move.fromRow && move.toColumn == move.fromColumn) || (move.toRow == move.fromRow && move.toColumn != move.fromColumn))
    		{
    			// Will execute if the piece is a black one
        		if(board[move.fromRow][move.fromColumn].player() == Player.BLACK)
        		{
        			// Checks to see if there are any pieces in the Rook's movement going vertically down
        			// Will execute only if there is at least one space between the current position to the target position
        			if((move.toRow - move.fromRow) > 0 && move.toColumn == move.fromColumn)
        			{
        				for(int row = move.fromRow + 1; row < move.toRow; row++)
        					if(board[row][move.fromColumn] != null) // Checks the vertical-down direction
        						return false;
        				if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
        					return true;
        			}
        			if((move.toRow - move.fromRow) < 0 && move.toColumn == move.fromColumn)
        			{
        				for(int row = move.fromRow - 1; row > move.toRow; row--)
        					if(board[row][move.fromColumn] != null) // Checks the vertical-up direction
        						return false;
        				if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
        					return true;
        			}
        			if((move.toColumn - move.fromColumn) > 0 && move.toRow == move.fromRow)
        			{
        				for(int col = move.fromColumn + 1; col < move.toColumn; col++)
        					if(board[move.fromRow][col] != null) // Checks the horizontal-right direction
        						return false;
        				if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
        					return true;
        			}
        			if((move.toColumn - move.fromColumn) < 0 && move.toRow == move.fromRow)
        			{
        				for(int col = move.fromColumn - 1; col > move.toColumn; col--)
        					if(board[move.fromRow][col] != null) // Checks the horizontal-left direction
        						return false;
        				if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
        					return true;
        			}
        		}
        		else if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
        		{
        			// Checks to see if there are any pieces in the Rook's movement going vertically down
        			// Will execute only if there is at least one space between the current position to the target position
        			if((move.toRow - move.fromRow) > 0 && move.toColumn == move.fromColumn)
        			{
        				for(int row = move.fromRow + 1; row < move.toRow; row++)
        					if(board[row][move.fromColumn] != null) // Checks the vertical-down direction
        						return false;
        				if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
        					return true;
        			}
        			if((move.toRow - move.fromRow) < 0 && move.toColumn == move.fromColumn)
        			{
        				for(int row = move.fromRow - 1; row > move.toRow; row--)
        					if(board[row][move.fromColumn] != null) // Checks the vertical-up direction
        						return false;
        				if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
        					return true;
        			}
        			if((move.toColumn - move.fromColumn) > 0 && move.toRow == move.fromRow)
        			{
        				for(int col = move.fromColumn + 1; col < move.toColumn; col++)
        					if(board[move.fromRow][col] != null) // Checks the horizontal-right direction
        						return false;
        				if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
        					return true;
        			}
        			if((move.toColumn - move.fromColumn) < 0 && move.toRow == move.fromRow)
        			{
        				for(int col = move.fromColumn - 1; col > move.toColumn; col--)
        					if(board[move.fromRow][col] != null) // Checks the horizontal-left direction
        						return false;
        				if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
        					return true;
        			}
        		}	
    		}
    		else
    			return false;
    		
    		return false;
    		
    }

}
