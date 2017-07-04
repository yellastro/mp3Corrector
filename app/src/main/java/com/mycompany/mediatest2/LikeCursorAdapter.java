package com.mycompany.mediatest2;
import android.content.*;
import android.database.*;
import android.provider.*;
import android.view.*;
import android.widget.*;
import com.mycompany.mediatest2.ierarhy.*;
import java.util.*;

public class LikeCursorAdapter extends BaseAdapter
{
	private LayoutInflater mLayoutInflater;

	private ArrayList<ListItem> hierarchyArray; // 2

	private ArrayList<String> artists;
	private SearchingList originalListItems; // 3
	private LinkedList<ListItem> openListItems; // 4

	private Cursor cursor;

    // Default constructor
    public LikeCursorAdapter(Context ctx //ArrayList<ListItem> ListItems) {  
		){
			
		cursor = ctx.getContentResolver().query(
			MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
			null,
			"_data LIKE ?",
			new String[] {"/storage/sdcard0/Music/sets%"},
			MediaStore.Audio.Media.DATA);
		
		mLayoutInflater = LayoutInflater.from(ctx);
		originalListItems = new SearchingList(); 

		hierarchyArray = new ArrayList<ListItem>();
		openListItems = new LinkedList<ListItem>(); 

		ListItem file,mainf;
		String adress,title;
		artists = new ArrayList<>();
		cursor.moveToFirst();
		
		adress=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
		title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
		
		mainf =new ListItem(title,adress);
		originalListItems.add(mainf);
		//artists.add(adress);
		
		while(adress.lastIndexOf("/")>1)
		{
			adress=FolderReader.getFolder(adress);
			title=FolderReader.getName(adress);
			file =new ListItem(title,adress);
			originalListItems.add(file);
			file.addChild(mainf);
			mainf=file;
			//artists.add(adress);
		}
		
		
		
		
		while(cursor.moveToNext())
		{

			title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
			adress=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
			ListItem topFile =new ListItem(title,adress);
			originalListItems.add(topFile);
			//artists.add(adress);
			scanData(adress,topFile);
			
		
			//artists.add(ssong);
			
			
			//originalListItems.add(new ListItem(adress));
		}
		
		
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

		TextView textViewTitle = (TextView) view.findViewById(R.id.name);
		TextView txtViewLabel =(TextView) view.findViewById(R.id.other);
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

	private void generateList(ArrayList ListItems) { // 3
		for (ListItem i : ListItems) {
			hierarchyArray.add(i);
			if (openListItems.contains(i))
				generateList(i.getChilds());
		}
	}
	
	private void scanData(String adress,ListItem mainf)
	{
		//adress=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
		
		adress=FolderReader.getFolder(adress);
		int i=originalListItems.searchItem(adress);
		if(i>-1)
		{
			
			//
			ListItem file=originalListItems.get(i);
			file.addChild(mainf);
			//mainf=file;

		}
		else
		{
			String titl=FolderReader.getName(adress);
			ListItem file =new ListItem(titl,adress);
			originalListItems.add(file);
			//artists.add(adress);
			//ListItem mainf =new ListItem(adress);
			//originalListItems.add(mainf);
			file.addChild(mainf);
			scanData(adress,file);
		}
	}
}

