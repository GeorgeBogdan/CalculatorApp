package com.example.bubus.myapplication;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private Integer previous = null;
    private int current = 0;
    private boolean isNewNumber = true;
    private Character op = '\0';

    private void DisplayStrings()
    {
        TextView currentTextView = (TextView)findViewById(R.id.currentTextView);
        currentTextView.setText(Integer.toString(current));
    }
    private void ClearAll()
    {
        current = 0;
        op = null;
        previous = null;
        isNewNumber = true;
        DisplayStrings();
    }
    private void ClearCurrent()
    {
        current = 0;
        isNewNumber = true;
        DisplayStrings();
    }
    private void NumericInput(String number)
    {
        current=(isNewNumber?0:current*10)+Integer.decode(number);
        isNewNumber = false;
        DisplayStrings();
    }
    private void BinaryOperationInput(String operation)
    {
        previous = current;
        isNewNumber = true;
        op = operation.charAt(0);
        DisplayStrings();
    }

    private void EvalInput()
    {
        if (previous != null)
        {
            switch (op)
            {
                case '+':
                    current = previous + current;
                    break;
                case '-':
                    current = previous - current;
                    break;
            }
            previous = null;
        }
        isNewNumber = true;
        DisplayStrings();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayStrings();
    }

    public void numericButton_Click(View view) {
        NumericInput(((Button) view).getText().toString());
    }

    public void binaryButton_Click(View view){
        BinaryOperationInput(((Button) view).getText().toString());
    }

    public void clear_Click(View view){
        ClearCurrent();
    }

    public void clearAll_Click(View view){
        ClearAll();
    }
    public void eval_Click(View view) {
        EvalInput();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
