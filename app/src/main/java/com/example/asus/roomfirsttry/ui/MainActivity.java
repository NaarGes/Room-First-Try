package com.example.asus.roomfirsttry.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.roomfirsttry.R;
import com.example.asus.roomfirsttry.database.AppDatabase;
import com.example.asus.roomfirsttry.entity.Comment;
import com.example.asus.roomfirsttry.entity.User;
import com.example.asus.roomfirsttry.ui.firstquery.FirstFragment;
import com.example.asus.roomfirsttry.ui.fourthfragment.FourthFragment;
import com.example.asus.roomfirsttry.ui.secondquery.SecondFragment;
import com.example.asus.roomfirsttry.ui.thirdquery.ThirdFragment;
import com.example.asus.roomfirsttry.util.DataGenerator;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> query1_result;
    private List<Comment> query2_result;
    private List<Comment> query3_result;
    private int query4_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDatabase database = AppDatabase.getAppDatabase(this);

        DataGenerator.with(database).generateUsers();
        DataGenerator.with(database).generatePosts();
        DataGenerator.with(database).generateComments();

        Button query1 = findViewById(R.id.query1_butt);
        query1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText postId = findViewById(R.id.query1_et);
                if(!postId.getText().toString().equals("")) {
                            query1_result = database.myDao().UsersCommentedOnAPost(Integer.parseInt(postId.getText().toString()));

                            FirstFragment firstFragment = FirstFragment.newInstance(query1_result);
                            setFragment(firstFragment);
                } else {
                    Toast.makeText(getApplicationContext(), "Please inter an User ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button query2 = findViewById(R.id.query2_butt);
        query2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userId = findViewById(R.id.query2_et);
                if(!userId.getText().toString().equals("")) {
                    query2_result = database.myDao().CommentsOfAUser(Integer.parseInt(userId.getText().toString()));

                    SecondFragment secondFragment = SecondFragment.newInstance(query2_result);
                    setFragment(secondFragment);
                } else {
                    Toast.makeText(getApplicationContext(), "Please inter an User ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button query3 = findViewById(R.id.query3_butt);
        query3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText postId = findViewById(R.id.query3_et);
                if(!postId.getText().toString().equals("")) {
                    query3_result = database.myDao().commentsOfAPost(Integer.parseInt(postId.getText().toString()));

                    ThirdFragment thirdFragment = ThirdFragment.newInstance(query3_result);
                    setFragment(thirdFragment);
                } else {
                    Toast.makeText(getApplicationContext(), "Please inter an Post ID", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button query4 = findViewById(R.id.query4_butt);
        query4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userId = findViewById(R.id.query4_et);
                if(!userId.getText().toString().equals("")) {
                    query4_result = database.myDao().NumberOfUsersComments(Integer.parseInt(userId.getText().toString()));

                    FourthFragment fourthFragment = FourthFragment.newInstance(query4_result);
                    setFragment(fourthFragment);
                } else {
                    Toast.makeText(getApplicationContext(), "Please inter an User ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }
}
