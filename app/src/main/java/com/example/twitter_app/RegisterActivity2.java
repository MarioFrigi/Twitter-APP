package com.example.twitter_app;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity2 extends AppCompatActivity {

    TextView policities;
    ImageButton backButton;
    EditText name, userID, birthday;
    Button logButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        Bundle datos = this.getIntent().getExtras();
        String nameRecieved = datos.getString("name");
        String userIDRecieved  = datos.getString("userID");
        String bornDateRecieved  = datos.getString("bornDate");

        name = findViewById(R.id.name);
        userID = findViewById(R.id.userID);
        birthday = findViewById(R.id.birthday);

        name.setText(nameRecieved);
        userID.setText(userIDRecieved);
        birthday.setText(bornDateRecieved);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity2.this, RegisterActivity.class);
                intent.putExtra("id", 1);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("userID", userID.getText().toString());
                intent.putExtra("bornDate", birthday.getText().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        userID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity2.this, RegisterActivity.class);
                intent.putExtra("id", 2);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("userID", userID.getText().toString());
                intent.putExtra("bornDate", birthday.getText().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity2.this, RegisterActivity.class);
                intent.putExtra("id", 3);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("userID", userID.getText().toString());
                intent.putExtra("bornDate", birthday.getText().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });


        policities = findViewById(R.id.politicies);
        backButton = findViewById(R.id.backButton);

        String text = "Al registrarte, aceptas nuestros Terminos, Politica de Privacidad y uso de Cookies.";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Uri uriUrl = Uri.parse("https://twitter.com/es/tos");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ds.linkColor);
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Uri uriUrl = Uri.parse("https://twitter.com/es/privacy");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ds.linkColor);
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan3 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Uri uriUrl = Uri.parse("https://help.twitter.com/es/rules-and-policies/twitter-cookies");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ds.linkColor);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan1, 33, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan2, 43, 65, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan3, 75, 82, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        policities.setText(ss);
        policities.setMovementMethod(LinkMovementMethod.getInstance());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        logButton = findViewById(R.id.logButton);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotificationCode();
            }
        });
    }

    public void createNotificationCode(){

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "notificacion")
                        .setSmallIcon(R.drawable.applogo)
                        .setContentTitle("Codigo de verificación")
                        .setContentText("89ad489sA4D");

        Notification notificacion = builder.build();
        notificationManager.notify(0, notificacion);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegisterActivity2.this, RegisterActivity.class);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("userID", userID.getText().toString());
        intent.putExtra("bornDate", birthday.getText().toString());
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}