package com.duffin22.marketingapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mgkan on 2016-08-17.
 */
public class MyDBHandler extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "superSickDB.db";
  public static final String TABLE_SUPERSICKS = "supersicks";

  public static final String COLUMN_SYMBOL = "symbol";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_EXCHANGE = "exchange";
  public static final String COLUMN_QUANTITY = "quantity";

  public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    super(context, DATABASE_NAME, factory, DATABASE_VERSION);
  }
  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_SUPERSICKS_TABLE = "CREATE TABLE " +
      TABLE_SUPERSICKS + "("
      + COLUMN_SYMBOL+ " TEXT,"
      + COLUMN_NAME + " TEXT,"
      + COLUMN_EXCHANGE+ " TEXT,"
      + COLUMN_QUANTITY + " INTEGER" + ")";
    db.execSQL(CREATE_SUPERSICKS_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPERSICKS);
    onCreate(db);
  }

  public long addProduct(ContentValues values) {
    SQLiteDatabase db = getWritableDatabase();
    long insertedRow = db.insert(TABLE_SUPERSICKS, null, values);
    db.close();
    return insertedRow;
  }

  public Cursor findProductByName(String selection) {
    String[] projection = {COLUMN_SYMBOL,COLUMN_NAME,COLUMN_EXCHANGE,COLUMN_QUANTITY};

    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.query(TABLE_SUPERSICKS,projection,selection,null,null,null,null);

    return cursor;
  }

  public int deleteProductByName(String selection) {
    SQLiteDatabase db = getWritableDatabase();

    int rowsDeleted = db.delete(TABLE_SUPERSICKS,selection,null);
    db.close();
    return rowsDeleted;
  }

  public int updateProduct(ContentValues values, String selection){
    SQLiteDatabase db = getWritableDatabase();

    int updatedRows = 0;

      updatedRows = db.update(TABLE_SUPERSICKS,values,selection);

    db.close();
    return updatedRows;
  }
}
