package second.assig.restaurant.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Guillermo on 04/11/2015.
 */
public class JSONActivity extends Activity {
    ListView listView;

    // JSON Node names
    private static final String TAG_TABLES = "tables";
    private static final String TAG_TBLNUM = "tbl_num";
    private static final String TAG_COMM = "commensal";
    private static final String TAG_SERVED = "served";
    private static final String TAG_MENU = "menu";
    private static final String TAG_MENU_APP = "appetizer";
    private static final String TAG_MENU_MAIN = "main";
    private static final String TAG_MENU_DESS = "dessert";

    // contacts JSONArray
    JSONArray tables = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> tableList;




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        tableList = new ArrayList<HashMap<String, String>>();

        listView = (ListView) findViewById(R.id.json_listview);

        /* if we want to read data from /res */
         String json_string = loadJSONFromAsset();

        /* if we want to read data from SD */
        /* String json_string = loadJSONFromSD(); */



        try {
            JSONObject jsonObj = new JSONObject(json_string);

            // Getting JSON Array node
            tables = jsonObj.getJSONArray(TAG_TABLES);

            // Looping through all contacts
            for(int i=0;i<tables.length();i++){
                JSONObject t = tables.getJSONObject(i);

                String tblNum = t.getString(TAG_TBLNUM);
                String commensals = t.getString(TAG_COMM);
                String served = t.getString(TAG_SERVED);

                // Phone node is JSON Object
                JSONObject menu = t.getJSONObject(TAG_MENU);
                String appetizer = menu.getString(TAG_MENU_APP);
                String main = menu.getString(TAG_MENU_MAIN);
                String dessert = menu.getString(TAG_MENU_DESS);

                // tmp hashmap for single contact
                HashMap<String, String> table = new HashMap<String, String>();
                // adding each child node to HashMap key => value
                table.put(TAG_TBLNUM, tblNum);
                table.put(TAG_COMM, commensals);
                table.put(TAG_SERVED, served);
                table.put(TAG_MENU_APP, appetizer);
                table.put(TAG_MENU_MAIN, main);
                table.put(TAG_MENU_DESS, dessert);

                // adding contact to contact list
                tableList.add(table);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                this, tableList,
                R.layout.list_item, new String[] { TAG_TBLNUM, TAG_SERVED,
                TAG_MENU_APP, TAG_MENU_MAIN, TAG_MENU_DESS }, new int[] { R.id.tbl_num,
                R.id.served, R.id.appetizer, R.id.main, R.id.dessert });

        listView.setAdapter(adapter);
    }

    public String loadJSONFromAsset(){
        String json = null;
        try{
            InputStream is = this.getResources().openRawResource(R.raw.menus);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }

    public String loadJSONFromSD(){
        String json = null;
        try{
            File dir = Environment.getExternalStorageDirectory();
            File file = new File(dir.getAbsolutePath(),"tables.json");
            FileInputStream stream = new FileInputStream(file);

            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            json = Charset.defaultCharset().decode(bb).toString();
            stream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
