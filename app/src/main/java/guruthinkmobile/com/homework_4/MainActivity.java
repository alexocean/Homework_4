package guruthinkmobile.com.homework_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    TextView tvA;
    TextView tvB;
    TextView tvResult;
    Button btnCalculate_AM;
    EditText etOpertaor_AM;
    Switch swTheme_AM;
    Intent starterIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        starterIntent = getIntent();
        if (Constants.theme.equals("LIGHT")) setTheme(R.style.MyThemeLight);
        else setTheme(R.style.MyThemeDark);

        setContentView(R.layout.activity_main);

        tvA = (TextView) findViewById(R.id.tvA);
        tvB = (TextView) findViewById(R.id.tvB);
        btnCalculate_AM = (Button) findViewById(R.id.btnCalculate_AM);
        etOpertaor_AM = (EditText) findViewById(R.id.etOperator_AM);
        tvResult = (TextView) findViewById(R.id.tvResult);
        swTheme_AM = (Switch) findViewById(R.id.swTheme);

        tvA.setOnClickListener(this);
        tvB.setOnClickListener(this);
        btnCalculate_AM.setOnClickListener(this);
        swTheme_AM.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, InputActivity.class);
        switch (v.getId()) {
            case R.id.tvA:
                startActivityForResult(intent, Constants.REQUEST_CODE_A);
                break;
            case R.id.tvB:
                startActivityForResult(intent, Constants.REQUEST_CODE_B);
                break;
            case R.id.btnCalculate_AM:
                if (Constants.a.equals("") || Constants.b.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.ERROR_MESSAGE, Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                        String s = Integer.toString(calculate());
                        if (Constants.ERROR_CODE_1 == 1){
                            tvResult.setText(R.string.ERROR_STRING);
                            Constants.ERROR_CODE_1 = 0;
                        }
                        else {
                            tvResult.setText(s);
                        }
                }
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case Constants.REQUEST_CODE_A:
                    tvA.setText(data.getStringExtra("value"));
                    Constants.a = tvA.getText().toString();
                    break;
                case Constants.REQUEST_CODE_B:
                    tvB.setText(data.getStringExtra("value"));
                    Constants.b = tvB.getText().toString();
                    break;
            }
        else {
            Toast.makeText(getApplicationContext(), R.string.ERROR_MESSAGE, Toast.LENGTH_LONG).show();
        }
    }
    public int calculate(){
        int r = 0;
        if (!Constants.a.equals("") && !Constants.b.equals("")) {
            int a = Integer.parseInt(Constants.a);
            int b = Integer.parseInt(Constants.b);
            switch (etOpertaor_AM.getText().toString()) {
                case ("+"):
                    r = a + b;
                    break;
                case ("-"):
                    r = a - b;
                    break;
                case ("*"):
                    r = a * b;
                    break;
                case ("/"):
                    if (b == 0) {
                        Toast.makeText(this, R.string.ERROR_DIVIDE_BY_ZERO, Toast.LENGTH_LONG).show();
                        tvResult.setText(R.string.ERROR_STRING);
                        Constants.ERROR_CODE_1 = 1;
                        break;
                    }
                    else {
                        r = a / b;
                        break;
                    }
                default:
                    etOpertaor_AM.setText("");
                    Toast.makeText(getApplicationContext(), R.string.ERROR_NO_OPERATOR, Toast.LENGTH_LONG).show();
                    Constants.ERROR_CODE_1 = 1;
                    break;
            }
        }
        return r;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            Constants.theme = "DARK";
            this.finish();
            startActivity(starterIntent);
        } else {
            Constants.theme = "LIGHT";
            this.finish();
            startActivity(starterIntent);
        }
    }
}
