package edu.chapman.cpsc356.routegenerator.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by tyler on 11/13/17.
 */

public class RouteListTouchHelper extends ItemTouchHelper.SimpleCallback {

        private RouteListAdapter slAdapter;

        public RouteListTouchHelper(RouteListAdapter a){
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            this.slAdapter = a;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            slAdapter.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            //Remove item
            slAdapter.remove(viewHolder.getAdapterPosition());

        }

    }




