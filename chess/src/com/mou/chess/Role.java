package com.mou.chess;


public class Role {
	
	public static final int BISHOP = 2;
	public static final int PAWN = 1;
	public static final int ROOK = 3;
	public static final int KNIGHT = 4;
	public static final int QUEEN = 5;
	public static final int KING = 6;
	
		
	//ideally this should return a type of move based upon which pieces should be moved
	public static Boolean isValidMove(int role1, int role2, int x1, int y1, int x2, int y2, int colour)
	{
		
		Boolean val = false;
		
		switch(role1)
		{
		case Role.BISHOP:
			{
				if((x1+y1)==(x2+y2) || (x1-y1)==(x2-y2) || (y1-x1)==(y2-x2))
				{
					if(x1<x2 && y1>y2)
					{
						Boolean temp = true;
						for(int x=x1+1, y=y1-1; x<=x2 || y>=y2; x++, y--)
						{
							Cell cell = Board.INSTANCE.getCell(x, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
								
						}
						val = temp;
					}
					else if(x1>x2 && y1<y2)
					{
						boolean temp = true;
						
						for(int x=x1-1, y=y1+1; x>=x2 || y<=y2; x--, y++)
						{
							Cell cell = Board.INSTANCE.getCell(x, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
								
						}
						val = temp;
					}
					else if(x1>x2 && y1>y2)
					{
						boolean temp = true;
						
						for(int x=x1-1, y=y1-1; x>=x2 || y>=y2; x--, y--)
						{
							Cell cell = Board.INSTANCE.getCell(x, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
								
						}
						val = temp;
					}
					else if(x1<x2 && y1<y2)
					{
						boolean temp = true;
						
						for(int x=x1+1, y=y1+1; x<=x2 || y<=y2; x++, y++)
						{
							Cell cell = Board.INSTANCE.getCell(x, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
								
						}
						val = temp;
					}
					
				}
				
			}
			break;
		case Role.KING:
			{
				if((x2-x1)==1 || (x1-x2)==1 || (y1-y2)==1 || (y2-y1)==1)
					val = true;
				else if ((x1==0 || x1==7) && y1==4 && x1==x2 && ((y1-y2)==2 || (y2-y1)==2)) //castling
				{
					if(y1>y2)
					{
						Cell king = Board.INSTANCE.getCell(x1, y1);
						Cell rook = Board.INSTANCE.getCell(x1, 7);
						if(rook.getIsOccupied())
						{
							if(rook.getPiece().getRole()==Role.ROOK && !rook.getPiece().isMoved() && !king.getPiece().isMoved())
							{
								Boolean temp = true;
								for(int y=y1-1; y>=y2; y--)
								{
									Cell cell = Board.INSTANCE.getCell(x1, y);
									if(cell.getIsOccupied())
									{
										temp = false;
										break;
									}
								}
								val = temp;
								Board.simpleMovePiece(x1, 0, x1, 3, "Succesfully castled.");
							}
						}
					}
					else if(y1<y2)
					{
						Cell king = Board.INSTANCE.getCell(x1, y1);
						Cell rook = Board.INSTANCE.getCell(x1, 7);
						if(rook.getIsOccupied())
						{
							if(rook.getPiece().getRole()==Role.ROOK && !rook.getPiece().isMoved() && !king.getPiece().isMoved())
							{
								Boolean temp = true;
								for(int y=y1+1; y<=y2; y++)
								{
									Cell cell = Board.INSTANCE.getCell(x1, y);
									if(cell.getIsOccupied())
									{
										temp = false;
										break;
									}
								}
								val = temp;
								Board.simpleMovePiece(x1, 7, x1, 5, "Succesfully castled.");
							}
						}
					}
				}
				
			}
			break;
		case Role.KNIGHT:
			{
				if((diff(x1,x2)==2 && diff(y1,y2)==1) || (diff(x1,x2)==1 && diff(y1,y2)==2))
					val = true;
				
			}
			break;
		case Role.PAWN:
			{
				if(colour==Player.BLACK)
				{
					if(role2==0 && (x2-x1==1 || (x2-x1==2 && x1==1)) && y1==y2)
					{
						val = true;
					}
					else if(role2!=0 && x2-x1==1 && diff(y1,y2)==1) //pawn captures piece
						val = true;
					else if(x1==4 && x2==5 && diff(y1,y2)==1)//en passant move
					{
						Cell cell = Board.INSTANCE.getCell(4, y2);
						if(cell.getIsOccupied() && cell.getPiece().getRole()==Role.PAWN)
						{
							val = true;
							Board.capturePiece(4, y2);
						}
					}
				
				}
					
				else if(colour==Player.WHITE)
				{
					if(role2==0 && (x1-x2==1 || (x1-x2==2 && x1==6)) && y1==y2)
					{
						val = true;
					}
					else if(role2!=0 && x1-x2==1 && diff(y1,y2)==1)
						val = true;
					else if(x1==3 && x2==2 && diff(y1,y2)==1)//en passant move
					{
						Cell cell = Board.INSTANCE.getCell(3, y2);
						if(cell.getIsOccupied() && cell.getPiece().getRole()==Role.PAWN)
						{
							val = true;
							Board.capturePiece(3, y2);
						}
					}
				
				}
				
			}
			break;
		case Role.QUEEN:
			{
				if(x1==x2)
				{
					if(y1<y2)
					{
						Boolean temp = true;
						
						for(int y=y1+1; y<=y2; y++)
						{
							Cell cell = Board.INSTANCE.getCell(x1, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
						}
						val = temp;
					}
					else if(y1>y2)
					{
						Boolean temp = true;
						
						for(int y=y1-1; y>=y2; y--)
						{
							Cell cell = Board.INSTANCE.getCell(x1, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
						}
						val = temp;
					}
				}
				else if(y1==y2)
				{
					if(x1<x2)
					{
						Boolean temp = true;
						
						for(int x=x1+1; x<=x2; x++)
						{
							Cell cell = Board.INSTANCE.getCell(x, y1);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
						}
						val = temp;
					}
					else if(x1>x2)
					{
						Boolean temp = true;
						
						for(int x=x1-1; x>=x2; x--)
						{
							Cell cell = Board.INSTANCE.getCell(x, y1);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
						}
						val = temp;
					}
				}
				else if((x1+y1)==(x2+y2) || (x1-y1)==(x2-y2) || (y1-x1)==(y2-x2))
				{
					if(x1<x2 && y1>y2)
					{
						Boolean temp = true;
						for(int x=x1+1, y=y1-1; x<=x2 || y>=y2; x++, y--)
						{
							Cell cell = Board.INSTANCE.getCell(x, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
								
						}
						val = temp;
					}
					else if(x1>x2 && y1<y2)
					{
						boolean temp = true;
						
						for(int x=x1-1, y=y1+1; x>=x2 || y<=y2; x--, y++)
						{
							Cell cell = Board.INSTANCE.getCell(x, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
								
						}
						val = temp;
					}
					else if(x1>x2 && y1>y2)
					{
						boolean temp = true;
						
						for(int x=x1-1, y=y1-1; x>=x2 || y>=y2; x--, y--)
						{
							Cell cell = Board.INSTANCE.getCell(x, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
								
						}
						val = temp;
					}
					else if(x1<x2 && y1<y2)
					{
						boolean temp = true;
						
						for(int x=x1+1, y=y1+1; x<=x2 || y<=y2; x++, y++)
						{
							Cell cell = Board.INSTANCE.getCell(x, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
								
						}
						val = temp;
					}
					
				}
				
				
			}
			break;
		case Role.ROOK:
			{
				if(x1==x2)
				{
					if(y1<y2)
					{
						Boolean temp = true;
						
						for(int y=y1+1; y<=y2; y++)
						{
							Cell cell = Board.INSTANCE.getCell(x1, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
						}
						val = temp;
					}
					else if(y1>y2)
					{
						Boolean temp = true;
						
						for(int y=y1-1; y>=y2; y--)
						{
							Cell cell = Board.INSTANCE.getCell(x1, y);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
						}
						val = temp;
					}
				}
				else if(y1==y2)
				{
					if(x1<x2)
					{
						Boolean temp = true;
						
						for(int x=x1+1; x<=x2; x++)
						{
							Cell cell = Board.INSTANCE.getCell(x, y1);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
						}
						val = temp;
					}
					else if(x1>x2)
					{
						Boolean temp = true;
						
						for(int x=x1-1; x>=x2; x--)
						{
							Cell cell = Board.INSTANCE.getCell(x, y1);
							if(cell.getIsOccupied())
							{
								temp = false;
								break;
							}
						}
						val = temp;
					}
				}
			}
			break;
		default:
			{
				val = false;
				
			}
			break;
		}
		
		return val;
	}
	
	public static int diff(int a, int b)
	{
		if(a>=b)
			return a-b;
		else
			return b-a;
	}
}
