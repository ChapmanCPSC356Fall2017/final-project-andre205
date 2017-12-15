package edu.chapman.cpsc356.routegenerator.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import edu.chapman.cpsc356.routegenerator.R;
import edu.chapman.cpsc356.routegenerator.RouteCollection;
import edu.chapman.cpsc356.routegenerator.SQL.DatabaseHelper;
import edu.chapman.cpsc356.routegenerator.SharedPrefHelper;
import edu.chapman.cpsc356.routegenerator.models.RouteModel;


public class RouteFragment extends Fragment
{
    private final String LOGTAG = "RouteFragment";
    public final static String ARG_CRIME_ID = "route_id";

    private EditText titleEditText;

    private EditText startEditText;
    private EditText destinationEditText;

    private EditText notesEditText;

    private Button dateButton;

    private RouteModel route;

    private DatabaseHelper db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        String routeId = getArguments().getString(RouteFragment.ARG_CRIME_ID);
        this.route = RouteCollection.GetInstance().getRoute(routeId);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_route, container, false);

        db = new DatabaseHelper(this.getContext());

        this.titleEditText = v.findViewById(R.id.et_title);
        this.titleEditText.setText(this.route.getTitle());

        this.startEditText = v.findViewById(R.id.et_start);
        this.startEditText.setText(this.route.getStartLocation());

        this.destinationEditText = v.findViewById(R.id.et_destination);
        this.destinationEditText.setText(this.route.getDestinationLocation());

        this.notesEditText = v.findViewById(R.id.et_notes);
        this.notesEditText.setText(this.route.getNotes());

        this.titleEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                Log.d(LOGTAG, "Text changed! to " + editable.toString());
                route.setTitle(editable.toString());
                db.updateRoute(route);
            }
        });

        startEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                route.setStartLocation(editable.toString());
                db.updateRoute(route);
            }
        });

        destinationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                route.setDestinationLocation(editable.toString());
                db.updateRoute(route);
            }
        });

        notesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                route.setNotes(editable.toString());
                db.updateRoute(route);
            }
        });

        this.dateButton = v.findViewById(R.id.btn_created_date);
        dateButton.setText(this.route.getDate().toString(DateTimeFormat.longDate()));

        this.dateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DateDialogFragment frag = DateDialogFragment.GetInstance(route.getDate());
                frag.setTargetFragment(RouteFragment.this, DateDialogFragment.REQUEST_CODE);

                frag.show(getFragmentManager(), null);
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_route, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_share_route:

                String defaultShareText = SharedPrefHelper.GetDefaultShareText(getContext());

                String shareMessage = defaultShareText + "\n" + this.route.getTitle() +
                        "\n From " + this.route.getStartLocation() +
                        " to " + this.route.getDestinationLocation();

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

                startActivity(shareIntent);

                return true;
            case R.id.menu_delete_route:

                final RouteModel r = this.route;

                AlertDialog alert = new AlertDialog.Builder(getContext())
                        .setTitle("Delete Route")
                        .setMessage("Are you sure you want to delete this route?")
                        //.setIcon(R.drawable.delete)

                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //button not displaying fix it
                                RouteCollection.GetInstance().getRoutes().remove(r);

                                Activity act = getActivity();

                                if (act != null)
                                {
                                    act.finish();
                                }
                                dialog.dismiss();
                            }

                        })

                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        })

                        .show();


                // TODO: maybe show a confirmation

//                RouteCollection.GetInstance().getRoutes().remove(r);
//
//                Activity act = getActivity();
//
//                if (act != null)
//                {
//                    act.finish();
//                }

                return true;
            default:
                return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DateDialogFragment.REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            // Get date extra from data
            DateTime date = (DateTime) data.getSerializableExtra(DateDialogFragment.EXTRA_DATE);
            this.route.setDate(date);

            this.dateButton.setText(date.toString(DateTimeFormat.longDate()));
        }
    }
}
