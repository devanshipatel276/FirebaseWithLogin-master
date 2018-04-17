package com.example.devanshi.firebasewithlogin;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.devanshi.firebasewithlogin.model.UserDetails;
import com.example.devanshi.firebasewithlogin.util.Mypref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.devanshi.firebasewithlogin.util.Mypref.Email;
import static com.example.devanshi.firebasewithlogin.util.Mypref.Image;
import static com.example.devanshi.firebasewithlogin.util.Mypref.Name;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, mname, mphone;
    private Button btnSignIn, btnSignUp, btnResetPassword, cancel;
    private ProgressBar progressBar;
    private ImageView image,back;
    private String picpath;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "SignupActivity";
    private View view;
    private TextView mtvname, memail, mpassword, mtphone;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        auth = FirebaseAuth.getInstance();
       // back=(ImageView)findViewById(R.id.btnSignupBack);
        image = (ImageView) findViewById(R.id.image);
        mname = (EditText) findViewById(R.id.name);
        mphone = (EditText) findViewById(R.id.phone);
        cancel = (Button) findViewById(R.id.btn_signup_cancel);
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        Toolbar toolbar=(Toolbar) findViewById(R.id.signupToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
              finish();
            }
        });




        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                final String name = mname.getText().toString().trim();
                Intent i = (new Intent(SignupActivity.this, Resetpassword.class));
                startActivity(i);
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagepicker();
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validation()) {
                    final String email = inputEmail.getText().toString().trim();
                    String password = inputPassword.getText().toString().trim();


                    progressBar.setVisibility(View.VISIBLE);


                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(Task<AuthResult> task) {
                                    Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent i = new Intent(SignupActivity.this, SigninActivity.class);
                                    startActivity(i);
                                        finish();
                                    }
                                }
                            });
                }
                else
                {

                }

            }
        });



    }


    private void imagepicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.d("HEY", "IMAGE path Filename:- " + picturePath);
                picpath = picturePath;

                Mypref mypref = new Mypref();
                mypref.Glide(picpath, SignupActivity.this, image);

                image.setImageBitmap(thumbnail);
            }
        }
    }

    private boolean validation() {
        TextInputLayout tPassword = findViewById(R.id.password_inputtext);
        TextInputLayout tPhone = findViewById(R.id.phone_inputtext);
        TextInputLayout tEmail = findViewById(R.id.email_inputtext);
        TextInputLayout tName = findViewById(R.id.name_inputtext);

        String email = inputEmail.getText().toString().trim();
        String phone = mphone.getText().toString().trim();
        if (mname.getText().toString().trim().isEmpty())
        {
            tName.setError("Enter Your name");
            return false;

        }
        else
        {
            tName.setErrorEnabled(false);
        }
        if (email.isEmpty() && !isValidEmail(email))
        {
            tEmail.setError("Enter Your emailId");
            return false;

        }
        else
        {
            tEmail.setErrorEnabled(false);
        }
        if (phone.isEmpty() && !isValidPhone(phone))
        {
            tPhone.setError("Enter Your Phonenumber");
            return false;

        }
        else
        {
            tPhone.setErrorEnabled(false);
        } if (inputPassword.getText().toString().trim().isEmpty() && inputPassword.getText().toString().length() < 6)
        {
            tPassword.setError("Enter Your Password");
            return false;

        }
        else
        {
            tPassword.setErrorEnabled(false);
        }
        return true;
    }


    private boolean isValidPhone(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }


    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
        finish();
    }

}



