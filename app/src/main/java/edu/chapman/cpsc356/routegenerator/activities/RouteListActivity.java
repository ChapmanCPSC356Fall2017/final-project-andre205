package edu.chapman.cpsc356.routegenerator.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import edu.chapman.cpsc356.routegenerator.R;
import edu.chapman.cpsc356.routegenerator.RouteCollection;
import edu.chapman.cpsc356.routegenerator.SQL.DatabaseHelper;
import edu.chapman.cpsc356.routegenerator.fragments.RouteListFragment;
import edu.chapman.cpsc356.routegenerator.models.RouteModel;

public class RouteListActivity extends SingleFragmentActivity
{
    private RouteListFragment fragment;

    @Override
    protected Fragment getFragment()
    {
        this.fragment = new RouteListFragment();
        return this.fragment;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_route_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_add_route:

                RouteModel newRoute = new RouteModel();
                newRoute.setTitle("New Route");
                //Add the newly generated route to the bottom of the list (max index)
                RouteCollection.GetInstance().getRoutes().add(RouteCollection.GetInstance().getRoutes().size(), newRoute);

                //add it to the database as well
                DatabaseHelper db = new DatabaseHelper(this);
                db.addRoute(newRoute);

                //start a route activity to let the user customize their route
                Intent routeIntent = RouteActivity.BuildIntent(newRoute, this);
                startActivity(routeIntent);

                return true;


            case R.id.menu_settings:

                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            default:
                return false;
        }
    }
}
