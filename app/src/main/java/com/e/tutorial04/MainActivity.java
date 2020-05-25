package com.e.tutorial04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.tutorial04.Database.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtPassword;
    Button btnSelectAll, btnAdd, btnDelete, btnSignIn, btnUpdate;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DBHelper(this);
        txtName = findViewById(R.id.name_value);
        txtPassword = findViewById(R.id.password_value);
        btnSelectAll = findViewById(R.id.selectall_btn);
        btnAdd = findViewById(R.id.add_btn);
        btnSignIn = findViewById(R.id.signin_btn);
        btnDelete = findViewById(R.id.delete_btn);
        btnUpdate = findViewById(R.id.update_btn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDB.addInfo(txtName.getText().toString(), txtPassword.getText().toString())){
                    Toast.makeText(MainActivity.this, "New record added", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Cannot add the record", Toast.LENGTH_LONG).show();
            }
        });

        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List result = myDB.readAllInfo();
                if(result != null){
                    for (int i=0;i < result.size();i++){
                        Log.d("Record", "new record" + result.get(i));
                    }
                }
                else
                    showMessage();
            }

            private void showMessage() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Error");
                builder.setMessage("No student available");
                builder.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.updateInfo(txtName.getText().toString(), txtPassword.getText().toString());
                Toast.makeText(MainActivity.this, "Update Succeessfully", Toast.LENGTH_LONG).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteInfo(txtName.getText().toString());
                Toast.makeText(MainActivity.this, "Delete Succeessfully", Toast.LENGTH_LONG).show();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDB.readInfo(txtName.getText().toString(), txtPassword.getText().toString()))
                    Toast.makeText(MainActivity.this, "User name password are exist", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "User name password are not exist", Toast.LENGTH_LONG).show();
            }
        });
    }
}
