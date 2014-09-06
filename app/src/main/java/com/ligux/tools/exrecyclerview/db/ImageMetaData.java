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

import android.content.ContentValues;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Version 1.0
 * <p/>
 * Date: 2014-09-06 19:28
 * Author: flzyup@ligux.com
 * <p/>
 * Copyright © 2009-2014 LiGux.com, All right reserved.
 */

/**
 * Image table which record the basic online image info: name, url
 */
public class ImageMetaData implements BaseColumns {
    public static final String TABLE_NAME = "res";

    public static final Uri CONTENT_URI = Uri.parse("content://" + ResProvider.AUTHORITY + "/" + TABLE_NAME);

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ligux.tools.test.image";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.ligux.tools.test.image";

    public static final String COL_NAME = "name";
    public static final String COL_URL = "url";

    public static final String DEFAULT_SORT_ORDER = _ID + " DESC";
    /**
     * The create sql for this table
     */
    public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY, "
            + COL_NAME + " TEXT, "
            + COL_URL + " TEXT"
            + ");";

    /**
     * The primary key id
     */
    public int _id;

    /**
     * Image name
     */
    public String name;

    /**
     * The url of this image
     */
    public String url;

    public ImageMetaData(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public ContentValues fill() {
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_URL, url);
        return cv;
    }
}
