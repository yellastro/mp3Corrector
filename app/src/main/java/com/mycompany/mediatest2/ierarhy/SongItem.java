package com.mycompany.mediatest2.ierarhy;
import com.mycompany.mediatest2.*;

public class SongItem implements Item
{
	String  title,adress,extra;

	public SongItem(String t,String a,String e)
	{
		title=t;
		adress=a;
		extra=e;
	}
	
	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public String getAdres()
	{
		return FolderReader.getName(adress);
	}

	@Override
	public String getExtra()
	{
		return extra;
	}


	

	@Override
	public int getIconResource()
	{
		return R.drawable.song;
	}

	
}
