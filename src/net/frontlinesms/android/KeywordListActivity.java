package net.frontlinesms.android;

import net.frontlinesms.android.data.domain.KeywordAction;
import net.frontlinesms.android.data.repository.DbKeywordActionDao;
import net.frontlinesms.android.data.repository.KeywordActionDao;
import net.frontlinesms.android.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class KeywordListActivity extends ListActivity {
    private static final int MENU_NEW_KEYWORD = 0;
	private static final int MENU_HELP = 1;
	
	private KeywordActionDao actionDao;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionDao = new DbKeywordActionDao(getContentResolver());
        if(actionDao.getKeywords().length == 0) {
        	createDemoKeywords();
        }
        
		String[] keywords = actionDao.getKeywords();
        setListAdapter(new ArrayAdapter<String>(this, R.layout.keyword_list_item, keywords));
        
        ListView listView = getListView();
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// When clicked, show the details of the keyword and its actions
			}
		});
    }
    
    private void createDemoKeywords() {
		// Populate the map with some keyword actions
		actionDao.addAction(KeywordAction.createReplyAction("hi", "Hello there, " + PropertySubstituter.KEY_SENDER_NAME
				+ ", i do like your phone number (" + PropertySubstituter.KEY_SENDER_PHONENUMBER + ")"));
		actionDao.addAction(KeywordAction.createJoinAction("join", "demoGroup"));
		actionDao.addAction(KeywordAction.createJoinAction("stop", "demoGroup"));
		actionDao.addAction(KeywordAction.createForwardAction("say", 
				PropertySubstituter.KEY_SENDER_NAME + " says " + PropertySubstituter.KEY_ORIGINAL_MESSAGE,
				"demoGroup"));
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0, MENU_NEW_KEYWORD, 0, R.string.action_new);
    	menu.add(0, MENU_HELP, 0, R.string.action_help);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case MENU_NEW_KEYWORD: return _handlerMenuOptionNewKeyword();
    	case MENU_HELP: return _handleMenuOptionHelp();
    	default: return false;
    	}
    }
	private boolean _handleMenuOptionHelp() {
		Toast.makeText(this, "Not implemented?!", 3).show();
		return false;
	}
	private boolean _handlerMenuOptionNewKeyword() {
		Toast.makeText(this, "Not implemented?!", 3).show();
		return false;
	}
}