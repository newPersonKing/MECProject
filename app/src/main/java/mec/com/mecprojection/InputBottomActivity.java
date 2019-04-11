package mec.com.mecprojection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

public class InputBottomActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_number)
    EditText et_number;

    @BindView(R.id.et_out_seam)
    EditText et_out_seam;

    @BindView(R.id.et_waist_bottom)
    EditText et_waist_bottom;

    @BindView(R.id.et_hip)
    EditText et_hip;

    @BindView(R.id.et_sleeve)
    EditText et_sleeve;

    @BindView(R.id.btn_check_bottom)
    Button btn_check_bottom;

    @BindView(R.id.ll_difference)
    LinearLayout ll_difference;

    private int tag;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bottom);
        ButterKnife.bind(this);

        tag = getIntent().getIntExtra("tag",1);

        if(tag==2){
            btn_check_bottom.setText("add Message");
            ll_difference.setVisibility(View.GONE);
        }
    }

    @OnClick({
        R.id.btn_check_bottom
    })
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_check_bottom:
                String userName = (String) SPUtils.getInstance().get("userName","");
                UserMessage userMessage = DaoUntils.getUserMessageByUserName(userName);
                if(tag == 2){
                    if(userMessage == null){
                        userMessage = new UserMessage();
                        userMessage.setUserName(userName);
                    }
                    String  et_out_seam_text = et_out_seam.getText().toString();
                    if(et_out_seam_text.isEmpty()){
                        return;
                    }else{
                        userMessage.setBottom_out_seam(Double.parseDouble(et_out_seam_text));
                    }
                    String  et_waist_bottom_text = et_waist_bottom.getText().toString();
                    if(et_waist_bottom_text.isEmpty()){
                        return;
                    }else{
                        userMessage.setBottom_waist(Double.parseDouble(et_waist_bottom_text));
                    }
                    String  et_hip_text = et_hip.getText().toString();
                    if(et_hip_text.isEmpty()){
                        return;
                    }else{
                        userMessage.setBottom_hip(Double.parseDouble(et_hip_text));
                    }
                    String  et_sleeve_text = et_sleeve.getText().toString();
                    if(et_sleeve_text.isEmpty()){
                        return;
                    }else{
                        userMessage.setBottom_sleeve(Double.parseDouble(et_sleeve_text));
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
                    String  et_out_seam_text = et_out_seam.getText().toString();
                    if(et_out_seam_text.isEmpty()){
                        return;
                    }else{
                        if(Math.abs(Double.parseDouble(et_out_seam_text)-userMessage.getBottom_out_seam())>chazhi){
                            Toast.makeText(this,"not fit",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    String  et_waist_bottom_text = et_waist_bottom.getText().toString();
                    if(et_waist_bottom_text.isEmpty()){
                        return;
                    }else{
                        if(Math.abs(Double.parseDouble(et_waist_bottom_text)-userMessage.getBottom_waist())>chazhi){
                            Toast.makeText(this,"not fit",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    String  et_hip_text = et_hip.getText().toString();
                    if(et_hip_text.isEmpty()){
                        return;
                    }else{
                        if(Math.abs(Double.parseDouble(et_hip_text)-userMessage.getBottom_hip())>chazhi){
                            Toast.makeText(this,"not fit",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    String  et_sleeve_text = et_sleeve.getText().toString();
                    if(et_sleeve_text.isEmpty()){
                        return;
                    }else{
                        if(Math.abs(Double.parseDouble(et_sleeve_text)-userMessage.getBottom_sleeve())>chazhi){
                            Toast.makeText(this,"not fit",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    Toast.makeText(this,"fit",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
