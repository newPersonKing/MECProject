package mec.com.mecprojection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mec.com.mecprojection.dao.DaoUntils;
import mec.com.mecprojection.model.UserMessage;
import mec.com.mecprojection.until.SPUtils;

public class InputTopActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_number)
    EditText et_number;

    @BindView(R.id.et_body)
    EditText et_body;

    @BindView(R.id.et_chest)
    EditText et_chest;

    @BindView(R.id.et_waist)
    EditText et_waist;

    @BindView(R.id.et_sleeve)
    EditText et_sleeve;

    private int tag;

    @BindView(R.id.btn_check_top)
    Button btn_check_top;

    @BindView(R.id.ll_difference)
    LinearLayout ll_difference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_top);
        ButterKnife.bind(this);

        tag = getIntent().getIntExtra("tag",1);
        if(tag==2){
            btn_check_top.setText("add Message");
            ll_difference.setVisibility(View.GONE);
        }
    }

    @OnClick({
            R.id.btn_check_top
    })
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_check_top:
                String userName = (String) SPUtils.getInstance().get("userName","");
                UserMessage userMessage = DaoUntils.getUserMessageByUserName(userName);
                if(tag == 2){
                    if(userMessage ==null){
                        userMessage = new UserMessage();
                        userMessage.setUserName(userName);
                    }
                    String et_body_text = et_body.getText().toString();
                    if(et_body_text.isEmpty()){
                        return;
                    }else{
                        userMessage.setTop_body(Double.parseDouble(et_body_text));
                    }
                    String et_chest_text = et_chest.getText().toString();
                    if(et_chest_text.isEmpty()){
                        return;
                    }else{
                        userMessage.setTop_chest(Double.parseDouble(et_chest_text));
                    }
                    String et_waist_text = et_waist.getText().toString();
                    if(et_waist_text.isEmpty()){
                        return;
                    }else{
                        userMessage.setTop_waist(Double.parseDouble(et_waist_text));
                    }
                    String et_sleeve_text = et_sleeve.getText().toString();
                    if(et_sleeve_text.isEmpty()){
                        return;
                    }else{
                        userMessage.setTop_sleeve(Double.parseDouble(et_sleeve_text));
                    }
                    DaoUntils.insertOrUpdateUserMessage(userMessage);
                }else{
                    if(userMessage == null){
                        Toast.makeText(this,"please insert selfMessage",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String  et_number_text = et_number.getText().toString();
                    double chazhi;
                    if(et_number_text.isEmpty()){
                        return;
                    }else{
                        chazhi = Double.parseDouble(et_number_text);
                    }

                    String et_body_text = et_body.getText().toString();
                    if(et_body_text.isEmpty()){
                        return;
                    }else{
                        if(Math.abs(Double.parseDouble(et_body_text)-userMessage.getTop_body())>chazhi){
                            Toast.makeText(this,"not fit",Toast.LENGTH_SHORT).show();
                            Log.i("cccccccccccc","1111111111==="+(Double.parseDouble(et_body_text)-userMessage.getTop_body()));
                            return;
                        }
                    }
                    String et_chest_text = et_chest.getText().toString();
                    if(et_chest_text.isEmpty()){
                        return;
                    }else{
                        if(Math.abs(Double.parseDouble(et_chest_text)-userMessage.getTop_chest())>chazhi){
                            Toast.makeText(this,"not fit",Toast.LENGTH_SHORT).show();
                            Log.i("cccccccccccc","222222");
                            return;
                        }
                    }
                    String et_waist_text = et_waist.getText().toString();
                    if(et_waist_text.isEmpty()){
                        return;
                    }else{
                        if(Math.abs(Double.parseDouble(et_waist_text)-userMessage.getBottom_waist())>chazhi){
                            Toast.makeText(this,"not fit",Toast.LENGTH_SHORT).show();
                            Log.i("cccccccccccc","333333");
                            return;
                        }
                    }
                    String et_sleeve_text = et_sleeve.getText().toString();
                    if(et_sleeve_text.isEmpty()){
                        return;
                    }else{
                        if(Math.abs(Double.parseDouble(et_sleeve_text)-userMessage.getBottom_sleeve())>chazhi){
                            Toast.makeText(this,"not fit",Toast.LENGTH_SHORT).show();
                            Log.i("cccccccccccc","44444");
                            return;
                        }
                    }
                    Toast.makeText(this,"fit",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
