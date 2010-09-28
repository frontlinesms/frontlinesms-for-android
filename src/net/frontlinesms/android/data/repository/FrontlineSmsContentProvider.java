/**
 * 
 */
package net.frontlinesms.android.data.repository;

import android.content.Context;

import com.alxndrsn.android.utils.db.DbContentProvider;
import com.alxndrsn.android.utils.db.DbSqliteHelper;

/**
 * @author aga
 */
public class FrontlineSmsContentProvider extends DbContentProvider {
	/** DbContentProvider#getDatabaseHelper() */
	@Override
	protected DbSqliteHelper getDatabaseHelper(Context context) {
		return new FrontlineSmsSqliteHelper(context);
	}

}
