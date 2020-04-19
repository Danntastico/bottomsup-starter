package com.raywenderlich.android.bottomsup.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FavDB(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DB_VERSION) {
    companion object {
        private val DB_VERSION = 1
        private val DATABASE_NAME = "BeerDB"
        private val TABLE_NAME = "favoriteDB"
        private val KEY_ID = "id"
        private val BEER_IMAGE = "beerImage"
        private val BEER_NAME = "beerName"
        private val BEER_STYLE = "beerStyle"
        private val FAVORITE_STATUS = "fStatus"

        private val CREATEE_TABLE =  "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ID + " TEXT," + BEER_NAME + " TEXT," +
                BEER_STYLE + " TEXT," + BEER_IMAGE + " TEXT)"
        private val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($KEY_ID TEXT, $BEER_NAME TEXT, $BEER_STYLE TEXT, $BEER_IMAGE TEXT)"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun insertEmpty() {
        val db = this.getWritableDatabase()
        val cv = ContentValues()
        for (x in 1..10)
        {
            cv.put(KEY_ID, x)
            cv.put(FAVORITE_STATUS, "0")
        }
    }

}