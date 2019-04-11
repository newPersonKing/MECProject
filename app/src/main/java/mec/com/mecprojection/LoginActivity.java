package mec.com.mecprojection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mec.com.mecprojection.dao.DaoUntils;
import mec.com.mecprojection.model.CheckData;
import mec.com.mecprojection.model.User;
import mec.com.mecprojection.until.ExcelUtil;
import mec.com.mecprojection.until.SPUtils;
import mec.com.mecprojection.until.ThreadPool;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_login_main_username)
    EditText et_login_main_username;
    @BindView(R.id.et_login_password)
    EditText et_login_password;

    @BindView(R.id.tv_login_register)
    TextView tv_login_register;
    @BindView(R.id.btn_login_in)
    Button btn_login_in;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        insertData();

    }

    private void insertData(){
        ThreadPool.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if(DaoUntils.hasCheckData()){
                        return;
                    }
                    ArrayList<ArrayList<Object>> data=ExcelUtil.readExcel2007(getAssets().open("pt.xlsx"));
                    List<CheckData> checkDatas = new ArrayList<>();
                    for(int i=1;i<data.size();i++){
                          ArrayList<Object> list = data.get(i);
                          CheckData checkData = new CheckData();
                          checkData.setAngeGrop(Integer.parseInt((String) list.get(0)));
                          checkData.setMaleWeak(Double.parseDouble((String) list.get(1)));
                          checkData.setMaleStrong(Double.parseDouble((String) list.get(2)));
                          checkData.setFemaleWeak(Double.parseDouble((String) list.get(3)));
                          checkData.setFemaleStrong(Double.parseDouble((String) list.get(4)));
                          checkData.setType(Integer.parseInt((String) list.get(5)));
                          checkDatas.add(checkData);
                    }
                    DaoUntils.insetCheckDatas(checkDatas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick({
            R.id.tv_login_register,
            R.id.btn_login_in
    })
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_login_in:
                String username= et_login_main_username.getText().toString();
                String password = et_login_password.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.isEmpty()){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = DaoUntils.getUser(username,password);
                if(user==null){
                    Toast.makeText(this,"用户名 密码 错误",Toast.LENGTH_SHORT).show();
                    return;
                }
                SPUtils.getInstance().set("userName",username);
                intent.setClass(this,MainActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_login_register:
                intent.setClass(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
