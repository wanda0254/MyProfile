package com.wandamuhammads.makersinstitute.myprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfileActivity extends AppCompatActivity {
    Button btnSaveProfile;
    EditText nameEditText, occupationEditText;
    static final int REQUEST_PROFILE_DETAILS = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        btnSaveProfile = (Button) findViewById(R.id.btnSaveProfile);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        occupationEditText = (EditText) findViewById(R.id.occuptionEditText);

        // Intent intentEdit = getIntent();
        //nameEditText.setText(intentEdit.getStringExtra(""));


        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", nameEditText.getText().toString());
                intent.putExtra("occupation", occupationEditText.getText().toString());
                setResult(REQUEST_PROFILE_DETAILS, intent);
                finish();
            }
        });
    }
}

