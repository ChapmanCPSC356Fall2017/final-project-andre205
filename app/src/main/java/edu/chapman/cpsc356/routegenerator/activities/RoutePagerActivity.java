package edu.chapman.cpsc356.routegenerator.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import edu.chapman.cpsc356.routegenerator.R;
import edu.chapman.cpsc356.routegenerator.RouteCollection;
import edu.chapman.cpsc356.routegenerator.adapters.RoutePagerAdapter;

public class RoutePagerActivity extends AppCompatActivity
{
    public static final String EXTRA_ROUTE_ID = "route_id";

    private ViewPager routePager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_pager);

        this.routePager = findViewById(R.id.vp_routes);

        RoutePagerAdapter adapter = new RoutePagerAdapter(getSupportFragmentManager());
        this.routePager.setAdapter(adapter);

        String routeId = getIntent().getStringExtra(RoutePagerActivity.EXTRA_ROUTE_ID);
        int routeIndex = RouteCollection.GetInstance().indexOf(routeId);

        this.routePager.setCurrentItem(routeIndex);
    }
}
