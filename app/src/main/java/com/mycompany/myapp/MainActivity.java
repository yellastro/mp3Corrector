package com.mycompany.myapp;

import android.app.*;
import android.content.*;
import android.database.*;
import android.net.*;
import android.os.*;
import android.widget.*;
import android.provider.MediaStore;
import android.util.*;
import android.media.*;
import android.view.*;
import android.widget.AdapterView.*;


public class MainActivity extends ListActivity {

	SimpleCursorAdapter adapter;
	MediaPlayer myMediaPlayer;
	Cursor cursor;
	ListView listviev;
	
	ListActivity thisis=this;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		

		AdapterView.OnItemClickListener itemClickListener = 
			new AdapterView.OnItemClickListener()
			{ 
				public void onItemClick(AdapterView<?> listView, View v, int position, long id)
				{ 
				 } };
//Добавить слушателя к списковому представлению
		ListView listView = (ListView) findViewById(R.id.mainListView);
	}
	
	public void init()
	{
		
		String[] from = {
			MediaStore.MediaColumns.TITLE};
		int[] to = {
			android.R.id.text1};
		
		
		 cursor = getContentResolver().query(
			MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
			//new String[]{"SELECT WHERE artist LIKE 'R%'"},
			null,
			//null,null,
			"_data LIKE ?",
			new String[] {"/storage/sdcard0/Music/sets/Монеточка%"},
			
			MediaStore.Audio.Media.DISPLAY_NAME);
			Toast.makeText(this,//Integer.toString(
			cursor.getColumnName(35),Toast.LENGTH_LONG).show();
			
		adapter = new SimpleCursorAdapter(thisis,
			android.R.layout.simple_list_item_1, cursor, from, to);
		
		listviev.setAdapter(adapter);
		
		//"_data = ?",
		//new String[] {"storage/sdcard0/Music/sets"},
		
		
	}
	
	void showFileExta(int position)
	{
		Cursor cursor = adapter.getCursor();
		cursor.moveToPosition(position);

		String _id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
		String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
		String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
		String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
		int duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

		Uri playableUri
			= Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, _id);
		//myMediaPlayer = MediaPlayer.create(getBaseContext(), playableUri);
		String msgMediaPlayer;
		if(myMediaPlayer != null){
			myMediaPlayer.start();
			msgMediaPlayer = "Playing: " + playableUri + "\n";
		}else{
			msgMediaPlayer = "Cannot play: " + playableUri + "\n";
		}

		String info = msgMediaPlayer + "\n"
			+ "_ID: " + _id + "\n"
			+ "TITLE: " + title + "\n"
			+ "ARTIST: " + artist + "\n"
			+ "ALBUM: " + album + "\n"
			+ "DURATION: " + duration/1000 + "s";

		Toast.makeText(thisis, info, Toast.LENGTH_LONG).show();
	}

}
