package edu.chapman.cpsc356.routegenerator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.chapman.cpsc356.routegenerator.R;
import edu.chapman.cpsc356.routegenerator.adapters.RouteListAdapter;
import edu.chapman.cpsc356.routegenerator.adapters.RouteListTouchHelper;


public class RouteListFragment extends Fragment
{
    private RouteListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_route_list, container, false);

        RecyclerView routesListView = v.findViewById(R.id.rv_routes);

        this.adapter = new RouteListAdapter();
        routesListView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new RouteListTouchHelper(this.adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(routesListView);

        routesListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        this.adapter.notifyDataSetChanged();
    }

    public void notifyDataChanged()
    {
        this.adapter.notifyDataSetChanged();
    }
}
