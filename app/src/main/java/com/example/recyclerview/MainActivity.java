package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ContactModel> arr = new ArrayList<>();
    ArrayList<String> arr1= new ArrayList<>();
    Toolbar t;
    RecylerContactAdapter adapter;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView r = findViewById(R.id.recyleview);
        Button b = findViewById(R.id.btnopen);
        t=findViewById(R.id.toolbaar);
        autoCompleteTextView=findViewById(R.id.s);
        setSupportActionBar(t);
        if(getSupportActionBar()!=null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                t.setTitle("Contacts");
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update);
                EditText edt2 = dialog.findViewById(R.id.name);
                EditText edt1 = dialog.findViewById(R.id.number);
                Button btn = dialog.findViewById(R.id.buttonaction);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", number = "";
                        if (!edt2.getText().toString().equals("")) {
                            name = edt2.getText().toString();
                        } else {
                            Toast.makeText(MainActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                        }
                        if (!edt1.getText().toString().equals("")) {
                            number = edt1.getText().toString();
                        } else {
                            Toast.makeText(MainActivity.this, "Please Enter Your Number", Toast.LENGTH_SHORT).show();
                        }
                        arr.add(new ContactModel(R.drawable.d,name, number));
                        adapter.notifyItemInserted(arr.size() - 1);
                        r.scrollToPosition(arr.size() - 1);
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        r.setLayoutManager(new LinearLayoutManager(this));

        arr.add(new ContactModel(R.drawable.f,"Harsh","8496446762"));
        arr.add(new ContactModel(R.drawable.b,"Jai","9234357891"));
        arr.add(new ContactModel(R.drawable.d,"Abhinav","4756974363"));
        arr.add(new ContactModel(R.drawable.f,"Neeraj","9234357891"));
        arr.add(new ContactModel(R.drawable.d,"Chanchal","9849754739"));
        arr.add(new ContactModel(R.drawable.b,"Daksh","9234357891"));
        arr.add(new ContactModel(R.drawable.d,"Himanshu","9030483048"));
        arr.add(new ContactModel(R.drawable.b,"Harish","9234357891"));
        arr.add(new ContactModel(R.drawable.b,"Abhijeet","8968947342"));
        arr.add(new ContactModel(R.drawable.f,"Sarthak","9234357891"));
        arr.add(new ContactModel(R.drawable.b,"Kapil","1234557890"));
        arr.add(new ContactModel(R.drawable.f,"Jatin","9234357891"));
        arr1.add("Harsh");
        arr1.add("Jai");
        arr1.add("Abhinav");
        arr1.add("Neeraj");
        arr1.add("Chanchal");
        arr1.add("Daksh");
        arr1.add("Himanshu");
        arr1.add("Harish");
        arr1.add("Abhijeet");
        arr1.add("Sarthak");
        arr1.add("Kapil");
        arr1.add("Jatin");
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arr1);
        autoCompleteTextView.setAdapter(adapter1);
        autoCompleteTextView.setThreshold(1);
        adapter = new RecylerContactAdapter(this, arr);
        r.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid=item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
