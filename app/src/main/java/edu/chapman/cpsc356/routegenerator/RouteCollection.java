package edu.chapman.cpsc356.routegenerator;

import android.content.Context;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import edu.chapman.cpsc356.routegenerator.SQL.DatabaseHelper;
import edu.chapman.cpsc356.routegenerator.models.RouteModel;

public class RouteCollection
{
    private static RouteCollection collection;

    //This is probably a bad idea but it works
    private Context ctx;

    //Keep track of whether or not the collection has been loaded in from the database
    private static boolean loaded = false;

    public static RouteCollection GetInstance()
    {
        if (collection == null)
        {
            collection = new RouteCollection();

        }

        return collection;

    }

    private List<RouteModel> routes;

    private RouteCollection() {
        this.routes = new ArrayList<>();

        // Randomly generate 10 routes
        for (int i = 0; i < 5; ++i) {
            RouteModel route = new RouteModel();
            route.setTitle("Route #" + (i + 1));
            route.setStartLocation("School");
            route.setDestinationLocation("Home");
            route.setNotes("Pick up paycheck\nBring a jacket");

            this.routes.add(route);
        }

    }

    public void LoadRouteCollection(Context ctx) {
        this.ctx = ctx;

        this.routes = new ArrayList<>();

        //Retrieve routes from database

        DatabaseHelper db = new DatabaseHelper(ctx);
        List<RouteModel> list = db.getAllRoutes();
        //If the db is empty, give the user a few example routes
        if (list.isEmpty())
        {
            db.addRoute(new RouteModel("Daily Commute", "Home", "Work", "Pick up coffee\nBring a jacket", UUID.randomUUID().toString(), DateTime.now()));
            db.addRoute(new RouteModel("Mountain Trail", "Santiago River Trail", "Santa Ana River Trail", "Use left lane on river trail", UUID.randomUUID().toString(), DateTime.now()));
            list = db.getAllRoutes();
        }
        else
        {
            //The user already has a few routes
        }

        for(RouteModel r : list)
        {
            routes.add(r);
        }

        this.loaded = true;

    }

    public List<RouteModel> getRoutes()
    {
        return this.routes;
    }

    public RouteModel getRoute(String id)
    {
        for(RouteModel route : this.routes)
        {
            if (route.getId().equals(id))
            {
                return route;
            }
        }

        return null;
    }

    public int indexOf(String id)
    {

        for (int i = 0; i < this.routes.size(); i++)
        {
            if (this.routes.get(i).getId().equals(id))
            {
                return i;
            }
        }

        return 0;
    }

    public static boolean isLoaded() {
        return loaded;
    }

    public void remove(int position)
    {
        RouteModel routeToDelete = RouteCollection.GetInstance().getRoutes().get(position);

        DatabaseHelper db = new DatabaseHelper(this.ctx);
        db.deleteRoute(routeToDelete);

        this.routes.remove(position);
    }

    public void swap(int firstPosition, int secondPosition) {
        Collections.swap(routes, firstPosition, secondPosition);
    }

    public void updateDatabaseRoutes(Context ctx)
    {
        DatabaseHelper db = new DatabaseHelper(ctx);

        for (RouteModel r : routes)
        {
            db.updateRoute(r);
        }

    }
}
