package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImage;

public class MasterListFragment extends Fragment {
    public MasterListFragment() {
    }

    onimgclick refreance; //instace of the interface

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate view
        View v=inflater.inflate(R.layout.fragment_master_layout , container ,false);
        //get reference from the grid view
        GridView gridView= (GridView) v.findViewById(R.id.gridview);
        //set adapter to the grid view and send context and the array list yo it adapter to display
        gridView.setAdapter(new MasterListAdapter(getActivity(), AndroidImage.getAll()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                refreance.onclick(position); // to send it to main activity
            }
        });

        //return the root view
        return v;
    }
    //to attacch the iterface with the activity main
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            refreance=(onimgclick)context;
        }catch (Exception e){
            Log.e("error emolemint","you mast implemint the interface in the main class");
        }
    }
    //iterface to send massage to the main activity the implementation in there
    public interface onimgclick{
        void onclick(int pos);
    }
}
