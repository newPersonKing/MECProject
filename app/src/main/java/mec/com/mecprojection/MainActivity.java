package mec.com.mecprojection;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.leon.lfilepickerlibrary.LFilePicker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mec.com.mecprojection.dao.DaoUntils;
import mec.com.mecprojection.master.CheckDataDao;
import mec.com.mecprojection.model.CheckData;
import mec.com.mecprojection.until.ExcelUtil;

public class MainActivity extends AppCompatActivity {

    Spinner s_sex,s_age,s_sf;
    EditText et_number;
    Button bt_submit;
    int sex=1;
    int age=1;
    int type=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s_sex = findViewById(R.id.s_sex);
        s_age = findViewById(R.id.s_age);
        s_sf = findViewById(R.id.s_sf);
        et_number = findViewById(R.id.et_number);
        bt_submit = findViewById(R.id.bt_submit);

        s_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                age = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                age = 1;
            }
        });

        s_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sex = 1;
            }
        });

        s_sf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
              type = 1;
            }
        });

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CheckData> datas=DaoUntils.queryCheckData(age,type);
                String num = et_number.getText().toString();
                if(num.isEmpty()){
                    return;
                }
                int wolizhi = Integer.parseInt(num);
                if(datas.size()>0){
                    CheckData checkData = datas.get(0);
                   if(sex==1){
                       if(wolizhi<checkData.getMaleWeak()){
                         Toast.makeText(MainActivity.this,"Weak",Toast.LENGTH_SHORT).show();
                       }else if(wolizhi>checkData.getMaleStrong()){
                           Toast.makeText(MainActivity.this,"Strong",Toast.LENGTH_SHORT).show();
                       }else {
                           Toast.makeText(MainActivity.this,"Nomer",Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       if(wolizhi<checkData.getFemaleWeak()){
                           Toast.makeText(MainActivity.this,"Weak",Toast.LENGTH_SHORT).show();
                       }else if(wolizhi>checkData.getFemaleWeak()){
                           Toast.makeText(MainActivity.this,"Strong",Toast.LENGTH_SHORT).show();
                       }else {
                           Toast.makeText(MainActivity.this,"Nomer",Toast.LENGTH_SHORT).show();
                       }
                   }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu,menu);
        return true;
    }
    int REQUESTCODE_FROM_ACTIVITY = 1000;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        if(item.getItemId()==R.id.import_file){
            new LFilePicker()
                    .withActivity(MainActivity.this)
                    .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                    .withStartPath("/storage")
                    .withIsGreater(false)
                    .withFileSize(500 * 1024)
                    .withMaxNum(1)
                    .start();
        }else if (item.getItemId() == R.id.check){
            intent.setClass(this,CheckActivity.class);
            intent.putExtra("tag",1);
            startActivity(intent);
        }else if(item.getItemId() == R.id.insert_self){
            intent.setClass(this,CheckActivity.class);
            intent.putExtra("tag",2);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE_FROM_ACTIVITY) {
                //If it is a file selection mode, you need to get the path collection of all the files selected
                //List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);//Constant.RESULT_INFO == "paths"
                List<String> list = data.getStringArrayListExtra("paths");
                //If it is a folder selection mode, you need to get the folder path of your choice
                String path = data.getStringExtra("path");
                Toast.makeText(getApplicationContext(), "The selected path is:" + path, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
