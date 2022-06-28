package com.example.week4;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog.*;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View.OnClickListener;
import android.view.*;
import android.os.Bundle;
import android.widget.*;
public class MainActivity extends AppCompatActivity implements OnClickListener{
    EditText RollNo ,Name , Marks;
    Button insert ,delete, update , view , viewAll;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RollNo = (EditText) findViewById(R.id.roll);
        Name = (EditText) findViewById(R.id.name);
        Marks = (EditText) findViewById(R.id.marks);
        insert = (Button) findViewById(R.id.insert);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.update);
        view = (Button) findViewById(R.id.view);
        viewAll = (Button) findViewById(R.id.viewall);

        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        view.setOnClickListener(this);
        viewAll.setOnClickListener(this);

        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists student(rollNo varchar(20),name varchar(20),marks varchar(20));");
    }
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.insert){
            if(RollNo.getText().toString().trim().length() == 0||
                Name.getText().toString().trim().length() == 0||
                Marks.getText().toString().trim().length() == 0){
                showMessage("Error","Please Enter All the Fields");
                return;
            }
            db.execSQL("insert into student values ('"+RollNo.getText()+"','"+Name.getText()+
                    "','"+Marks.getText()+"');");
            showMessage("Success" , "Added Records Succesfully");
            clearText();
        }
        if(v.getId() == R.id.delete){
            if(RollNo.getText().toString().trim().length() == 0){
                showMessage("Error","Please Enter Roll No");
                return;
            }
            Cursor c = db.rawQuery("select * from student where rollNo = '"+RollNo.getText().toString()+"'",null);
            if(c.moveToFirst()){
                db.execSQL("delete from student where rollNo = '"+RollNo.getText().toString()+"'");
                showMessage("Deleted","Successfully Deleted");
            }
            else
                showMessage("Error","Record Not Found");
            clearText();
        }
        if(v.getId()==R.id.update)
        {
            if(RollNo.getText().toString().trim().length()==0){
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rollNo='"+RollNo.getText()+"'", null);
            if(c.moveToFirst()) {
                db.execSQL("UPDATE student SET name='" + Name.getText() + "',marks='" + Marks.getText() +
                        "' WHERE rollNo='"+RollNo.getText()+"'");
                showMessage("Updated", "Record Modified");
            }
            else showMessage("Error", "Invalid Rollno");
            clearText();
        }
        if(v.getId()==R.id.view)
        {
            if(RollNo.getText().toString().trim().length()==0){
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rollNo='"+RollNo.getText()+"'", null);
            if(c.moveToFirst()){
                Name.setText(c.getString(1));
                Marks.setText(c.getString(2));
            }
            else {
                showMessage("Error", "Invalid Rollno");
                clearText();
            }
        }
        if(v.getId()==R.id.viewall) {
            Cursor c=db.rawQuery("SELECT * FROM student", null);
            if(c.getCount()==0){
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            c.moveToFirst();
            while(c.moveToNext()){
                buffer.append("Rollno: "+c.getString(0)+"\n");
                buffer.append("Name: "+c.getString(1)+"\n");
                buffer.append("Marks: "+c.getString(2)+"\n\n");
            }
            showMessage("Student Details", buffer.toString());
        }
    }
    public void showMessage(String title, String message){
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText(){
        RollNo.setText("");
        Name.setText("");
        Marks.setText("");
    }
}