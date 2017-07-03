package com.mycompany.mediatest2;
import android.content.*;
import android.view.*;
import android.widget.*;
import com.mycompany.mediatest2.*;
import com.mycompany.mediatest2.ierarhy.*;
import java.util.*;

public class LikeCursorAdapter extends BaseAdapter
{
	private LayoutInflater mLayoutInflater;

	private ArrayList<ListItem> hierarchyArray; // 2

	private ArrayList<ListItem> originalListItems; // 3
	private LinkedList<ListItem> openListItems; // 4


    // Default constructor
    public LikeCursorAdapter(Context ctx, ArrayList<ListItem> ListItems) {  
		mLayoutInflater = LayoutInflater.from(ctx);
		originalListItems = ListItems; 

		hierarchyArray = new ArrayList<ListItem>();
		openListItems = new LinkedList<ListItem>(); 

		generateHierarchy(); // 5
	}  

	@Override
	public int getCount() {
		return hierarchyArray.size();
	}

	
	public Object getItem(int position) {
		return hierarchyArray.get(position);
	}

	
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null)
			view = mLayoutInflater.inflate(R.layout.rowlayout, null);             

		TextView textViewTitle = (TextView) view.findViewById(R.id.label1);
		TextView txtViewLabel =(TextView) view.findViewById(R.id.label2);
		TextView autorView=(TextView) view.findViewById(R.id.autor_list);
		
		ListItem ListItem = hierarchyArray.get(position);
		//ListItem ListItem=originalListItems.get(position);
		textViewTitle.setText(ListItem.getTitle());
		/*title.setCompoundDrawablesWithIntrinsicBounds*/
		//(ListItem.getIconResource(), 0, 0, 0); // 6
		return view;  
	}
	
	private void generateHierarchy() {
		hierarchyArray.clear(); // 1
		generateList(originalListItems); // 2
	}

	private void generateList(ArrayList<ListItem> ListItems) { // 3
		for (ListItem i : ListItems) {
			hierarchyArray.add(i);
			if (openListItems.contains(i))
				generateList(i.getChilds());
		}
	}
}

