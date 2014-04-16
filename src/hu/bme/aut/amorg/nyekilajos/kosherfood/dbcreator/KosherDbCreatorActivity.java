package hu.bme.aut.amorg.nyekilajos.kosherfood.dbcreator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class KosherDbCreatorActivity extends Activity {

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kosher_db_creator);
		CreateDbAsync createDbAsync = new CreateDbAsync(this);
		createDbAsync.execute();
	}

	public void showProgressDialog() {
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("Working...");
		progressDialog.setMessage("Database creation... Please wait!");
		progressDialog.show();
	}

	public void dbCreated() {
		progressDialog.dismiss();

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("Kosher Db created!");
		alertDialogBuilder.setPositiveButton("OK", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}

		});
		alertDialogBuilder.show();
	}

}
