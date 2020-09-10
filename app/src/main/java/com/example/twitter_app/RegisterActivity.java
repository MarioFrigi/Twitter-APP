package com.example.twitter_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    Button changeUserID, logButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        bornDate = findViewById(R.id.bornDate);
        userID = findViewById(R.id.userID);
        datePicker = findViewById(R.id.datePicker);
        CharCountTextView tvCharCount = (CharCountTextView) findViewById(R.id.counter);
        counter = findViewById(R.id.counter);
        backButton = findViewById(R.id.backButton);
        bornDate.setInputType(InputType.TYPE_NULL);
        changeUserID = findViewById(R.id.changeUserID);
        logButton = findViewById(R.id.logButton);

        Bundle datos = this.getIntent().getExtras();
        if(datos != null) {
            String nameRecieved = datos.getString("name");
            String userIDRecieved = datos.getString("userID");
            String bornDateRecieved = datos.getString("bornDate");
            Integer id = datos.getInt("id");
            name.setText(nameRecieved);
            Integer namelenght = 50 - name.length();
            counter.setText(namelenght.toString());
            userID.setText(userIDRecieved);
            bornDate.setText(bornDateRecieved);
            String[] date = bornDateRecieved.split(" ");
            int day = Integer.parseInt(date[0]);
            String month = date[1];
            switch(month) {
                case "January":
                case "enero":
                    month = "1";
                    break;

                case "Febuary":
                case "febrero":
                    month = "2";
                    break;

                case "March":
                case "marzo":
                    month = "3";
                    break;

                case "April":
                case "abril":
                    month = "4";
                    break;

                case "May":
                case "mayo":
                    month = "5";
                    break;

                case "June":
                case "junio":
                    month = "6";
                    break;

                case "July":
                case "julio":
                    month = "7";
                    break;

                case "August":
                case "agosto":
                    month = "8";
                    break;

                case "September":
                case "septiembre":
                    month = "9";
                    break;

                case "October":
                case "octubre":
                    month = "10";
                    break;

                case "November":
                case "noviembre":
                    month = "11";
                    break;

                case "December":
                case "diciembre":
                    month = "12";
                    break;
            }
            int year = Integer.parseInt(date[2]);
            datePicker.init(year, Integer.parseInt(month)-1, day,null);
            focusSelector(id);
        }

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

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, RegisterActivity2.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("userID", userID.getText().toString());
                intent.putExtra("bornDate", bornDate.getText().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

    public void focusSelector(Integer id){
        if(id == 1){
            name.requestFocus();
        }else if (id ==2){
            userID.requestFocus();
            changeUserID = findViewById(R.id.changeUserID);
            changeUserID.setVisibility(View.VISIBLE);
        }else if (id == 3){
            bornDate.requestFocus();
        }
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