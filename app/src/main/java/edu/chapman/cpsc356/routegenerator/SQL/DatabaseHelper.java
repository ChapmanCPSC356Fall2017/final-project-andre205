package edu.chapman.cpsc356.routegenerator.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.joda.time.DateTime;

import java.util.LinkedList;
import java.util.List;

import edu.chapman.cpsc356.routegenerator.models.RouteModel;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 12;
    // Database Name
    private static final String DATABASE_NAME = "RouteDB";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create route table
        String CREATE_ROUTE_TABLE = "CREATE TABLE routes ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "start TEXT, "+
                "dest TEXT, "+
                "notes TEXT, "+
                "uuid TEXT, "+
                "date TEXT)";

        db.execSQL(CREATE_ROUTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older routes table if existed
        db.execSQL("DROP TABLE IF EXISTS routes");
        this.onCreate(db);
    }
    //---------------------------------------------------------------------


    private static final String TABLE_ROUTES = "routes";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_START = "start";
    private static final String KEY_DEST = "dest";
    private static final String KEY_NOTES = "notes";
    private static final String KEY_UUID = "uuid";
    private static final String KEY_DATE = "date";

    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_START,KEY_DEST,KEY_NOTES,KEY_UUID,KEY_DATE};

    //Append a new route to the end of the database
    public void addRoute(RouteModel route){
        Log.d("addRoute", route.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, route.getTitle()); // get title
        values.put(KEY_START, route.getStartLocation());
        values.put(KEY_DEST, route.getDestinationLocation());
        values.put(KEY_NOTES, route.getNotes());
        values.put(KEY_UUID, route.getId());
        values.put(KEY_DATE, route.getDate().toString());

        db.insert(TABLE_ROUTES,
                null,
                values);

        db.close();
    }

    //Fetch a route from the database by id (only used for indexing)
    public RouteModel getRoute(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_ROUTES, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        RouteModel route = new RouteModel();

        route.setTitle(cursor.getString(1));
        route.setStartLocation(cursor.getString(2));
        route.setDestinationLocation(cursor.getString(3));
        route.setNotes(cursor.getString(4));
        route.setId(cursor.getString(5));
        route.setDate(DateTime.parse(cursor.getString(6)));

        Log.d("getRoute("+id+")", route.toString());

        return route;
    }

    // Get All Routes
    public List<RouteModel> getAllRoutes() {
        List<RouteModel> routes = new LinkedList<RouteModel>();

        String query = "SELECT  * FROM " + TABLE_ROUTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        RouteModel route = null;
        if (cursor.moveToFirst()) {
            do {
                route = new RouteModel();
                route.setTitle(cursor.getString(1));
                route.setStartLocation(cursor.getString(2));
                route.setDestinationLocation(cursor.getString(3));
                route.setNotes(cursor.getString(4));
                route.setId(cursor.getString(5));
                route.setDate(DateTime.parse(cursor.getString(6)));

                routes.add(route);
            } while (cursor.moveToNext());
        }

        Log.d("getAllRoutes()", routes.toString());

        return routes;
    }

    // Update a single route BY UUID NOT INT ID
    public int updateRoute(RouteModel route) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, route.getTitle()); // get title
        values.put(KEY_START, route.getStartLocation());
        values.put(KEY_DEST, route.getDestinationLocation());
        values.put(KEY_NOTES, route.getNotes());
        values.put(KEY_UUID, route.getId());
        values.put(KEY_DATE, route.getDate().toString());

        int i = db.update(TABLE_ROUTES, //table
                values, // column/value
                KEY_UUID+" = ?", // selections
                new String[] { String.valueOf(route.getId()) }); //selection args

        db.close();

        return i;
    }

    // Deleting single route
    public void deleteRoute(RouteModel route) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_ROUTES,
                KEY_UUID+" = ?",
                new String[] { String.valueOf(route.getId()) });

        db.close();

        Log.d("deleteRoute", route.toString());

    }
}