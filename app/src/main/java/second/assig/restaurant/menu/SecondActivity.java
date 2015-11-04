package second.assig.restaurant.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;

public class SecondActivity extends Activity {
    ListView l1, l2,l3;
    String firstcourse, secondcourse, dessert;
    
    String[] firstCourse = {
            "Balsamic raw tuna",
            "Foie gras roasted in seaweed",
            "Trout tartare and orange",        
    					};
    String[] secondCourse = {
            "Slices of tempered beef steak",
            "Red prawn on a seabed",
            "Home yolk with toasted butter",        
    					};
    String[] desserts = {
            "Miso and white chocolate creamy",
            "Forest red fruits",
            "Caipirinha melon with jelly",        
    					};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, firstCourse);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, secondCourse);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, desserts);

        
        
        //---List View---
        l1 = (ListView) findViewById(R.id.ListView1);
        l1.setAdapter(adapter);
        l1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                int index = arg2;
                Toast.makeText(getBaseContext(),
                    "You have selected item : " + firstCourse[index], 
                    Toast.LENGTH_SHORT).show();
                firstcourse=firstCourse[index];
            }
        });

        //---List View---
        l2 = (ListView) findViewById(R.id.ListView2);
        l2.setAdapter(adapter1);
        l2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                int index = arg2;
                Toast.makeText(getBaseContext(),
                    "You have selected item : " + secondCourse[index],
                    Toast.LENGTH_SHORT).show();
                secondcourse=secondCourse[index];
            }
        });
        //---List View---
        l3 = (ListView) findViewById(R.id.ListView3);
        l3.setAdapter(adapter2);
        l3.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                int index = arg2;
                Toast.makeText(getBaseContext(),
                    "You have selected item : " + desserts[index],
                    Toast.LENGTH_SHORT).show();
                dessert=desserts[index];
            }
        });

    }
  
    public void onClick(View view) {
    	Intent i =new Intent("second.assig.restaurant.menu.ThirdActivity");
    	
    	MenuClass myObject = new MenuClass();
    	myObject.setfirst(firstcourse);
    	myObject.setsecond(secondcourse);
    	myObject.setdessert(dessert);

        // put the selected items in the JSON
        writeToJSON(myObject);

    	i.putExtra("MyObject", myObject);
    	
    	startActivity(i);
    	
    }

    public void writeToJSON(MenuClass menu){
        Random r = new Random();
        int tbl_num = r.nextInt(20);
        int commensals = r.nextInt(10);
        String served = "no";
        String appetizer = menu.firstc();
        String main = menu.secondc();
        String dessert = menu.dessert;

        JSONObject new_table = new JSONObject();
        JSONObject menu_courses = new JSONObject();

        try{
            menu_courses.put("appetizer", appetizer);
            menu_courses.put("main", main);
            menu_courses.put("dessert", dessert);
        } catch (JSONException e){
            e.printStackTrace();
        }

        try{
            new_table.put("tbl_num", tbl_num);
            new_table.put("commensals", commensals);
            new_table.put("served", served);
            new_table.put("menu", menu_courses);


        } catch (JSONException e){
            e.printStackTrace();
        }


        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/download");
        File file = new File(dir, "tables.json");

        try{
            FileOutputStream f = new FileOutputStream(file, true);
            // FileOutputStream f = openFileOutput(file.getAbsolutePath(), MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(f);

            outputStreamWriter.append(",");
            outputStreamWriter.append(new_table.toString());
            outputStreamWriter.close();
            f.close();

            /*

            PrintWriter pw = new PrintWriter(f);
            pw.println(new_table.toString());
            pw.flush();
            pw.close();
            f.close();
            */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
