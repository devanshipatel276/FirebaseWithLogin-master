package com.example.devanshi.firebasewithlogin.fragement;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.devanshi.firebasewithlogin.HomeActivity;
import com.example.devanshi.firebasewithlogin.R;
import com.example.devanshi.firebasewithlogin.model.Register;
import com.example.devanshi.firebasewithlogin.util.Mypref;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.Activity.RESULT_OK;


public class RegisterFragment extends Fragment
{
    EditText registername,registeraddress,registerphone,registerwebsite,registerdescription;
    ImageView registerImage;
    Spinner registerspinner;
    Button btnRegister;
    String  name,address,phone,website,description,id,register,picpath, rootnode;
    DatabaseReference databasehospital;
    StorageReference storage;
    private static final String TAG="HospitalFragement";
    private static final int SELECT_PICTURE = 100;
    String[] catagory = { "Doctor", "Hospital", "Pharmacy", "Laboratory"};

    public RegisterFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_register, container, false);

        registername=view.findViewById(R.id.register_name);
        registeraddress=view.findViewById(R.id.register_address);
        registerphone=view.findViewById(R.id.register_phone);
        registerwebsite=view.findViewById(R.id.register_website);
        registerdescription=view.findViewById(R.id.register_describe);
        registerImage=view.findViewById(R.id.register_image);
        btnRegister=view.findViewById(R.id.register_register);
        registerspinner=view.findViewById(R.id.register_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, catagory);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        registerspinner.setAdapter(adapter);

        registerspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                 rootnode = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onCreateView: "+rootnode);
                databasehospital = FirebaseDatabase.getInstance().getReference(rootnode);
                storage= FirebaseStorage.getInstance().getReference();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                imagepicker();



            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                name = registername.getText().toString();
                address = registeraddress.getText().toString();
                phone = registerphone.getText().toString();
                website = registerwebsite.getText().toString();
                description = registerdescription.getText().toString();



                if(validation())
                {
                    addhospital();

                    Intent i = new Intent(getContext(), HomeActivity.class);
                    startActivity(i);
                }
                   else {

                }

                }


        });
    }

    private boolean validation()
    {
        TextInputLayout tName= getView().findViewById(R.id.name_registerinputtext);
        TextInputLayout tAddress=getView().findViewById(R.id.address_registerinputtext);
        TextInputLayout tPhone=getView().findViewById(R.id.phonenumber_registerinputtext);
        TextInputLayout tWebsite=getView().findViewById(R.id.website_registerinputtext);
        TextInputLayout tDescription=getView().findViewById(R.id.description_registerinputtext);

        if(name.isEmpty() )
        {
            tName.setError("Enter Your Name");
            return false;
        }
        else
        {
            tName.setErrorEnabled(false);
        }
         if(address.isEmpty())
        {

             tAddress.setError("Enter Your Address");
                return false;
        }
        else
         {
             tAddress.setErrorEnabled(false);
         }
         if(phone.isEmpty() && !isValidPhone(phone))
        {
            tPhone.setError("Enter Your PhoneNumber");
            return false;
        }
        else
         {
             tPhone.setErrorEnabled(false);
         }
         if(website.isEmpty() && !isValidWebsite(website))
        {
            tWebsite.setError("Enter Your Website");
            return false;
        }
        else
         {
             tWebsite.setErrorEnabled(false);
         }
         if(description.isEmpty() && description.length()<10)
        {
            tDescription.setError("Enter Description Proper");
            return false;
        }


       else {

             tDescription.setErrorEnabled(false);
         }
        return true;
    }



    private boolean isValidWebsite(String website)
    {
        return Patterns.WEB_URL.matcher(website).matches();
    }


    private boolean isValidPhone(String phone)
    {
            return Patterns.PHONE.matcher(phone).matches();
    }

    private void imagepicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    private void addhospital()
    {
            String id = databasehospital.push().getKey();
            Register register = new Register(id,name,address,phone,website,description,picpath);
            databasehospital.child(id).setValue(register);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == SELECT_PICTURE)
            {

                final Uri uri=data.getData();
                final StorageReference path= storage.child("photos").child("simple_image");
                path.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                    {
                        Log.d(TAG, "onSuccess: "+String.valueOf(taskSnapshot.getDownloadUrl()));
                        picpath= String.valueOf(taskSnapshot.getDownloadUrl());
                    }
                });

            }
        }
    }



}
