package com.example.twitter_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.wafflecopter.charcounttextview.CharCountTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class RegisterActivity extends AppCompatActivity {

    TextInputEditText name, userID, bornDate;
    TextView counter;
    ImageButton backButton;
    DatePicker datePicker;
    Button changeUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        bornDate = findViewById(R.id.bornDate);
        counter = findViewById(R.id.counter);
        userID = findViewById(R.id.userID);
        datePicker = findViewById(R.id.datePicker);

        backButton = findViewById(R.id.backButton);
        bornDate.setInputType(InputType.TYPE_NULL);
        changeUserID = findViewById(R.id.changeUserID);

        CharCountTextView tvCharCount = (CharCountTextView) findViewById(R.id.counter);
        tvCharCount.setEditText(name);
        tvCharCount.setCharCountChangedListener(new CharCountTextView.CharCountChangedListener() {
            @Override
            public void onCountChanged(int countRemaining, boolean hasExceededLimit) {
                //Enable disable tweet button for example
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                changeUserID.setVisibility(View.GONE);
                datePicker.setVisibility(View.GONE);
                userID.setHint("");
                return false;
            }
        });

        bornDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(bornDate.getWindowToken(), 0);
                datePicker.setVisibility(View.VISIBLE);
                changeUserID.setVisibility(View.GONE);
                userID.setHint("");
                return false;
            }
        });

        userID.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                changeUserID.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.GONE);
                if(userID.getInputType() == InputType.TYPE_CLASS_PHONE){
                    userID.setHint("Teléfono");
                }else{
                    userID.setHint("Correo");
                }
                changeUserID.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (userID.getInputType() == InputType.TYPE_CLASS_PHONE) {
                            userID.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                            changeUserID.setText("Usar teléfono");
                            userID.setText("");
                            userID.setHint("Correo");
                        } else {
                            userID.setInputType(InputType.TYPE_CLASS_PHONE);
                            changeUserID.setText("Usar correo");
                            userID.setText("");
                            userID.setHint("Teléfono");
                        }
                    }
                });
                return false;
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year =  datePicker.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
                String dateFormatted = simpleDateFormat.format(calendar.getTime());

                bornDate.setText(dateFormatted.replaceFirst("^0*", ""));
            }
        });

        userID.setHint("");
        changeUserID.setVisibility(View.GONE);
        datePicker.setVisibility(View.GONE);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void closeKeyboard(View view){
        view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager inm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        changeUserID.setVisibility(View.GONE);
        datePicker.setVisibility(View.GONE);
    }

}