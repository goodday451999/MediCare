package com.example.mohit.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.View;
import android.view.WindowManager;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText First_Input;
    EditText Second_Input;
    EditText Third_Input;
    EditText Fourth_Input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    @Override                        // for keyboard goes disappear
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        if (super.dispatchTouchEvent(ev)) return true;
        else return false;
    }


        public void onClick(View view){
            Intent i = new Intent(this,Hipage.class);

            First_Input = (EditText) findViewById(R.id.editText);
            String usermessage = First_Input.getText().toString();

            if (usermessage.matches("")) {
                Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
                return;
            }

            Second_Input = (EditText) findViewById(R.id.editText2);
            String usermessage2 = Second_Input.getText().toString();

            if (usermessage2.matches("")) {
                Toast.makeText(this, "You did not enter a password", Toast.LENGTH_SHORT).show();
                return;
            }

            Third_Input = (EditText) findViewById(R.id.editText3);
            String usermessage3 = Third_Input.getText().toString();

            if (usermessage3.matches("")) {
                Toast.makeText(this, "You did not enter a email", Toast.LENGTH_SHORT).show();
                return;
            }

        {
            boolean x = isEmailValid(usermessage3);

            if(!x) {
                Toast.makeText(this, "You did not enter a valid email", Toast.LENGTH_SHORT).show();
                return;
            }
        }


            Fourth_Input = (EditText) findViewById(R.id.editText4);
            String usermessage4 = Fourth_Input.getText().toString();

            if (usermessage4.matches("")) {
                Toast.makeText(this, "You did not enter a contact no", Toast.LENGTH_SHORT).show();
                return;
            }

        {
            boolean y = isMobileValid(usermessage4);

            if(!y) {
                Toast.makeText(this, "You did not enter a valid phone no", Toast.LENGTH_SHORT).show();
                return;
            }
        }

            i.putExtra("firstmessage",usermessage);
            startActivity(i);


        }
    @Override
    public void onPause(){
        super.onPause();

        // Clear all value here
       // editTextone.setText("");
       // editTexttwo.setText("");
        First_Input.setText("");
        Second_Input.setText("");
        Third_Input.setText("");
        Fourth_Input.setText("");
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isMobileValid(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

}
