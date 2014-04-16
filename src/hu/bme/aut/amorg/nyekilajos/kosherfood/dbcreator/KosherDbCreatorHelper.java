package hu.bme.aut.amorg.nyekilajos.kosherfood.dbcreator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class KosherDbCreatorHelper extends SQLiteOpenHelper {

	public static final String FOODS_TABLE = "foods";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_IS_KOSHER = "is_kosher";
	public static final String COLUMN_INFORMATION = "information";

	public static final String NOT_KOSHER_PAIRS_TABLE = "not_kosher_pairs";
	public static final String COLUMN_FOOD_ID_FIRST = "food_id_first";
	public static final String COLUMN_FOOD_ID_SECOND = "food_id_second";

	protected static final String DATABASE_NAME = "kosher.db";
	protected static final int DATATBASE_VERSION = 1;

	protected static final String DATABASE_PATH = Environment
			.getDataDirectory()
			+ "/data/hu.bme.aut.amorg.nyekilajos.kosherfood/databases/";

	private SQLiteDatabase database;

	protected static final String CREATE_FOODS = "create table " + FOODS_TABLE
			+ "(" + COLUMN_ID + " integer primary key not null, " + COLUMN_NAME
			+ " text not null, " + COLUMN_IS_KOSHER + " integer not null, "
			+ COLUMN_INFORMATION + " text not null);";

	protected static final String CREATE_NOT_KOSHER_PAIRS = "create table "
			+ NOT_KOSHER_PAIRS_TABLE + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_FOOD_ID_FIRST
			+ " integer not null, " + COLUMN_FOOD_ID_SECOND
			+ " integer not null, " + COLUMN_INFORMATION + " text not null);";

	public KosherDbCreatorHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_FOODS);
		db.execSQL(CREATE_NOT_KOSHER_PAIRS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + FOODS_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + NOT_KOSHER_PAIRS_TABLE);
		onCreate(db);
	}

	@Override
	public synchronized void close() {
		database.close();
		super.close();
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		this.database = this.getWritableDatabase();
		truncateFoods();
		truncateNotKosherPairs();
		initDatabase();
		this.close();
	}

	private void initDatabase() {

		Foods food = new Foods();
		food.set_id(1);
		food.setName("pig");
		food.setIs_kosher(0);
		food.setInformation("Jews must not eat pork!");
		insert(food);

		food.set_id(2);
		food.setName("bug");
		food.setIs_kosher(0);
		food.setInformation("Jews must not eat bug!");
		insert(food);

		food.set_id(3);
		food.setName("milk");
		food.setIs_kosher(1);
		food.setInformation("Jews can drink milk!");
		insert(food);

		food.set_id(4);
		food.setName("chicken");
		food.setIs_kosher(1);
		food.setInformation("Jews can eat chicken!");
		insert(food);

		food.set_id(5);
		food.setName("egg");
		food.setIs_kosher(1);
		food.setInformation("Jews can eat egg!");
		insert(food);

		food.set_id(6);
		food.setName("fish");
		food.setIs_kosher(1);
		food.setInformation("Jews can eat fish!");
		insert(food);

		food.set_id(7);
		food.setName("goose");
		food.setIs_kosher(0);
		food.setInformation("Jews must not eat goose!");
		insert(food);

		NotKosherPairs notKosherPairs = new NotKosherPairs();

		notKosherPairs.setFood_first_id(3);
		notKosherPairs.setFood_second_id(4);
		notKosherPairs
				.setInformation("Jews must not eat chicken and drink milk together!");
		insert(notKosherPairs);

		notKosherPairs.setFood_first_id(3);
		notKosherPairs.setFood_second_id(5);
		notKosherPairs
				.setInformation("Jews must not eat egg and drink milk together!");
		insert(notKosherPairs);

		notKosherPairs.setFood_first_id(3);
		notKosherPairs.setFood_second_id(6);
		notKosherPairs
				.setInformation("Jews must not eat fish and drink milk together!");
		insert(notKosherPairs);
	}

	public Foods insert(Foods food) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_ID, food.get_id());
		values.put(COLUMN_NAME, food.getName());
		values.put(COLUMN_IS_KOSHER, food.getIs_kosher());
		values.put(COLUMN_INFORMATION, food.getInformation());
		try {
			database.beginTransaction();
			database.insert(FOODS_TABLE, null, values);
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
		return food;
	}

	public NotKosherPairs insert(NotKosherPairs notKosherPairs) {
		ContentValues values = new ContentValues();
		String nullString = null;
		values.put(COLUMN_ID, nullString);
		values.put(COLUMN_FOOD_ID_FIRST, notKosherPairs.getFood_first_id());
		values.put(COLUMN_FOOD_ID_SECOND, notKosherPairs.getFood_second_id());
		values.put(COLUMN_INFORMATION, notKosherPairs.getInformation());
		database.insert(NOT_KOSHER_PAIRS_TABLE, COLUMN_ID, values);
		return notKosherPairs;
	}

	public void truncateFoods() {
		try {
			database.beginTransaction();
			database.execSQL("delete from " + FOODS_TABLE + ";");
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
	}

	public void truncateNotKosherPairs() {
		try {
			database.beginTransaction();
			database.execSQL("delete from " + NOT_KOSHER_PAIRS_TABLE + ";");
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
	}

}
