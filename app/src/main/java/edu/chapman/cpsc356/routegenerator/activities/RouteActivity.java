package edu.chapman.cpsc356.routegenerator.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import edu.chapman.cpsc356.routegenerator.fragments.RouteFragment;
import edu.chapman.cpsc356.routegenerator.models.RouteModel;

public class RouteActivity extends SingleFragmentActivity
{
    public static final String EXTRA_ROUTE_ID = "route_id";

    public static Intent BuildIntent(RouteModel route, Context ctx)
    {
        Intent i = new Intent(ctx, RouteActivity.class);
        i.putExtra(RouteActivity.EXTRA_ROUTE_ID, route.getId());

        return i;
    }

    @Override
    protected Fragment getFragment()
    {
        Bundle extras = getIntent().getExtras();

        RouteFragment frag = new RouteFragment();
        frag.setArguments(extras);

        return frag;
    }
}
