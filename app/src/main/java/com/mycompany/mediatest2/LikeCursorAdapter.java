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

	private ArrayList<Item> hierarchyArray; // 2

	private ArrayList<String> artists;
	private SearchingList originalListItems; // 3
	private LinkedList<ListItem> openListItems; // 4
	private LinkedList<Item> topLevelList;
	private Context ctxt;
	private String rootChose="/storage/sdcard0/Music/sets";
	
	private Cursor cursor;

    // Default constructor
    public LikeCursorAdapter(Context ctx //ArrayList<ListItem> ListItems) {  
		){
			ctxt=ctx;
		cursor = ctx.getContentResolver().query(
			MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
			null,
			"_data LIKE ?",
			new String[] {rootChose+"%"},
			MediaStore.Audio.Media.DATA);
		
		mLayoutInflater = LayoutInflater.from(ctx);
		originalListItems = new SearchingList(); 

		hierarchyArray = new ArrayList<>();
		openListItems = new LinkedList<>(); 
		topLevelList = new LinkedList<>();

		ListItem file,mainf;
		String adress,title;
		artists = new ArrayList<>();
		cursor.moveToFirst();
		
		adress=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
		title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
		
		
		Item songit =new SongItem(title,adress,
			 cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
		originalListItems.add(songit);
		//artists.add(adress);
		
		while(!adress.equals(rootChose))
		{
			adress=FolderReader.getFolder(adress);
			title=FolderReader.getName(adress);
			file =new ListItem(title,adress);
			originalListItems.add(file);
			file.addChild(songit);
			songit=file;
			//artists.add(adress);
		}
		topLevelList.add(songit);
		
		
		
		while(cursor.moveToNext())
		{

			title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
			adress=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
			SongItem topFile =new SongItem(title,adress,
					  cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
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
		ImageView img=(ImageView) view.findViewById(R.id.icon);
		
		Item ListItem = hierarchyArray.get(position);
		//ListItem ListItem=originalListItems.get(position);
		textViewTitle.setText(ListItem.getTitle());
		txtViewLabel.setText(ListItem.getAdres());
		autorView.setText(ListItem.getExtra());
		img.setImageResource(ListItem.getIconResource());
		/*title.setCompoundDrawablesWithIntrinsicBounds*/
		//(ListItem.getIconResource(), 0, 0, 0); // 6
		return view;  
	}
	
	private void generateHierarchy() {
		hierarchyArray.clear(); // 1
		generateList(topLevelList); // 2
	}

	private void generateList(List ListItems) { // 3
		
		int lastf;
		for (Item i : ListItems) {
			
			hierarchyArray.add(i);
			if (openListItems.contains(i))
			{
				ListItem L=(ListItem)i;
				generateList(L.getChilds());
			}
		}
	}
	
	private void scanData(String adress,Item mainf)
	{
		//adress=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
		
		adress=FolderReader.getFolder(adress);
		int i=originalListItems.searchItem(adress);
		if(i>-1)
		{
			
			//
			ListItem file=(ListItem)originalListItems.get(i);
			file.addChild(mainf);
			//mainf=file;

		}
		else
		{
			String titl=FolderReader.getName(adress);
			ListItem file =new ListItem(titl,adress);
			originalListItems.add(file);
			//openListItems.add(file);
			//artists.add(adress);
			//ListItem mainf =new ListItem(adress);
			//originalListItems.add(mainf);
			file.addChild(mainf);
			scanData(adress,file);
		}
		
		
	}
	
	public void clickOnItem (int position) {
		Item i = hierarchyArray.get(position);
		if(i.getClass()==SongItem.class)
		{
			showExtra(position);
		}
		else{ListItem L=(ListItem)i;
		if (!openListItems.remove(L)) 
		{openListItems.add(L);}
		generateHierarchy();
		notifyDataSetChanged();
		}
	}
	
	void showExtra(int position)
	{
		Intent intent=new Intent(ctxt,SongExt.class);
		intent.putExtra(SongExt.EXTRA_POSITION, (int)position);
		ctxt.startActivity(intent);
	}
}

