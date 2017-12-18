package br.com.vitorsalgado.androidstarter.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.vitorsalgado.androidstarter.logger.CLog;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	@NonNull
	private final BriteDatabase mBriteDatabase;

	public DbHelper(@NonNull Context context, @NonNull String database, @NonNull boolean doLog) {
		super(context, database, null, DATABASE_VERSION);

		SqlBrite sqlBrite = new SqlBrite.Builder()
				.logger(message -> CLog.d(message))
				.build();

		mBriteDatabase = sqlBrite.wrapDatabaseHelper(this, Schedulers.io());
		mBriteDatabase.setLoggingEnabled(doLog);
	}

	@NonNull
	public BriteDatabase getBriteDb() {
		return mBriteDatabase;
	}

	@Override
	public void onCreate(@NonNull SQLiteDatabase db) {
		db.beginTransaction();

		try {
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	@Override
	public void onConfigure(@NonNull SQLiteDatabase db) {
		super.onConfigure(db);
		db.setForeignKeyConstraintsEnabled(true);
	}
}
