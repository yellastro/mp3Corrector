package com.mycompany.mediatest2.ierarhy;

import com.mycompany.mediatest2.*;
import java.util.*;

public class ListItem
 {

	private String title,adress;
	private ArrayList<ListItem> childs;

	public ListItem (String title,String adf) { // 1
		this.title = title;
		this.adress=adf;
		childs = new ArrayList<ListItem>();
	}

	//@Override
	public String getTitle() { // 2
		return title;
	}
	
	public String getAdres()
	{
		return adress;
	}

	//@Override
	public ArrayList<ListItem> getChilds() { // 3
		return childs;
	}

	//@Override
	public int getIconResource() { // 4
		if (childs.size() > 0)
			return R.drawable.folder;
		return R.drawable.song;
	}

	public int addChild (ListItem item) { // 5
		childs.add(item);
		return childs.size()-1;
	}
}
