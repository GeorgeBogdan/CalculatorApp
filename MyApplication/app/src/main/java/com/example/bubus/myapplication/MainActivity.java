package com.example.bubus.myapplication;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private HistoryItem current;
    private boolean isNewNumber;
    private ArrayList<HistoryItem> history = new ArrayList<HistoryItem>();
    private void DisplayStrings()//some change@
    {
        String result = "";
        for (HistoryItem item:history) {
            result=result+" " + item.content;
        }
        TextView historyTextView = (TextView)findViewById(R.id.historyTextView);
        historyTextView.setText(result);
        TextView currentTextView = (TextView)findViewById(R.id.currentTextView);
        currentTextView.setText(current.content);
    }
    private void ClearAll() {
        current = new HistoryItem();
        current.isOperation = false;
        current.content = "0";
        isNewNumber=true;
        history.clear();
        DisplayStrings();
    }
    private void ClearCurrent()
    {
        current = new HistoryItem();
        current.isOperation = false;
        current.content = "0";
        isNewNumber=true;
        DisplayStrings();
    }
    private void NumericInput(String number)
    {
        current.content=(isNewNumber?"":current.content)+number;
        isNewNumber = false;
        DisplayStrings();
    }
    private void BinaryOperationInput(String operation)
    {
        HistoryItem opItem = new HistoryItem();
        opItem.isOperation = true;
        opItem.content = operation;
        history.add(current);
        current = eval(history);
        history.add(opItem);
        isNewNumber = true;
        DisplayStrings();
    }

    private HistoryItem eval(ArrayList<HistoryItem> history) {
        int currennt =0 ;
        int result = 0;
        String curOp = "";
        for (HistoryItem item:history) {
            if (item.isOperation)
            {
                curOp = item.content;
                continue;
            }
            else{
                currennt = Integer.decode(item.content);
            }
            switch (curOp) {
                case "-":
                    result=result-currennt;
                    break;
                case "+":
                    result=result+currennt;
                    break;
                default:
                    result = currennt;
                    break;
            }
        }
        final int finalResult = result;
        return new HistoryItem(){{isOperation=false;content=Integer.toString(finalResult);}};
    }

    private void EvalInput()
    {
        history.add(current);
        current  = eval(history);
        history.clear();
        isNewNumber = true;
        DisplayStrings();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClearAll();
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
