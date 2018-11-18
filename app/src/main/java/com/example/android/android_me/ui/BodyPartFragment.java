package com.example.android.android_me.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class BodyPartFragment extends Fragment {

    private int index;
    private List<Integer> list;

    @SuppressLint("ValidFragment")
    public BodyPartFragment() {
        index=0;
    }

    // inflate fragment layout and set the source

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //check if there an saved instant or not if there set it to the variable
        if(savedInstanceState != null){
            index=savedInstanceState.getInt("Kindex");
            list=savedInstanceState.getIntegerArrayList("Klist");
        }


        //inflate the android me fragment layout
        View v=inflater.inflate(R.layout.fragment_body_bart,container,false);
        //get reference to the ImageView in the layout
        final ImageView img= (ImageView) v.findViewById(R.id.body_part_image_view);
        //set the image resource to display img if list is set else it will == null so no thing is display
        if(list != null)
        {
            img.setImageResource(list.get(index));
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index=(index+1)%list.size();
                    img.setImageResource(list.get(index));
                }
            });
        }
        else
            Log.v("error : ","list is null so nothing is comeing to display");
        //return the root view
        return v;
    }
    //set index which start with
    public void setIndex(int index) {
        this.index = index;
    }
    //set the list of img which set img with in it
    public void setList(List<Integer> list) {
        this.list = list;
    }
    //save the currant state the index and the list
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("Klist",(ArrayList<Integer>)list);
        outState.putInt("Kindex",index);
    }
}
