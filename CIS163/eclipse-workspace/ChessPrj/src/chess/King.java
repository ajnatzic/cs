package chess;

public class King extends ChessPiece
{

	public King(Player player)
	{
		super(player);
	}
	public String type()
	{
		return "King";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board)
	{
		boolean valid = false;
		if(!super.isValidMove(move, board))
			return false;
		
		if(board[move.fromRow][move.fromColumn].player() == Player.BLACK)
		{
			if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn) // Checks to see if piece is moving up one space
				valid = true;
			if(move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn) // Checks to see if piece is moving back one space
				valid = true;
			if(move.toColumn == move.fromColumn + 1 && move.toRow == move.fromRow) // Checks to see if piece is moving right one space
				valid = true;
			if(move.toColumn == move.fromColumn - 1 && move.toRow == move.fromRow) // Checks to see if piece is moving left one space
				valid = true;
			if(move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn - 1) // checks to see if piece is moved in the NW direction
				valid = true;
			if(move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn + 1) // checks to see if piece is moved in the NE direction
				valid = true;
			if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn + 1) // checks to see if piece is moved in the SE direction
				valid = true;
			if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn - 1) // checks to see if piece is moved in the SW direction
				valid = true;
			
			if(board[move.toRow][move.toColumn] != null && valid)
				return true;
		}
		else if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
		{
			if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn) // Checks to see if piece is moving up one space
				valid = true;
			if(move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn) // Checks to see if piece is moving back one space
				valid = true;
			if(move.toColumn == move.fromColumn + 1 && move.toRow == move.fromRow) // Checks to see if piece is moving right one space
				valid = true;
			if(move.toColumn == move.fromColumn - 1 && move.toRow == move.fromRow) // Checks to see if piece is moving left one space
				valid = true;
			if(move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn - 1) // checks to see if piece is moved in the NW direction
				valid = true;
			if(move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn + 1) // checks to see if piece is moved in the NE direction
				valid = true;
			if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn + 1) // checks to see if piece is moved in the SE direction
				valid = true;
			if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn - 1) // checks to see if piece is moved in the SW direction
				valid = true;
			
			if(board[move.toRow][move.toColumn] != null && valid)
				return true;
		}
		return valid;
	}
}
