package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImage;

public class MainActivity extends AppCompatActivity implements MasterListFragment.onimgclick {

    private int headind,bodyind,legind; //img index hat display now
    private boolean aBoolean; //big view or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if we saved instance we set the value that was to save the change in any case like landScape if it is not it set it with 0 to began from the first
        if(savedInstanceState != null){
            headind=savedInstanceState.getInt("kheadind");
            bodyind=savedInstanceState.getInt("kbodyind");
            legind=savedInstanceState.getInt("klegind");
        }else {
            headind=bodyind=legind=0;
        }

        //check if it is tape (landscape) or phone
        //linearlayout is in large view only so we check by it what view i use
        if(findViewById(R.id.linearlayout) != null){
            aBoolean=true;

            //to get the button visibility gone because we do not net it in this view
            Button button= (Button) findViewById(R.id.next_button);
            button.setVisibility(View.GONE);

            //set the number of columns 2 because ot is smaller now
            GridView gridView= (GridView) findViewById(R.id.gridview);
            gridView.setNumColumns(2);

            //to do this in the first time open the app in not in any other case like rotation
            //if(savedInstanceState == null){
                //create instance from fragment class to display the argument point to the type of the fragment is it display head or body or leg
                // -> head
                BodyPartFragment headFragment=new BodyPartFragment();
                headFragment.setList(AndroidImage.getHead());
                headFragment.setIndex(headind);
                // -> body
                BodyPartFragment bodyFargment=new BodyPartFragment();
                bodyFargment.setList(AndroidImage.getBody());
                bodyFargment.setIndex(bodyind);
                // -> leg
                BodyPartFragment legsFragment=new BodyPartFragment();
                legsFragment.setList(AndroidImage.getLegs());
                legsFragment.setIndex(legind);

                // FragmentManager and transaction to display the fragment on the screen

                FragmentManager fragmentManager=getSupportFragmentManager();

                //fragment transaction to display head
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.head_container,headFragment)
                        .commit();
                //fragment transaction to display body
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.body_container,bodyFargment)
                        .commit();
                //fragment transaction to display leg
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.legs_container,legsFragment)
                        .commit();
            //}

        }else {
            //not on the tow fragment view tis on phone
            aBoolean=false;
        }



        //this on small screen that when user push next button it create bundle and but the head,body and leg index in it and send it to the activity that show the parts
        final Intent intent=new Intent(this,AndroidMeActivity.class);
        Button button= (Button) findViewById(R.id.next_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the bundle and start the activity
                Bundle bundle=new Bundle();
                bundle.putInt("khead",headind);
                bundle.putInt("kbody",bodyind);
                bundle.putInt("kleg",legind);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //implementation of the onclick function in the interface in masterlistfragment to send the data from fragment to the activity
    @Override
    public void onclick(int pos) {
        //get what part user want to change head , body or leg
        int bodybart=pos/12;
        //get the index of the part user change
        int partind=pos%12;


        //chick if i was in tap( landscape ) or phone because it is different between tow cases
        //first if aBoolean is true that main the two fragment is display and the change is going to be once on click
         if(aBoolean){
             BodyPartFragment fragment=new BodyPartFragment();
             switch (bodybart){
                 case 0:
                     //head
                     headind=partind;
                     //set list of head images
                     fragment.setList(AndroidImage.getHead());
                     //set the index of the chosen img
                     fragment.setIndex(partind);
                     //show it in it is container
                     getSupportFragmentManager().beginTransaction().replace(R.id.head_container,fragment).commit();
                     break;
                 case 1:
                     //body
                     bodyind=partind;
                     //set list of body images
                     fragment.setList(AndroidImage.getBody());
                     //set the index of the chosen img
                     fragment.setIndex(partind);
                     //show it in it is container
                     getSupportFragmentManager().beginTransaction().replace(R.id.body_container,fragment).commit();
                     break;
                 case 2:
                     //leg
                     legind=partind;
                     //set list of legs images
                     fragment.setList(AndroidImage.getLegs());
                     //set the index of the chosen img
                     fragment.setIndex(partind);
                     //show it in it is container
                     getSupportFragmentManager().beginTransaction().replace(R.id.legs_container,fragment).commit();
                     break;
                 default: break;
             }
         }else{
             // second it wait untill the user click nexxtbutton so we just change the value
             switch (bodybart){
                 case 0:
                     //head
                     headind=partind;
                     break;
                 case 1:
                     //body
                     bodyind=partind;
                     break;
                 case 2:
                     //leg
                     legind=partind;
                     break;
                 default: break;
             }
         }
    }

    //onSaveInstanceState to save the state of the activity on rotate or pose
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("kheadind",headind);
        outState.putInt("kbodyind",bodyind);
        outState.putInt("klegind",legind);
    }
}
