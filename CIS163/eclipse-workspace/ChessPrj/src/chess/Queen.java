package chess;

public class Queen extends ChessPiece{
	
	public Queen(Player player)
	{
		super(player);
	}
	public String type()
	{
		return "Queen";
	}
	
	public boolean isValidMove(Move move, IChessPiece[][] board)
	{
		if(!super.isValidMove(move, board))
			return false;
		if(board[move.fromRow][move.fromColumn].player() == Player.BLACK)
		{
			// Checks the Horizontal and Vertical direction
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
			// Checks the Diagonal directions
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
			// Checks the Horizontal and Vertical direction
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
			// Checks the Diagonal directions
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
		return false;
	}

}
