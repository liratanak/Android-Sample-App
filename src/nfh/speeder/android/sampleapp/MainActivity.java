package nfh.speeder.android.sampleapp;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private static final int RESULT_SETTINGS = 1;
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	@InjectView(R.id.pager) ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_settings:
			Intent i = new Intent(this, UserSettingActivity.class);
			startActivityForResult(i, RESULT_SETTINGS);
			break;
		}
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SETTINGS:
			showUserSettings();
			break;

		}

	}
	
	private void showUserSettings() {
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		StringBuilder builder = new StringBuilder();

		builder.append("\n Username: " + sharedPrefs.getString("prefUsername", "NULL"));

		builder.append("\n Send report:" + sharedPrefs.getBoolean("prefSendReport", false));

		builder.append("\n Sync Frequency: " + sharedPrefs.getString("prefSyncFrequency", "NULL"));

		TextView settingsTextView = (TextView) findViewById(R.id.settings);

		settingsTextView.setText(builder.toString());
	}

}
