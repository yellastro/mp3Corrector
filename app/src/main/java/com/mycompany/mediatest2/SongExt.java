package com.mycompany.mediatest2;
import android.app.*;
import android.database.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;

public class SongExt extends Activity
{
	public static final String EXTRA_POSITION="position";

	TextView nameView,artistView,filenameView,finalNameView,folderView;
	String titel,artist,trackNo,fileNames,newFileName;
	Cursor cursor;
	File file;
	
	private String LOG_TAG;
	@Override
	public void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.song_extra);
		
		//Intent intent = getIntent();
		int position =(Integer)getIntent().getExtras().get(EXTRA_POSITION);
		initInfo(position);
		
	}
	
	public void onRenameClick(View vv)
	{
		//readFile(file.getAbsolutePath());
		SongCorrector.renameSong();
		filenameView.setText(SongCorrector.fileNames);
	}
	
	void initInfo(int position)
	{
		
		nameView=(TextView)findViewById(R.id.song_name);
		artistView=(TextView)findViewById(R.id.song_autor);
		filenameView=(TextView)findViewById(R.id.song_filename);
		finalNameView=(TextView)findViewById(R.id.song_finalName);
		folderView=(TextView)findViewById(R.id.song_fileFolder);
		//(Integer.toString( position));
		//cursor =MainActivity.cursor;
		//MainActivity.adapter.getCursor();
		/*cursor.moveToPosition(position);

		String _id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
		
		*/
		
		SongCorrector.initInfo(position);
		
		;
		
		/*
		titel = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
		artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
		//trackNo= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK));
		file=new File(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
		fileNames=file.getName();
		*/
		nameView.setText(SongCorrector.titel);
		artistView.setText(SongCorrector.artist);
		filenameView.setText(SongCorrector.fileNames);
		folderView.setText(SongCorrector.folder);
		
		//newFileName=titel+" - "+artist+".mp3";
		
		finalNameView.setText(SongCorrector.newFileName);
		
		/*Uri playableUri
			= Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, _id);
		*/
		//ImageView img=(ImageView)findViewById(R.id.song_extraImageView);
		//img.setImageURI(playableUri);
		//playableUri.get
		//m
		
	}
	void readFile(String DIR_SD)
	{
	// проверяем доступность SD
    if (!Environment.getExternalStorageState().equals(
	Environment.MEDIA_MOUNTED)) {
		Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
		return;
    }
    // получаем путь к SD
    //File sdPath =new File( DIR_SD);
	
    // добавляем свой каталог к пути
    //sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
    
	fileNames=file.toString();
	int lengOfFolder=fileNames.lastIndexOf("/")+1;
	fileNames=fileNames.substring(0,lengOfFolder);
	fileNames=fileNames+newFileName;
	File newFiles=new File(fileNames);
	file.renameTo(newFiles);
	
	
	filenameView.setText(file.getName());
	
	// формируем объект File, который содержит путь к файлу
    /*File sdFile = new File(sdPath, FILEnameView_SD);
	
    try {
		// открываем поток для чтения
		BufferedReader br = new BufferedReader(new FileReader(sdFile));
		String str = "";
		str=br.readLine();
		// читаем содержимое
		while ((str = br.readLine()) != null) {
			Log.d(LOG_TAG, str);
		}
    } catch (FileNotFoundException e) {
		e.printStackTrace();
    } catch (IOException e) {
		e.printStackTrace();
    }*/
	}
}
