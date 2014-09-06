/*
 * Copyright (C) 2014 flzyup@ligux.com, LiGux.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ligux.tools.exrecyclerview.db;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

/**
 * Version 1.0
 * <p/>
 * Date: 2014-09-06 19:08
 * Author: flzyup@ligux.com
 * <p/>
 * Copyright © 2009-2014 LiGux.com, All right reserved.
 */

/**
 * Test {@link android.content.ContentProvider} for this demo project
 *
 * <p>It hold only one table which has online images definition</p>
 */
public class ResProvider extends ContentProvider {

    public static final String AUTHORITY = "com.ligux.tools.provider.res_provider";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "res_test";

    private static final int IMAGE_SETS = 0x01;
    private static final int IMAGE_ITEM = 0x02;

    private static final Object LOCK = new Object();

    private DBOpenHelper mOpenHelper;
    private static UriMatcher sUriMatcher;


    @Override
    public boolean onCreate() {
        mOpenHelper = new DBOpenHelper(getContext());

        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        sUriMatcher.addURI(AUTHORITY, ImageMetaData.TABLE_NAME, IMAGE_SETS);
        sUriMatcher.addURI(AUTHORITY, ImageMetaData.TABLE_NAME + "/*", IMAGE_ITEM);

        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void shutdown() {
        super.shutdown();
        if (mOpenHelper != null) {
            mOpenHelper.close();
        }
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case IMAGE_SETS:
                return ImageMetaData.CONTENT_TYPE;
            case IMAGE_ITEM:
                return ImageMetaData.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknow URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String orderBy = null;
        switch (sUriMatcher.match(uri)) {
            case IMAGE_SETS:
                qb.setTables(ImageMetaData.TABLE_NAME);
                orderBy = !TextUtils.isEmpty(sortOrder) ? sortOrder : ImageMetaData.DEFAULT_SORT_ORDER;
                break;
            case IMAGE_ITEM:
                qb.setTables(ImageMetaData.TABLE_NAME);
                qb.appendWhere(ImageMetaData._ID + " = " + uri.getPathSegments().get(1));
                orderBy = !TextUtils.isEmpty(sortOrder) ? sortOrder : ImageMetaData.DEFAULT_SORT_ORDER;
                break;
        }
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);

        if (c != null) {
            c.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (values == null) {
            throw new IllegalArgumentException("Can't insert null to database, please check!");
        }
        long rowId;
        synchronized (LOCK) {
            switch (sUriMatcher.match(uri)) {
                case IMAGE_SETS:
                    rowId = mOpenHelper.getWritableDatabase().insert(ImageMetaData.TABLE_NAME, null, values);
                    if (rowId > 0) {
                        Uri notifyUri = Uri.parse(ImageMetaData.CONTENT_URI + "/" + values.getAsString(ImageMetaData._ID));
                        getContext().getContentResolver().notifyChange(notifyUri, null);
                        return notifyUri;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        synchronized (LOCK) {
            String rowId;
            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            switch (sUriMatcher.match(uri)) {
                case IMAGE_SETS:
                    count = db.delete(ImageMetaData.TABLE_NAME, selection, selectionArgs);
                    break;
                case IMAGE_ITEM:
                    rowId = uri.getPathSegments().get(1);
                    count = db.delete(ImageMetaData.TABLE_NAME
                            , ImageMetaData._ID + " = " + rowId + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : "")
                            , selectionArgs);
                    break;
                default:
                    throw new IllegalArgumentException("Unknow URI: " + uri);
            }
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = 0;
        synchronized (LOCK) {
            switch (sUriMatcher.match(uri)) {
                case IMAGE_SETS:
                    count = db.update(ImageMetaData.TABLE_NAME, values, selection, selectionArgs);
                    break;
                case IMAGE_ITEM:
                    String rowId = uri.getPathSegments().get(1);
                    count = db.update(ImageMetaData.TABLE_NAME
                            , values
                            , ImageMetaData._ID + " = " + rowId + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : "")
                            , selectionArgs);
                    break;
                default:
                    throw new IllegalArgumentException("Unknow URI: " + uri);
            }
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    /**
     * The open helper class which extends {@link android.database.sqlite.SQLiteOpenHelper}
     */
    private class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create image table
            db.execSQL(ImageMetaData.SQL_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //TODO: The upgrade logic
        }
    }
}
