package com.mycompany.mediatest2.ierarhy;

import com.mycompany.mediatest2.*;
import java.util.*;

public class ListItem
 {

	private String title;
	private ArrayList<ListItem> childs;

	public ListItem (String title) { // 1
		this.title = title;
		childs = new ArrayList<ListItem>();
	}

	//@Override
	public String getTitle() { // 2
		return title;
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
