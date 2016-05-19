package com.example.patternapplication.model.db;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;

/**
 * Created by 1 on 19.05.2016.
 */
public class DBLoader extends CursorLoader {

    private IDBModel db;
    private Bundle args;

    public DBLoader(Context context, Bundle args, IDBModel db) {
        super(context);
        this.db = db;
        this.args = args;
    }

    @Override
    public Cursor loadInBackground() {
        return db.getNewDBCursor();
    }
}
