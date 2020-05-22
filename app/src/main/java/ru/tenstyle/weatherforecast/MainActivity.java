package ru.tenstyle.weatherforecast;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Log.d("myLog", "onCreate()");
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show();

        // Временный код для проверки сохранения данных при смене ориентации экрана

        temporarySavingState();

    }

    private void temporarySavingState() {
        final TextView textCounter = findViewById(R.id.textCounter);
        final TextView textCounter2 = findViewById(R.id.textCounter2);
        final Singleton presenter = Singleton.getInstance();
        textCounter.setText(((Integer)presenter.getCounter()).toString());
        textCounter2.setText(((Integer)presenter.getCounter2()).toString());
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {      // Обработка нажатий
            @Override
            public void onClick(View v) {
                presenter.incrementCounter();
                textCounter.setText(((Integer)presenter.getCounter()).toString());
                textCounter2.setText(((Integer)presenter.getCounter2()).toString());
            }
        });
    }

    private void initViews() {
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        String[] cities = getResources().getStringArray(R.array.cities);
        List<String> catList = Arrays.asList(cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, catList);
        autoCompleteTextView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("myLog", "onStart()");
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Log.d("myLog", "onRestoreInstanceState()");
        Toast.makeText(getApplicationContext(), "onRestoreInstanceState()",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("myLog", "onResume()");
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("myLog", "onPause()");
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.d("myLog", "onSaveInstanceState()");
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("myLog", "onStop()");
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("myLog", "onRestart()");
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("myLog", "onDestroy()");
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
    }
}
