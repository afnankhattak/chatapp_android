package com.example.haier.chatapp.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.haier.chatapp.Fragments.ChatFragment;
import com.example.haier.chatapp.R;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ChatFragment chatFragment = new ChatFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, chatFragment);
        fragmentTransaction.addToBackStack("ChatFragment");
        fragmentTransaction.commit();
    }
}
