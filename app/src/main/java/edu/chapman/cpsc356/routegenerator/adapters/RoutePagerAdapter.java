package edu.chapman.cpsc356.routegenerator.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.chapman.cpsc356.routegenerator.RouteCollection;
import edu.chapman.cpsc356.routegenerator.fragments.RouteFragment;
import edu.chapman.cpsc356.routegenerator.models.RouteModel;


public class RoutePagerAdapter extends FragmentStatePagerAdapter
{
    public RoutePagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        RouteModel route = RouteCollection.GetInstance().getRoutes().get(position);

        RouteFragment frag = new RouteFragment();

        Bundle args = new Bundle();
        args.putString(RouteFragment.ARG_CRIME_ID, route.getId());

        frag.setArguments(args);

        return frag;
    }

    @Override
    public int getCount()
    {
        return RouteCollection.GetInstance().getRoutes().size();
    }
}
