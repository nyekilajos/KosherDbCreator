package hu.bme.aut.amorg.nyekilajos.kosherfood.dbcreator;

import android.os.AsyncTask;

public class CreateDbAsync extends AsyncTask<Void, Void, Void> {

	private KosherDbCreatorActivity kosherDbCreatorActivity;

	public CreateDbAsync(KosherDbCreatorActivity kosherDbCreatorActivity) {
		this.kosherDbCreatorActivity = kosherDbCreatorActivity;
	}

	@Override
	protected void onPreExecute() {
		kosherDbCreatorActivity.showProgressDialog();
		super.onPreExecute();
	}

	@Override
	protected Void doInBackground(Void... params) {
		new KosherDbCreatorHelper(kosherDbCreatorActivity);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		kosherDbCreatorActivity.dbCreated();
		super.onPostExecute(result);
	}

}
