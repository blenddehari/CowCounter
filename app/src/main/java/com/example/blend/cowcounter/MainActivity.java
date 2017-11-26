package com.example.blend.cowcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public static String LOGGING_TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOGGING_TAG, getLocalClassName() + ".onCreate is called!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TableLayout table = (TableLayout) findViewById(R.id.breedtable);

        final EditText editText_breed = (EditText) findViewById(R.id.editText_breed);
        final EditText editText_id = (EditText) findViewById(R.id.editText_id);
        final TextView numberOfCows = (TextView) findViewById(R.id.number_of_cows);


        // clear id and breed fields
        Button RejectButton = (Button) findViewById(R.id.button_rej);
        RejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_breed.setText("");
                editText_id.setText("");

            }
        });

        Button AddButton = (Button) findViewById(R.id.button_add);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updaterow(editText_breed, editText_id, numberOfCows, table);

            }
        });


        //button CLEAR to remove all added table rows and reset the numberOfCows
        Button ClearButton = (Button) findViewById(R.id.button_clear);
        ClearButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                int childCount = table.getChildCount();
                table.removeViews(1, childCount - 1);
                numberOfCows.setText("Cows:");
            }

        });

    }

    public void updaterow(EditText breedEdit, EditText idEdit, final TextView numberOfCows, final TableLayout table) {

        final TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));

        TextView tv_breed = new TextView(this);
        TextView tv_id = new TextView(this);

        String breed = breedEdit.getText().toString().trim();
        String id = idEdit.getText().toString().trim();

        //To check breed is empty or not
        if (TextUtils.isEmpty(breed)) {
            //breed is empty
            Toast.makeText(this, "Please enter the breed.", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        //To check id is empty or not
        if (TextUtils.isEmpty(id)) {
            //id is empty
            Toast.makeText(this, "Please enter an id.", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        int breed_num = Integer.parseInt(breed);
        int id_num = Integer.parseInt(id);
        if (breed_num < 0 || breed_num > 999) {
            Toast.makeText(this, "Please enter a number with a value between 0 and 999.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (id_num < 0 || id_num > 999) {
            Toast.makeText(this, "Please enter a number with a value between 0 and 999.", Toast.LENGTH_SHORT).show();
            return;
        }

        //checking if the cow is already entered!
        for(int i =0; i < table.getChildCount(); i++) {
            TableRow tableRow = (TableRow) table.getChildAt(i);
            TextView idTextView = (TextView) tableRow.getVirtualChildAt(1);
            if (idTextView.getText().toString().equals(id)) {
                Toast.makeText(this, "You cannot enter the same cow twice dude!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    //making new tabletext
        tv_breed.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,(float)1.0));
        tv_breed.setText(breedEdit.getText());
        tv_breed.setGravity(Gravity.CENTER);
        tv_breed.setBackgroundResource(R.drawable.table_border);

        tv_id.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,(float)1.0));
        tv_id.setText(idEdit.getText());
        tv_id.setGravity(Gravity.CENTER);
        tv_id.setBackgroundResource(R.drawable.table_border);

    //add the tabletexts to the tablerow
        tr.addView(tv_breed);
        tr.addView(tv_id);

    //add the tablerow to the table, index:1 means adding the table row at the index 1 of the table
        table.addView(tr,1);


    //display number of cows in the table
        numberOfCows.setText("Cows: "+(table.getChildCount()-1));



}

    //saving the information on the table after switching to landscape
    int cnt = 0;

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("breed", cnt);
        outState.putInt("ID", cnt);
        Log.d(LOGGING_TAG, "onSaveInstanceState");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cnt = savedInstanceState.getInt("breed");
        cnt = savedInstanceState.getInt("ID");
        Log.d(LOGGING_TAG, "onRestoreInstanceState");
    }
}
