package edu.chapman.cpsc356.routegenerator.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.chapman.cpsc356.routegenerator.R;
import edu.chapman.cpsc356.routegenerator.RouteCollection;
import edu.chapman.cpsc356.routegenerator.activities.RoutePagerActivity;
import edu.chapman.cpsc356.routegenerator.models.RouteModel;


public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.RouteViewHolder>
{
    private final String LOGTAG = "RouteListAdapter";

    @Override
    public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(LOGTAG, "onCreateViewHolder()");

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cell_route, parent, false);

        return new RouteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RouteViewHolder holder, int position)
    {
        Log.d(LOGTAG, "onBindViewHolder(" + position + ")");
        // Get route at specified position
        RouteModel route = RouteCollection.GetInstance().getRoutes().get(position);

        // Setup the ViewHolder
        holder.setup(route);
    }

    @Override
    public int getItemCount()
    {
        Log.d(LOGTAG, "getItemCount()");
        return RouteCollection.GetInstance().getRoutes().size();
    }

    public void remove(int position)
    {
        RouteCollection.GetInstance().remove(position);
        notifyItemRemoved(position);
    }

    public void swap(int firstPosition, int secondPosition)
    {
        RouteCollection.GetInstance().swap(firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }

    class RouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private RouteModel route;

        private TextView titleTextView;

        public RouteViewHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);

            this.titleTextView = itemView.findViewById(R.id.tv_title);
        }

        public void setup(RouteModel route)
        {
            this.route = route;

            this.titleTextView.setText(route.getTitle());
        }

        @Override
        public void onClick(View view)
        {
            Intent routeIntent = new Intent(view.getContext(), RoutePagerActivity.class);
            routeIntent.putExtra(RoutePagerActivity.EXTRA_ROUTE_ID, this.route.getId());

            view.getContext().startActivity(routeIntent);
        }


    }
}
