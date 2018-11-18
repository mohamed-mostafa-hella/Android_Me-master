package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImage;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);


        //check saved instance if it is not null this mane there an saved one we do not net to do this
        if(savedInstanceState == null){

            Intent intent=getIntent();
            Bundle bundle=intent.getExtras();

            //create instance from fragment class to display the argument point to the type of the fragment is it display head or body or leg
            // -> head
            BodyPartFragment headFragment=new BodyPartFragment();
            headFragment.setList(AndroidImage.getHead());
            headFragment.setIndex(bundle.getInt("khead"));
            // -> body
            BodyPartFragment bodyFargment=new BodyPartFragment();
            bodyFargment.setList(AndroidImage.getBody());
            bodyFargment.setIndex(bundle.getInt("kbody"));
            // -> leg
            BodyPartFragment legsFragment=new BodyPartFragment();
            legsFragment.setList(AndroidImage.getLegs());
            legsFragment.setIndex(bundle.getInt("kleg"));

            // FragmentManager and transaction to display the fragment on the screen

            FragmentManager fragmentManager=getSupportFragmentManager();

            //fragment transaction to display head
            fragmentManager
                    .beginTransaction()
                    .add(R.id.head_container,headFragment)
                    .commit();
            //fragment transaction to display body
            fragmentManager
                    .beginTransaction()
                    .add(R.id.body_container,bodyFargment)
                    .commit();
            //fragment transaction to display leg
            fragmentManager
                    .beginTransaction()
                    .add(R.id.legs_container,legsFragment)
                    .commit();
        }

    }
}
