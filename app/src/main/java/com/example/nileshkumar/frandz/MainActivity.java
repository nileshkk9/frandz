package com.example.nileshkumar.frandz;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ViewPager viewPager;
    private SectionsPageAdapter mSectionPagerAdapter;
    private TabLayout mTabLayout;
    private DatabaseReference mUserRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        viewPager=findViewById(R.id.main_tabPager);
//        mToolbar = findViewById(R.id.main_page_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("Frandz");
        mSectionPagerAdapter =new SectionsPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionPagerAdapter);
        mTabLayout =findViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(viewPager);
        if (mAuth.getCurrentUser() != null) {


            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            sendToStart();
        }
        else{
            mUserRef.child("online").setValue(true);
        }
}

    @Override
    protected void onStop() {
        super.onStop();
        mUserRef.child("online").setValue(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.main_logout_btn){
            FirebaseAuth.getInstance().signOut();
            sendToStart();
        }
        if(item.getItemId()==R.id.main_settings_btn){
            Intent in =new Intent(this,SettingsActivity.class);
            startActivity(in);
        }
        if(item.getItemId()==R.id.main_all_btn){
            Intent in =new Intent(this,UsersActivity.class);
            startActivity(in);
        }

            return true;
    }
    private void sendToStart() {

        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();

    }
}
