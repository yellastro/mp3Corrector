package com.mycompany.mediatest2;

import android.app.*;
import android.database.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.view.*;
import android.widget.*;
import android.content.*;

public class MainActivity extends Activity 
{
	static Cursor cursor;
	static public LikeCursorAdapter adapter;
	ListView listView;
	SongCorrector songcorrector=new SongCorrector();
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		/*
		AdapterView.OnItemClickListener itemClickListener = 
		new AdapterView.OnItemClickListener()
		{ 
		public void onItemClick(AdapterView<?> listView, View v, int position, long id) 
		{ 
		} };*/
//Добавить слушателя к списковому представлению
		listView = (ListView) findViewById(R.id.mainListView);
		//listView.setOnItemClickListener(itemClickListener);
		
		;
		loadBase();
    }
	
	void loadBase()
	{/*
		String[] from = {
			MediaStore.MediaColumns.TITLE,MediaStore.MediaColumns.DATA};
		int[] to = {
			android.R.id.text1,android.R.id.text2};


		cursor = getContentResolver().query(
			MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
			//new String[]{"SELECT WHERE artist LIKE 'R%'"},
			null,
			//null,null,
			"_data LIKE ?",
			new String[] {"/storage/sdcard0/Music/test%"},
			MediaStore.Audio.Media.DISPLAY_NAME);
		Toast.makeText(this,//Integer.toString(
			 cursor.getColumnName(35),Toast.LENGTH_LONG).show();

		adapter = new SimpleCursorAdapter(this,
			  android.R.layout.two_line_list_item, cursor, from, to);
*/
		
		listView.setAdapter(songcorrector.loadBase(this));

			//"_data = ?",
			//new String[] {"storage/sdcard0/Music/sets"},

			
		AdapterView.OnItemClickListener itemClickListener = 
			new AdapterView.OnItemClickListener()
			{ public void onItemClick(AdapterView<?> listView, View v, int position, long id)
				{showExtra(position);}};

		listView.setOnItemClickListener(itemClickListener);
	}
	
	void showExtra(int position)
	{
		Intent intent=new Intent(MainActivity.this,SongExt.class);
		intent.putExtra(SongExt.EXTRA_POSITION, (int)position);
		startActivity(intent);
		
	
		/*Cursor cursor = adapter.getCursor();
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
		/*if(myMediaPlayer != null){
			myMediaPlayer.start();
			msgMediaPlayer = "Playing: " + playableUri + "\n";
		/*}else{
			msgMediaPlayer = "Cannot play: " + playableUri + "\n";
		}

		String info = msgMediaPlayer + "\n"
			+ "_ID: " + _id + "\n"
			+ "TITLE: " + title + "\n"
			+ "ARTIST: " + artist + "\n"
			+ "ALBUM: " + album + "\n"
			+ "DURATION: " + duration/1000 + "s";
*/
		//Toast.makeText(this, info, Toast.LENGTH_LONG).show();
	}
}
