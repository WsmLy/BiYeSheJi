package Servers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainDatabase extends SQLiteOpenHelper{

	SQLiteDatabase database;
	
	public MainDatabase(Context context) {
		super(context, "mainDatabase.db", null, 1);
	}
	
	//�״δ������ݿ��ʱ����ã����������������ݿ�ʹ�����
	@Override
	public void onCreate(SQLiteDatabase db) {
		database = db;
		
		String strCreateMainTable = "create table if not exit mainTable("
				+ "_id integer primary key autoincrement,"
				+ "imageId integer,"
				+ "sellerVendorName varchar(20),"
				+ "sellerVendorId integer,"
				+ "commodityName varchar(40),"
				+ "commodityId integer,"
				+ "originalBuyDate datetime,"
				+ "originalCost float,"
				+ "currentPrice float)";
		String strCreateShoppingCarTable = "create table shoppingcarTable("
				+ "_id integer primary key autoincrement,"
				+ "commodityId integer,"
				+ "AddShoppingCarDate datetime)";
		String strInsertMainTable = "insert into mainTable("
				+ "1,R.drawable.ic_launcher,СA,0102,ƻ���ֻ�,0023,2016-2-1,"
				+ "2034,300.2)";
		database.execSQL(strCreateMainTable);
		database.execSQL(strCreateShoppingCarTable);
		database.execSQL(strInsertMainTable);
		database.execSQL(strInsertMainTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
