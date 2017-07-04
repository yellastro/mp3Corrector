package com.mycompany.mediatest2;

import android.app.*;
import android.database.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.util.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import java.io.*;
import com.mycompany.mediatest2.ierarhy.*;
import java.util.*;

public class SongCorrector
{
	static Cursor cursor;
	static BaseAdapter adapter;
	static String titel,artist,fileNames,newFileName,folder;
	static File file, newFile;
	static ArrayList<String> artists=new ArrayList<>();
	static ArrayList<String> albums=new ArrayList<>();
	static ArrayList<ListItem> items=new ArrayList<>();
	
	
	
	static public BaseAdapter  loadBase(Activity activity)
	{
		String[] from = {
			MediaStore.MediaColumns.TITLE,MediaStore.MediaColumns.DATA};
		int[] to = {
			R.id.name,R.id.autor_list};

		cursor = activity.getContentResolver().query(
			MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
			null,
			"_data LIKE ?",
			new String[] {"/storage/sdcard0/Music/sets%"},
			MediaStore.Audio.Media.DATA);
		
		//String[] tables=new String[20];
		
		/*for (int i=0;i<20;i++)
		{
			String tables=
			cursor.getString(cursor.getColumnIndex( MediaStore.Audio.Media.TITLE ));
		/*Toast.makeText(activity,//Integer.toString(
					   tables,Toast.LENGTH_LONG).show();
		
		//}*/
		/*ListItem item;
		cursor.moveToFirst();
		while(cursor.moveToNext())
		{
			
			String ssong=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
			/*int i=artists.indexOf(ssong);
			if(i<0)*l
			{
				artists.add(ssong);
				items.add(new ListItem(ssong));
			}
			//item=items.get(i);
	
				/*ssong=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
				
				
				if(albums.indexOf(ssong)<0)
				{
					albums.add(ssong);
					item.addChild(new ListItem(ssong));
				}
			
			ssong=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
			 //item=item.getChilds().get(i);
		
				//albums.add(ssong);
				//item.addChild(new ListItem(ssong));
			artists.add(ssong);
		}
			*/
		//folder=getFolder(ssong);
		/*if(folders.indexOf(
		ListItem it=new ListItem(folder);
		it.addChild(new ListItem(ssong));
		*/
		//return new AdresserAdapter(activity, artists);
		return new LikeCursorAdapter(activity);
		//return adapter = new MyCursorAdapter(activity,
					//	 cursor,R.layout.rowlayout);
	}
	
	static void initInfo(int position)
	{
		cursor.moveToPosition(position);

		//String _id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));

		titel = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
		artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
		newFileName=
		(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
		fileNames=getName(newFileName);
		folder=getFolder(newFileName);
		
		//trackNo= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK));
		/*file=new File(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
		fileNames=file.getName();
		
		folder=getFolder(file.toString());*/
		//int lengOfFolder=folder.lastIndexOf("/");
		//folder=folder.substring(0,lengOfFolder);

		newFileName=titel+" - "+artist+".mp3";

		
	}
	
	static void renameSong()
	{
		if(file.getName()!=newFileName)//нужен метод с сравнением всех форматов
		{
			newFile= new File(folder+"/"+newFileName);
			file.renameTo(newFile);
			fileNames=file.getName();
		}else
		{
			fileNames="Error";
		}
		
	}
	
	static String getFolder(String Adress)
	{
		int lengOfFolder=Adress.lastIndexOf("/")+1;
		return Adress.substring(0,lengOfFolder);
	}
	
	static String getName(String name)
	{
		int lengOfFolder=name.lastIndexOf("/")+1;
		return name.substring(lengOfFolder);
	}
}
