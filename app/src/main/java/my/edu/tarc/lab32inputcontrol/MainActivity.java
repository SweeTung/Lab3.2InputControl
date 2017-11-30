package my.edu.tarc.lab32inputcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;


    private int premium=0, total, smokeFee=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking UI
        spinnerAge = (Spinner) findViewById(R.id.spinnerAge);
        radioGroupGender=(RadioGroup)findViewById(R.id.radioGroupGender);
        radioButtonMale=(RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale=(RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxSmoker=(CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium=(TextView)findViewById(R.id.textViewPremium);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                   this,
                    R.array.age_group,
                    android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );
        spinnerAge.setOnItemSelectedListener(this);
        spinnerAge.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"Position : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calculatePremium (View view){
        int position;

        position = spinnerAge.getSelectedItemPosition();

        int indexGender;
        indexGender = radioGroupGender.getCheckedRadioButtonId();
        if(indexGender == R.id.radioButtonMale){
            //TODO: CALCULATE PREMIUM For MALe
            if(position == 0)
                premium=50;
            else if(position == 1)
                premium=55;
            else if(position == 2)
                premium=60+50;
            else if (position ==3)
                premium=70+100;
            else if (position ==4)
                premium=120+100;
            else if (position==5)
                premium=160+50;
            else if(position == 6)
                premium=200;
            else if(position == 7)
                premium=250;
        }
        else{
            if(position == 0)
                premium=50;
            else if(position == 1)
                premium=55;
            else if(position == 2)
                premium=60;
            else if(position == 3)
                premium=70;
            else if(position == 4)
                premium=120;
            else if(position == 5)
                premium=160;
            else if(position == 6)
                premium=200;
            else if(position == 7)
                premium=250;

        }


        if(checkBoxSmoker.isChecked()){
            //TODO: CALCULATE PREMIUM OF SMOKER
            if(position == 0)
               smokeFee=0;
            else if(position == 1)
                smokeFee=0;
            else if(position == 2)
                smokeFee=0;
            else if (position ==3)
                smokeFee=100;
            else if (position ==4)
                smokeFee=150;
            else if (position==5)
                smokeFee=150;
            else if(position == 6)
                smokeFee=250;
            else if(position == 7)
                smokeFee=250;
        }
        total= premium + smokeFee;
    //textViewPremium.setText(getString(R.string.premium)+ " : " +total);

        Locale locale = Locale.getDefault();

        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String currencyText = fmt.format(total);
        //Display premium
        textViewPremium.setText(getString(R.string.premium)+" : "+currencyText);


    }
    public void reserPremium(View view){
    textViewPremium.setText("");
        smokeFee=0;
        premium=0;
        total=0;
        radioButtonMale.hasFocus();
        checkBoxSmoker.setChecked(false);
        spinnerAge.setSelection(0);

    }
}
