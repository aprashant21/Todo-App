package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import com.example.todoapp.fragments.TodoListFragment;

public class MainActivity extends AppCompatActivity {
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar added
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(),R.color.white));
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        //initial fragment added
        TodoListFragment fragment = new TodoListFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_container, fragment).commit();

        //for dialog
        dialog = new Dialog(this);

    }


    //menu for about pages
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_about){
            dialog.setContentView(R.layout.about_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            Button okay_button = dialog.findViewById(R.id.btn_okay_about);
            //Setting Version name
            TextView versionText = dialog.findViewById(R.id.emailText);
            String versionName = BuildConfig.VERSION_NAME;
            versionText.setText("VERSION : "+ versionName);

            //okay button for dialog dismiss
            okay_button.setOnClickListener(view -> dialog.dismiss());

        }
        else if(item.getItemId() == R.id.action_contact){
            dialog.setContentView(R.layout.contact_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            AppCompatImageButton callButton  = dialog.findViewById(R.id.call_now_button);
            AppCompatImageButton emailBtn  = dialog.findViewById(R.id.email_button);
            //call now button use
            callButton.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9823643590"));
                startActivity(intent);
                dialog.dismiss();
            });
            //email now button
            emailBtn.setOnClickListener(view -> {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "aprashant21@tbc.edu.np"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                    dialog.dismiss();
                }catch(ActivityNotFoundException e){
                    Log.d("TAG",e.getMessage()+"");
                    dialog.dismiss();
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }
}