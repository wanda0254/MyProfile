package com.wandamuhammads.makersinstitute.myprofile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {
    ImageView myFoto;
    Button btnTake;
    Button btnPorfile;
    TextView textName;
    TextView textSubName;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_POFILE_DETAILS = 11;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    String name,occupation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        myFoto = (ImageView) findViewById(R.id.myFoto);
        btnTake = (Button) findViewById(R.id.btnTake);
        btnPorfile = (Button) findViewById(R.id.btnProfile);
        textName = (TextView) findViewById(R.id.fullName);
        textSubName = (TextView) findViewById(R.id.subName);

        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        mSharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        String name = mSharedPreferences.getString("name","");
        String occupation = mSharedPreferences.getString("occupation","");
        textName.setText(name);
        textSubName.setText(occupation);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//intent to camera
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {//cek if camera is avaible
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);//running intent
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {// chek if the request code is from camera and if the result OK
            Bundle extras = data.getExtras();//getting data from camera
            Bitmap imageBitmap = (Bitmap) extras.get("data");//assign the data, which is a picture to a variable
            myFoto.setImageBitmap(imageBitmap);//applying the picture into an image view
        } else if (requestCode == REQUEST_POFILE_DETAILS) {
            name = data.getStringExtra("name");
            occupation = data.getStringExtra("occupation");
            textName.setText(name);
            textSubName.setText(occupation);
        }
    }

    public void editProfileButton() {
        Intent intent = new Intent(MyProfileActivity.this, EditProfileActivity.class);
        intent.putExtra("name",textName.getText().toString());
        intent.putExtra("occupation",textSubName.getText().toString());
        startActivityForResult(intent, REQUEST_POFILE_DETAILS);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor = mSharedPreferences.edit();
        mEditor.putString("name", textName.getText().toString());
        mEditor.putString("occupation", textSubName.getText().toString());
        mEditor.apply();
    }
}


