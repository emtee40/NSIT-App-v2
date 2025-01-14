package nsit.app.com.nsitapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import functions.ButtonAnimation;
import functions.Constant;
import functions.Val;

public class ChooseClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Constant {



    String[] sem={"Semester","Sem 1","Sem 2","Sem 3","Sem 4","Sem 5","Sem 6","Sem 7","Sem 8"};
    String[] branch={"Branch","COE","IT","ECE","ICE","MPAE","BT","ME"};
    String[] section={"Section","Sec 1","Sec 2","Sec 3"};
    String[] section1={"Section","Sec 1","Sec 2"};
    String[] section2={"Section","Sec 1"};

    String[] x ={"Section"};
    Spinner Sem,Branch,Sec;
    Boolean s,b,sect;
    Button set;
    int se,br,sec;
    SharedPreferences sh;
    SharedPreferences.Editor e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Sem = (Spinner) findViewById(R.id.sem);
        Branch = (Spinner) findViewById(R.id.branch);
        Sec = (Spinner) findViewById(R.id.sec);
        set = (Button) findViewById(R.id.set);

        ArrayAdapter adapte = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,x);
        Sec.setAdapter(adapte);
        Sec.setOnItemSelectedListener(this);


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,sem);
        Sem.setAdapter(adapter);
        Sem.setOnItemSelectedListener(this);

        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,branch);
        Branch.setAdapter(adapter2);
        Branch.setOnItemSelectedListener(this);



        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!s) {
                    SnackbarManager.show(
                            Snackbar.with(getApplicationContext())
                                    .text("Please Enter Semester")
                                    .duration(Snackbar.SnackbarDuration.LENGTH_SHORT), ChooseClass.this);
                } else if (!b) {
                    SnackbarManager.show(
                            Snackbar.with(getApplicationContext())
                                    .text("Please Enter Branch")
                                    .duration(Snackbar.SnackbarDuration.LENGTH_SHORT), ChooseClass.this);
                } else if (!sect && br!=7) {
                    SnackbarManager.show(
                            Snackbar.with(getApplicationContext())
                                    .text("Please Enter Section")
                                    .duration(Snackbar.SnackbarDuration.LENGTH_SHORT), ChooseClass.this);
                } else {

                    Log.e("here", se + "\n" + sec + "\n" + br + "\n" + "  ");
                    String timetableid = null;
                    sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    e = sh.edit();
                    e.putInt(CALENDAR_SEM, se);
                    e.putInt(CALENDAR_BRANCH, br);
                    e.putInt(CALENDAR_SECTION, sec);
                    e.putBoolean(IS_CLASS_SET, true);
                    e.putBoolean(IS_TIME_TABLE_CHANGED,true);
                    e.commit();

                    finish();
                }
                ButtonAnimation btnAnimation = new ButtonAnimation();
                btnAnimation.animateButton(view, getApplicationContext());
            }
        });
        setTitle("Select Your Class ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.sem) {
            if(position!=0){
                s=true;
                se = position;
            }else {
                s = false;
            }
        }
        if(parent.getId()==R.id.branch) {
            if(position==0){
                ArrayAdapter adapte = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, x);
                Sec.setAdapter(adapte);
                b = false;
            }else {

                b=true;
                br = position;
                if(position==2){
                    ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,section1);
                    Sec.setAdapter(adapter2);


                }else if(position==6 || position==7){
                    ArrayAdapter adapte = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, section2);
                    Sec.setAdapter(adapte);

                }

                else {
                    ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,section);
                    Sec.setAdapter(adapter2);
                }
            }
        }

        if(parent.getId()==R.id.sec){
            if(position==0){
                sect=false;
            }else{
                sect=true;
                sec = position;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}