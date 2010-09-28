/**
 * 
 */
package com.alxndrsn.android.utils.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * @author aga
 */
public abstract class DbContentProvider extends ContentProvider {
	private DbSqliteHelper helper;
	
	/** @see ContentProvider#delete(Uri, String, String[]) */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		String whereClause = DbUtils.getWhereClause(uri, selection);
		return helper.getWritableDatabase().delete(DbUtils.getTableName(uri), whereClause, selectionArgs);
	}

	/** @see ContentProvider#getType(Uri) */
	@Override
	public String getType(Uri uri) {
		// TODO Check if URI is plain and return mixed
		// TODO check if URI matches entity class and return appropriate MIME
		// If no type is matched, return null
		return null;
	}

	/** @see ContentProvider#insert(Uri, ContentValues) */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long insertId = this.helper.getWritableDatabase().insert(DbUtils.getTableName(uri), "_id", values);
		return ContentUris.withAppendedId(uri, insertId);
	}

	/** @see ContentProvider#onCreate() */
	@Override
	public boolean onCreate() {
		this.helper = getDatabaseHelper(getContext());
		return true;
	}

	protected abstract DbSqliteHelper getDatabaseHelper(Context context);

	/** @see ContentProvider#query(Uri, String[], String, String[], String) */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return this.helper.getReadableDatabase().query(DbUtils.getTableName(uri),
				projection, selection, selectionArgs, null, null, null);
	}

	/** @see ContentProvider#update(Uri, ContentValues, String, String[]) */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return this.helper.getReadableDatabase().update(DbUtils.getTableName(uri),
				values, selection, selectionArgs);
	}

}
