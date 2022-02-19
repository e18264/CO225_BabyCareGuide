package com.thakshila.regform;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.media.metrics.LogSessionId;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private EditText txtFirstName, txtLastName, txtBirthdate, txtCurrentWeight, txtCurrentHeight;
    private TextView txtViewNotify;
    private RadioGroup rbGender;
    private Button btnRegister;
    private RelativeLayout parent;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonRegister:

                if (validateData()){
                    showSnackBar();
                }
                break;
            case R.id.editTextFirstName:
                Toast.makeText(this, "Type first name of your baby here", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editTextLastName:
                Toast.makeText(this, "Type last name of your baby here", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        txtFirstName.setOnClickListener(this);
        txtLastName.setOnClickListener(this);
        rbGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
            switch (checkedId){
                case R.id.rbMale:
                    Toast.makeText(MainActivity.this, "Male", Toast.LENGTH_SHORT).show();
                case R.id.rbFemale:
                    Toast.makeText(MainActivity.this, "Female", Toast.LENGTH_SHORT).show();
                default:
                    break;
            }
            }
        })
        ;

        btnRegister.setOnClickListener(this);


    }

    private boolean validateData(){
        Log.d(TAG, "validateData: started");
        if (txtFirstName.getText().toString().equals("") || txtLastName.getText().toString().equals("")
                || txtBirthdate.getText().toString().equals("") || txtCurrentWeight.getText().toString().equals("")
                || txtCurrentHeight.getText().toString().equals("") ){
            txtViewNotify.setVisibility(View.VISIBLE);
            txtViewNotify.setText("Required all fields !");
            return false;
        }
        return true;
    }

    private  void showSnackBar(){
        Log.d(TAG, "showSnackBar: Started");
        txtViewNotify.setVisibility(View.GONE);
        Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();

        Snackbar.make(parent, "User Registered", Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        txtFirstName.setText("");   txtBirthdate.setText("");
                        txtLastName.setText("");    txtCurrentHeight.setText("");
                        txtCurrentWeight.setText("");
                    }
                }).show();
    }

    private void initViews(){
        Log.d(TAG, "initViews: Started");
        txtFirstName     = findViewById(R.id.editTextFirstName);
        txtLastName = findViewById(R.id.editTextLastName);
        txtBirthdate = findViewById(R.id.editTextBirthDate);
        rbGender = findViewById(R.id.rbGender);
        btnRegister = findViewById(R.id.buttonRegister);
        txtCurrentWeight = findViewById(R.id.editTextCurrentWeight);
        txtCurrentHeight = findViewById(R.id.editTextCurrentHeight);
        txtViewNotify = findViewById(R.id.textViewNotify);
        parent = findViewById(R.id.parent);
    }
}