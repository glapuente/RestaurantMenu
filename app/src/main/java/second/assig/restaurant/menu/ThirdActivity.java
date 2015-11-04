package second.assig.restaurant.menu;

import second.assig.restaurant.menu.MenuClass;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ThirdActivity extends Activity {

	public MenuClass myobj;
    private Button btnJSON;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        
        MenuClass obj = (MenuClass) getIntent().getSerializableExtra("MyObject");
        
        final EditText TxtFirst = (EditText)findViewById(R.id.TxtFirst);
        TxtFirst.setText(obj.firstc());
        
        final EditText TxtSecond = (EditText)findViewById(R.id.TxtSecond);
        TxtSecond.setText(obj.secondc());
        
        final EditText TxtDessert = (EditText)findViewById(R.id.TxtDessert);
        TxtDessert.setText(obj.dessertc());

        btnJSON = (Button) findViewById(R.id.btn_JSON);
        btnJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("second.assig.restaurant.menu.JSONActivity");
                startActivity(i);
                //finish();

            }
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
