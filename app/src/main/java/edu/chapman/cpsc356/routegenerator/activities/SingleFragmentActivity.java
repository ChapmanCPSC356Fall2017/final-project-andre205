package edu.chapman.cpsc356.routegenerator.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import edu.chapman.cpsc356.routegenerator.R;
import edu.chapman.cpsc356.routegenerator.RouteCollection;
import edu.chapman.cpsc356.routegenerator.SQL.DatabaseHelper;

public abstract class SingleFragmentActivity extends AppCompatActivity
{
    protected abstract Fragment getFragment();

    DatabaseHelper db;
    boolean s = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);


        //Populate the route collection from the database
        RouteCollection rc = RouteCollection.GetInstance();
        if(!rc.isLoaded())
        {
            rc.LoadRouteCollection(this);
        }

        showFragment(getFragment());
    }

    protected void onPause(Bundle SavedInstanceState)
    {
        RouteCollection rc = RouteCollection.GetInstance();
        rc.updateDatabaseRoutes(this);
    }


    private void showFragment(Fragment frag)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_fragment_container, frag)
                .commit();
    }
}
