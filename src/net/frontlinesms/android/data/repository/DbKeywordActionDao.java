/**
 * 
 */
package net.frontlinesms.android.data.repository;

import java.util.List;
import java.util.TreeSet;

import net.frontlinesms.android.data.domain.KeywordAction;

import com.alxndrsn.android.utils.db.BaseDbAccessObject;

import android.content.ContentResolver;

/**
 * @author aga
 */
public class DbKeywordActionDao extends BaseDbAccessObject implements KeywordActionDao {
	public DbKeywordActionDao(ContentResolver contentResolver) {
		super(contentResolver);
	}

	@Override
	public void addAction(KeywordAction action) {
		super.save(action);
	}

	@Override
	public KeywordAction[] getActions(String messageContent) {
		KeywordAction example = new KeywordAction();
		example.setKeyword(KeywordAction.getKeyword(messageContent));
		return super.get(example).toArray(new KeywordAction[0]);
	}

	@Override
	public String[] getKeywords() {
		// TODO could re-implement this to directly select unique keywords
		List<KeywordAction> actions = super.get(KeywordAction.class);
		TreeSet<String> keywords = new TreeSet<String>();
		for(KeywordAction action : actions) {
			keywords.add(action.getKeyword());
		}
		return keywords.toArray(new String[0]);
	}

	@Override
	protected String getBaseUri() {
		return FrontlineSmsSqliteHelper.CONTENT_URI;
	}
}
