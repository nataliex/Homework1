package com.example.homework;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private int length = 100;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        Button textView;
        MyViewHolder(Button v) {
            super(v);
            textView = v;
        }
    }

    private RecyclerView.Adapter mAdapter;

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private ArrayList<String> mDataset;

        // Provide a suitable constructor (depends on the kind of dataset)
        MyAdapter(ArrayList<String> myDataset) {
            mDataset = myDataset;
        }

        public void add(int i){

            mDataset.add(String.valueOf(i));

        }

        // Create new views (invoked by the layout manager)
        @Override
        @NonNull
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // create a new view
            Button v = (Button) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
            return new MyViewHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            final String a = mDataset.get(position);
            holder.textView.setText(a);
            holder.textView.setTextColor((position % 2 == 0) ? Color.BLUE : Color.RED);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mListener.onFragmentInteraction(a, position);

                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }

    }

    private OnFragmentInteractionListener mListener;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.RecyclerView1);

        int a = getResources().getInteger(R.integer.column_count);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), a);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> b = new ArrayList<>(100);
        for (int i = 1; i < 101; i++){
            b.add(String.valueOf(i));
        }

        mAdapter = new MyAdapter(b);
        recyclerView.setAdapter(mAdapter);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyAdapter)mAdapter).add(++length);
                mAdapter.notifyItemInserted(length);
            }
        });

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String a, int position);
    }
}
