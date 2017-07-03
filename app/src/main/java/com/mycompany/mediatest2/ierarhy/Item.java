package com.mycompany.mediatest2.ierarhy;
import java.util.*;

public interface Item {
	public String getTitle();
	public int getIconResource();
	public ArrayList<Item> getChilds();
}
