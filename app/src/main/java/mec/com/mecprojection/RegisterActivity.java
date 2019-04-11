package mec.com.mecprojection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mec.com.mecprojection.dao.DaoUntils;
import mec.com.mecprojection.model.User;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener {

    @BindView(R.id.et_nick_nama)
    EditText et_nick_nama;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @OnClick({
            R.id.btnCommit,
            R.id.tv_login
    })
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btnCommit:
                String phone = et_phone.getText().toString();
                String nick_name = et_nick_nama.getText().toString();
                String password = et_password.getText().toString();
                if(phone.isEmpty()){
                    Toast.makeText(this,"请输入电话号码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(nick_name.isEmpty()){
                    Toast.makeText(this,"请输入昵称",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User();
                user.setNickName(nick_name);
                user.setPassword(password);
                user.setPhone(phone);
               long id =  DaoUntils.insertUser(user);
                Log.i("ccccccccccc","id==="+id);
                break;
            case R.id.tv_login:
                intent.setClass(this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
