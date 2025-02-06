package com.example.sain_bse_8a;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // hooks
    EditText etCnic;
    Button btnClear, btnSubmit;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etCnic.getText().toString().trim();
                //if(TextUtils.isEmpty(id))

                parseID(id);


            }
        });


    }

    private void parseID(String id)
    {
        if(id.isEmpty())
        {
            etCnic.setError("id can't be empty");
            return;
        }
        if(id.length()!=13)
        {
            Toast.makeText(MainActivity.this, "invalid length of id", Toast.LENGTH_LONG).show();
            return;
        }

        String dobSeq = id.substring(0, 6);
        int gen = Integer.parseInt(id.substring(6, 10));
        char citizenCh = id.charAt(10);
        char validityCh = id.charAt(12);

        String []months = new String[]{"","January", "Feb", "March","April", "May", "June", "July", "Aug",
                "Sept", "Oct", "Nov", "Dec"};

        String result = "Date of Birth : "+dobSeq.substring(4, 6)+" "+
                months[Integer.parseInt(dobSeq.substring(2, 4))]+" 19"+dobSeq.substring(0, 2)+"\n";
        result+="Gender : ";
        if(gen>=0 && gen<=4999)
            result += "Female";
        else
            result += "Male";

        result+="\nCitizenship : ";

        if(citizenCh=='0')
            result+="SA Citizen";
        else
            result+="Permanent Resident";

        result+="\nValidity : ";

        if(validityCh == '0')
            result+="Invalid";
        else
            result+="Valid";

        tvResult.setText(result);

        Intent i = new Intent(MainActivity.this, Home.class);
        startActivity(i);
        finish();
    }

    private void init()
    {
        etCnic = findViewById(R.id.etCnic);
        btnClear = findViewById(R.id.btnClear);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.tvResult);
    }

    // this is one way to attach method with button clicks
    public void btnClearClicked(View view)
    {
        etCnic.setText("");
    }
}