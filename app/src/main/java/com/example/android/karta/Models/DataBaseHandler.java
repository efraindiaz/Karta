package com.example.android.karta.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Root on 29/07/2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    //DB
    private static final int DATABASE_VERSION  = 1;
    private static final String DATABASE_NAME  = "account";
    private static final String TABLE          = "user";

    //Table columns
    private static final String ID       = "id";
    private static final String ID_INFO_USER_CONSUMER = "id_user";
    private static final String NAME     = "name";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL    = "email";
    private static final String PASSWORD = "password";
    private static final String PHONE    = "phone";
    private static final String TOKEN   = "token";

    public DataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //SQL sentence
        String SQL = "CREATE TABLE " + TABLE +
                "(" + ID + " INTEGER PRIMARY KEY," + ID_INFO_USER_CONSUMER + " TEXT " + TOKEN + " TEXT," + NAME + " TEXT, " +
                LASTNAME + " TEXT," + EMAIL + " TEXT," + PASSWORD + " TEXT," + PHONE + " TEXT" + ")";

        db.execSQL(SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE);
        onCreate(db);
    }

    //CRUD
    public boolean saveUser(User user)
    {
        SQLiteDatabase db    = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID_INFO_USER_CONSUMER, user.getId_info_user_consumer());
        values.put(TOKEN  , user.getToken());
        values.put(NAME    , user.getName());
        values.put(LASTNAME, user.getLast_name());
        values.put(EMAIL   , user.getEmail());
        values.put(PASSWORD, user.getPassword());
        values.put(PHONE   , user.getPhone());

        //Inserting Row
        db.insert(TABLE, null, values);
        db.close();

        return  true;
    }

    public void trunkTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL = "DELETE FROM " + TABLE;
        db.execSQL(SQL);
    }
    /*
    public User getUser()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =  db.query(
                TABLE,
                new String[] {
                        ID,
                        APIKEY,
                        NAME,
                        LASTNAME,
                        EMAIL,
                        PASSWORD,
                        PHONE
                },
                ID + "= ?",
                new String[]{
                        String.valueOf(1)
                },
                null, null, null, null
        );

        if(getUserCount() > 0){
            cursor.moveToFirst();
            User user = new User(
                    Integer.parseInt(
                            cursor.getString(0)), //id local table
                    cursor.getString(1), //apikey
                    cursor.getString(2), //name
                    cursor.getString(3), //lastname
                    cursor.getString(4), //email
                    cursor.getString(5), //password
                    cursor.getString(6) //phone
            );
            return user;
        } else {
            return null;
        }
    }

    //update data user
    public int updateUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME    , user.getName());
        values.put(LASTNAME, user.getLastname());
        //values.put(EMAIL   , user.getEmail());
        values.put(PASSWORD, user.getPassword());
        values.put(PHONE   , user.getPhone());

        return db.update(TABLE,
                values, ID + " = ?",
                new String[] { String.valueOf(user.getId())});
    }

    public int updateUserPassword(String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PASSWORD, password);

        return db.update(TABLE, values, ID + " = ?", new String[] { String.valueOf(1)});
    }

    //never used
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + " = ?", new String[] {String.valueOf(user.getId())});
        db.close();
    }

    // Getting contacts Count
    public int getUserCount() {
        String countQuery = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int total = cursor.getCount();
        cursor.close();

        // return count
        return total;
    }*/
}
