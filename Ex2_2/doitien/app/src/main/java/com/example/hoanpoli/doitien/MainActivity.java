package com.example.hoanpoli.doitien;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String URL_DATA_CURRENCY = "http://www.vietcombank.com.vn/exchangerates/ExrateXML.aspx";
    Button bConvert ;
    EditText etInValue , etOutValue ;
    Spinner sInCurrency , sOutCurrency ;
    Map<String, Currency> lCurrency ;
    XmlHanlder dataHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Load UI
        bConvert = (Button) findViewById(R.id.bConvert);
        sOutCurrency = (Spinner) findViewById(R.id.sOutCurrency);
        sInCurrency = (Spinner) findViewById(R.id.sInCurrency);
        etInValue = (EditText) findViewById(R.id.etInValue);
        etOutValue = (EditText) findViewById(R.id.etOutValue);

        if(isOnline()) {
            dataHandler = new XmlHanlder(URL_DATA_CURRENCY);
        }
        else {
            Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show();
        }

        lCurrency = dataHandler.GetDataCurrency();

        ArrayAdapter<Price> adapter = new ArrayAdapter<Price>(this,
                android.R.layout.simple_spinner_item, Price.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sOutCurrency.setAdapter(adapter);
        sInCurrency.setAdapter(adapter);

        bConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lCurrency.isEmpty()) {
                    Toast.makeText(getBaseContext(), "empty data!", Toast.LENGTH_LONG).show();
                } else {
                    OnConvert();
                }
            }
        });
    }

    void OnConvert(){
        if (etInValue.getText().length() == 0){
            Toast.makeText(this, "Please insert value", Toast.LENGTH_LONG).show();
        } else {
            Double value = Double.parseDouble(String.valueOf(etInValue.getText()));
            String fromCur = sInCurrency.getSelectedItem().toString();
            String toCur = sOutCurrency.getSelectedItem().toString();
            Price from = Price.valueOf(fromCur);
            Price to = Price.valueOf(toCur);
            Double result = (TransferCurrency(value, from, to));
            etOutValue.setText(result.toString());
        }
    }

    Double TransferCurrency(Double _value, Price from, Price to){
        Currency fromCur = lCurrency.get(from.toString());
        Currency toCur = lCurrency.get(to.toString());

        if (to == Price.VND){
            return _value * fromCur.GetSell();
        } else {
            if (from == Price.VND){
                return _value / toCur.GetSell();
            } else {
                return _value * fromCur.GetSell() / toCur.GetSell();
            }
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}