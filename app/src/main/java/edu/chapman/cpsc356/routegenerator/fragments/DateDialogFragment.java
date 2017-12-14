package edu.chapman.cpsc356.routegenerator.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import org.joda.time.DateTime;


public class DateDialogFragment extends DialogFragment
{
    public static final int REQUEST_CODE = 1000;
    private static final String ARG_DATE = "arg_date";
    public static final String EXTRA_DATE = "extra_date";

    public static DateDialogFragment GetInstance(DateTime date)
    {
        DateDialogFragment frag = new DateDialogFragment();

        Bundle b = new Bundle();
        b.putSerializable(ARG_DATE, date);

        frag.setArguments(b);

        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        DateTime date = (DateTime) getArguments().getSerializable(ARG_DATE);

        final DatePicker picker = new DatePicker(getActivity());
        picker.init(date.getYear(), date.getMonthOfYear() - 1, date.getDayOfMonth(), null);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Pick a date")
                .setView(picker)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        DateTime date = new DateTime(picker.getYear(), picker.getMonth() + 1, picker.getDayOfMonth(), 0, 0);

                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_DATE, date);

                        getTargetFragment().onActivityResult(REQUEST_CODE, Activity.RESULT_OK, intent);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        return dialog;
    }
}
