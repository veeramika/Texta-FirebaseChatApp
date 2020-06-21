package com.rashmiappd.texta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rashmiappd.texta.Model.User;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    CircleImageView mprofileImg;
    TextView mUsername;

    FirebaseUser mFirebaseUser;
    DatabaseReference mReference;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null)
        {
            sendToStart();
        }
    }

    private void sendToStart() {
        Intent startIntent = new Intent(MainActivity.this , StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar setup
        Toolbar mToolbar = findViewById(R.id.main_toolbarID);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        mprofileImg = findViewById(R.id.main_profile_image);
        mUsername = findViewById(R.id.main_usernameID);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference("Users")
                .child(mFirebaseUser.getUid());

       mReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               User user = dataSnapshot.getValue(User.class);
               mUsername.setText(user.getUsername());
               if (user.getImageURL().equals("default")){
                   mprofileImg.setImageResource(R.mipmap.ic_launcher);
               } else {

                   //change this
                   Glide.with(getApplicationContext()).load(user.getImageURL()).into(mprofileImg);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });


    }
}
