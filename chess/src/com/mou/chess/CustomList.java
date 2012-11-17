package com.mou.chess;


import java.util.ArrayList;
import java.util.Collection;

public class CustomList<Piece> extends ArrayList<Piece>{
	
	public CustomList()
	{
		super();
	}
	
	public CustomList(int a)
	{
		super(a);
	}
	public Boolean equals(CustomList<Piece> list)
	{
		Boolean temp;
		if(this.size() == list.size())
		{
			temp = true;
			for(int i=0; i<this.size(); i++)
			{
				if(!this.get(i).equals(list.get(i)))
				{
					temp = false;
					break;
				}
			}
		}
		else
			temp = false;
		return temp;
	}
	

}
