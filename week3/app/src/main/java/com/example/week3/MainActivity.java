package com.example.week3;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText num1, num2;
    Button add,sub,mul,div;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = (EditText)findViewById(R.id.editText1);
        num2 = (EditText)findViewById(R.id.editText2);
        add = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);
        result = (TextView) findViewById(R.id.textView);

        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        float Num1 = 0 , Num2 = 0 , Result = 0;
        String oper = new String();
        if(TextUtils.isEmpty(num1.getText().toString())||TextUtils.isEmpty(num2.getText().toString()))
            return;
        Num1 = Float.parseFloat(num1.getText().toString());
        Num2 = Float.parseFloat(num2.getText().toString());
        switch(v.getId()){
            case R.id.add:
                oper = "+";
                Result = Num1 + Num2;
                break;
            case R.id.sub:
                oper = "-";
                Result = Num1 - Num2;
                break;
            case R.id.mul:
                oper = "*";
                Result = Num1 * Num2;
                break;
            case R.id.div:
                oper = "/";
                Result = Num1 / Num2;
                break;
            default:
                break;
        }
        result.setText(Num1+" "+oper+" "+Num2+" = "+Result);
    }
}