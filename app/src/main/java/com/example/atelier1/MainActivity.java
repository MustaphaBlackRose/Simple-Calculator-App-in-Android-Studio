package com.example.atelier1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    TextView view1;
    TextView view2;
    Button btnRacine;
    String calculator_text = "";
    String calculator_text2 = "";
    String calculator_text3 ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRacine= (Button) findViewById(R.id.buttonRacine);
        initTextViews();
        btnRacine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val =view1.getText().toString();
                double r = Math.sqrt(Double.parseDouble(val));
                view2.setText(String.valueOf(r));
            }
        });
    }
    private void initTextViews() {
        view1 = (TextView)findViewById(R.id.textView1);
        view2 = (TextView)findViewById(R.id.textView2);
    }
    private void settext (String givenValue ){
        calculator_text = calculator_text + givenValue;
        view1.setText(calculator_text);
    }
    public void OnClick1(View view) {
        settext("1");
    }
    public void OnClick2(View view) {
        settext("2");
    }
    public void OnClick3(View view) {
        settext("3");
    }
    public void OnClick_clear(View view) {
        view1.setText("");
        calculator_text = "";
        view2.setText("");
    }
    public void OnClick4(View view) {
        settext("4");
    }
    public void OnClick5(View view) {
        settext("5");
    }
    public void OnClick6(View view) {
        settext("6");
    }
    public void OnClick_plus(View view) {
        settext("+");
    }
    public void OnClick7(View view) {
        settext("7");
    }
    public void OnClick8(View view) {
        settext("8");
    }
    public void OnClick9(View view) {
        settext("9");
    }
    public void OnClick_moins(View view) {
        settext("-");
    }
    public void OnClick_pourcentage(View view) {
        settext("%");
    }
    public void OnClick0(View view) {
        settext("0");
    }
    public void OnClick_division(View view) {
        settext("/");
    }

    public void OnClick_multiplication(View view) {
        settext("*");
    }

    public void OnClick_puissance(View view) {
        
        settext("^");
    }
    public void OnClick_point(View view) {
        settext(".");
    }
    public void OnClick_egale(View view) {
        Double result = null;
        ScriptEngine engine;
        engine = new ScriptEngineManager().getEngineByName("rhino");
        checkpuissance();
        try {
            result = (double)engine.eval(calculator_text);
        }
        catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
        if(result != null)
        {
            view2.setText(String.valueOf(result.doubleValue()));
        }
    }
    private void checkpuissance() {
        {
            ArrayList<Integer> indexOfPowers = new ArrayList<>();
            for (int i = 0; i < calculator_text.length(); i++) {
                if (calculator_text.charAt(i) == '^')
                    indexOfPowers.add(i);
            }
            calculator_text2 = calculator_text;
            calculator_text3 = calculator_text;
            for (Integer index : indexOfPowers) {
                changetext(index);
            }
            calculator_text2 = calculator_text3;
        }
    }
    private void changetext(Integer index) {
        String numberLeft = "";
        String numberRight = "";
        for(int i = index + 1; i< calculator_text.length(); i++)
        {
            if(isNumeric(calculator_text.charAt(i)))
                numberRight = numberRight + calculator_text.charAt(i);
            else
                break;
        }
        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(calculator_text.charAt(i)))
                numberLeft = numberLeft + calculator_text.charAt(i);
            else
                break;
        }
        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        calculator_text3 = calculator_text3.replace(original,changed);
    }
    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;
        return false;
    }
    boolean leftBracket = true;
    public void OnClick_parentheses1(View view) {
        if(leftBracket)
        {
            settext("(");
            leftBracket = false;
        }
        else
        {
            settext(")");
            leftBracket = true;
        }
    }
}