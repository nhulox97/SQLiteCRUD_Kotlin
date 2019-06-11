package com.nhulox.eds.sqlitecrud.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.nhulox.eds.sqlitecrud.utils.Keys.Companion.COL_CONTENT
import com.nhulox.eds.sqlitecrud.utils.Keys.Companion.COL_ID
import com.nhulox.eds.sqlitecrud.utils.Keys.Companion.COL_TITLE
import com.nhulox.eds.sqlitecrud.utils.Keys.Companion.DB_NAME
import com.nhulox.eds.sqlitecrud.utils.Keys.Companion.DB_TABLE
import com.nhulox.eds.sqlitecrud.utils.Keys.Companion.DB_VERSION


/** * Created by nhulox97 on 29,mayo,2019 */
class NoteDbManager{

    private val CREATE_TABLE_SQL =
        "CREATE TABLE IF NOT EXISTS $DB_TABLE ($COL_ID INTEGER PRIMARY KEY,$COL_TITLE TEXT, $COL_CONTENT TEXT);"
    private var db: SQLiteDatabase? = null

    constructor(context: Context){
        var dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase

    }

    fun insert(values: ContentValues): Long{
        //val ID = db!!.insert(DB_TABLE, "", values)
        return db!!.insert(DB_TABLE, "", values)
    }

    fun queryAll(): Cursor{
        return db!!.rawQuery("select * from $DB_TABLE", null)
    }

    fun delete(selection: String, selectionArgs: Array<String>): Int{
        //val count = db!!.delete(DB_TABLE, selection, selectionArgs)
        return db!!.delete(DB_TABLE, selection, selectionArgs)
    }

    fun update(values: ContentValues, selection: String, selectionArgs: Array<String>): Int{
        //val count = db!!.update(DB_TABLE, values, selection, selectionArgs)
        return db!!.update(DB_TABLE, values, selection, selectionArgs)
    }

    inner class DatabaseHelper: SQLiteOpenHelper{
        var context: Context? = null

        constructor(context: Context): super(context, DB_NAME, null, DB_VERSION){
            this.context = context
        }
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(CREATE_TABLE_SQL)
            Toast.makeText(this.context, "Database is created", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS $DB_TABLE")
        }

    }
}