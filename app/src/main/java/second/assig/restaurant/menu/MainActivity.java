package second.assig.restaurant.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

	private ControlLogin ctlLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        ctlLogin = (ControlLogin)findViewById(R.id.CtlLogin);
        
        ctlLogin.setOnLoginListener(new OnLoginListener() 
        {
			public void onLogin(String usuario, String password) 
			{
				//UserName and Password Validation
				if (usuario.equals("android") && password.equals("coursera"))
					{ctlLogin.setMensaje("Correct Login!");
					Intent i =new Intent("second.assig.restaurant.menu.SecondActivity");
					startActivity(i);
					}
				else
					ctlLogin.setMensaje("Try Again!");
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
