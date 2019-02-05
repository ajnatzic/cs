package chess;

public class Bishop extends ChessPiece
{
	public Bishop(Player player)
	{
		super(player);
	}
	public String type()
	{
		return "Bishop";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board)
	{
		if(!super.isValidMove(move, board))
			return false;
		// Checks to make sure player is not trying to move straight
		if((move.toRow != move.fromRow && move.toColumn != move.fromColumn))
		{
			if(board[move.fromRow][move.fromColumn].player() == Player.BLACK)
			{
				// Checks to see if move is valid going diagonally north-east
				if((move.toRow - move.fromRow) < 0 && (move.toColumn - move.fromColumn) > 0)
					if((move.toRow - move.fromRow) == (move.fromColumn - move.toColumn))
					{
						int SIZE = move.fromRow - move.toRow;
						for(int m = 1; m < SIZE; m++)
							if(board[move.fromRow - m][move.fromColumn + m] != null) // Checks to see if there is a clear path going diagonally north-east
								return false;
						if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
							return true;
					}
				// Checks to see if move is valid going diagonally north-west
				if((move.toRow - move.fromRow) < 0 && (move.toColumn - move.fromColumn) < 0)
					if((move.fromRow - move.toRow) == (move.fromColumn - move.toColumn))
					{
						int SIZE = move.fromRow - move.toRow;
						for(int m = 1; m < SIZE; m++)
							if(board[move.fromRow - m][move.fromColumn - m] != null) // Checks to see if there is a clear path going diagonally north-west
								return false;
						if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
							return true;
					}
				// Checks to see if move is valid going diagonally south-west
				if((move.toRow - move.fromRow) > 0 && (move.toColumn - move.fromColumn) < 0)
					if((move.toRow - move.fromRow) == (move.fromColumn - move.toColumn))
					{
						int SIZE = move.toRow - move.fromRow;
						for(int m = 1; m < SIZE; m++)
							if(board[move.fromRow + m][move.fromColumn - m] != null) // Checks to see if there is a clear path going diagonally south-west
								return false;
						if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
							return true;
					}
				// Checks to see if move is valid going diagonally south-east
				if((move.toRow - move.fromRow) > 0 && (move.toColumn - move.fromColumn) > 0)
					if((move.toRow - move.fromRow) == (move.toColumn - move.fromColumn))
					{
						int SIZE = move.toRow - move.fromRow;
						for(int m = 1; m < SIZE; m++)
							if(board[move.fromRow + m][move.fromColumn + m] != null) // Checks to see if there is a clear path going diagonally south-east
								return false;
						if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
							return true;
					}
			}
			else if(board[move.fromRow][move.fromColumn].player() == Player.WHITE)
			{
				// Checks to see if move is valid going diagonally north-east
				if((move.toRow - move.fromRow) < 0 && (move.toColumn - move.fromColumn) > 0)
					if((move.toRow - move.fromRow) == (move.fromColumn - move.toColumn))
					{
						int SIZE = move.fromRow - move.toRow;
						for(int m = 1; m < SIZE; m++)
							if(board[move.fromRow - m][move.fromColumn + m] != null) // Checks to see if there is a clear path going diagonally north-east
								return false;
						
						if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
							return true;
					}
				// Checks to see if move is valid going diagonally north-west
				if((move.toRow - move.fromRow) < 0 && (move.toColumn - move.fromColumn) < 0)
					if((move.fromRow - move.toRow) == (move.fromColumn - move.toColumn))
					{
						int SIZE = move.fromRow - move.toRow;
						for(int m = 1; m < SIZE; m++)
							if(board[move.fromRow - m][move.fromColumn - m] != null) // Checks to see if there is a clear path going diagonally north-west
								return false;
						if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
							return true;
					}
				// Checks to see if move is valid going diagonally south-west
				if((move.toRow - move.fromRow) > 0 && (move.toColumn - move.fromColumn) < 0)
					if((move.toRow - move.fromRow) == (move.fromColumn - move.toColumn))
					{
						int SIZE = move.toRow - move.fromRow;
						for(int m = 1; m < SIZE; m++)
							if(board[move.fromRow + m][move.fromColumn - m] != null) // Checks to see if there is a clear path going diagonally south-west
								return false;
						if(board[move.toRow][move.toColumn] != null || board[move.toRow][move.toColumn] == null)
							return true;
					}
				// Checks to see if move is valid going diagonally south-east
				if((move.toRow - move.fromRow) > 0 && (move.toColumn - move.fromColumn) > 0)
					if((move.toRow - move.fromRow) == (move.toColumn - move.fromColumn))
					{
						int SIZE = move.toRow - move.fromRow;
						for(int m = 1; m < move.toRow; m++)
							if(board[move.fromRow + m][move.fromColumn + m] != null) // Checks to see if there is a clear path going diagonally south-east
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
