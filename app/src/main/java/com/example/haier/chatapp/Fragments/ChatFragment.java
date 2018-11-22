package com.example.haier.chatapp.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.haier.chatapp.Models.Author;
import com.example.haier.chatapp.Models.Message;
import com.example.haier.chatapp.Models.SMS;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.Date;
import com.example.haier.chatapp.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private MessageInput messageInput;
    private MessagesList messagesList;

    private Author currentUser;
    FirebaseDatabase database ;
    DatabaseReference myRef;
    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference();
        messageInput = (MessageInput) view.findViewById(R.id.input);
        messagesList = (MessagesList) view.findViewById(R.id.messagesList);

        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, @Nullable String url, @Nullable Object payload) {

                Picasso.get().load(url).into(imageView);
            }
        };

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUser = new Author(firebaseUser.getUid(), firebaseUser.getEmail());

        final MessagesListAdapter<Message> adapter = new MessagesListAdapter<>(currentUser.getId(), imageLoader);
        messagesList.setAdapter(adapter);

        messageInput.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(final CharSequence input) {



                SMS sms=new SMS(firebaseUser.getUid(),input.toString(), currentUser, new Date());


                myRef.child("afnan_chat").push().setValue(sms).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Message message = new Message(firebaseUser.getUid(),input.toString(), currentUser, new Date());
                        adapter.addToStart(message, true);

                        //Log.e("Message",aVoid.toString());
                        // Write was successful!
                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Write failed
                           //     Log.e("Message",e.getMessage());

                                // ...
                            }
                        });
                return true;
            }
        });

        return view;
    }

}
