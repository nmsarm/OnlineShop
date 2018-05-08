package com.sarmiento.daneahmarelle.onlineshop;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class fragment_subscribe extends Fragment{

    View myView;

    //view objects
    private Button mButtonSubscribe;
    private EditText mEditTextEmail;

    //database reference
    private DatabaseReference mDatabaseRef; //POINTS TO DATABASE

//    List<Upload> upload;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.subscribe_layout, container, false);
        return myView;

    }
    @Override
    public void onViewCreated(View myView, Bundle savedInstanceState) {
        super.onViewCreated(myView, savedInstanceState);

        getActivity().setTitle("Subscribe");

        //getting reference
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Subscribers"); //INITIALIZE

        //getting views
        mButtonSubscribe = (Button) myView.findViewById(R.id.button_subscribe);
        mEditTextEmail = (EditText) myView.findViewById(R.id.et_email);

//        //list to store artists
//        upload = new ArrayList<>();

        //UPLOAD BUTTON IS CLICKED
        mButtonSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribe();
            }
        });
    }

    private void subscribe() {

        String email = mEditTextEmail.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(email)) {

            //getting a unique id using push().getKey() method
            String id = mDatabaseRef.push().getKey();

            //creating an Email Object
            Upload upload = new Upload(id, email);

            //Saving the Email
            mDatabaseRef.child(id).setValue(upload);

            //setting edittext to blank again
            mEditTextEmail.setText("");

            //displaying a success toast
            Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_SHORT).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(getActivity(), "Please enter an email", Toast.LENGTH_SHORT).show();
        }
    }


}