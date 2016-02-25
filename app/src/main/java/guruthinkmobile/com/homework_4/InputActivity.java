package guruthinkmobile.com.homework_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends Activity implements View.OnClickListener {
    EditText etInputValue;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Constants.theme.equals("LIGHT")){
            setTheme(R.style.MyThemeLight);
        }   else
        {
            setTheme(R.style.MyThemeDark);
        }
        setContentView(R.layout.activity_input);
        etInputValue = (EditText) findViewById(R.id.etInputValue);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String input = etInputValue.getText().toString();
        if (!input.equals("")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("value", etInputValue.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.ERROR_MESSAGE, Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
