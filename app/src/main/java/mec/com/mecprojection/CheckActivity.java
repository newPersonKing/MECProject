package mec.com.mecprojection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ll_first)
    LinearLayout ll_first;

    @BindView(R.id.ll_second)
    LinearLayout ll_second;

    @BindView(R.id.btn_take_photo)
    Button btn_take_photo;

    @BindView(R.id.btn_selectImg)
    Button btn_selectImg;

    @BindView(R.id.btn_input_size)
    Button btn_input_size;

    int tag;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        ButterKnife.bind(this);

        tag = getIntent().getIntExtra("tag",1);

        if(tag == 2){
            btn_take_photo.setVisibility(View.GONE);
            btn_selectImg.setVisibility(View.GONE);
        }else{
            btn_input_size.setText("Input clothing size");
        }


    }

    @OnClick({
            R.id.ll_second,
            R.id.ll_first,
            R.id.btn_input_size,
            R.id.btn_top,
            R.id.btn_bottom,
            R.id.btn_take_photo,
            R.id.btn_selectImg
    })
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_input_size:
                ll_second.setVisibility(View.VISIBLE);
                ll_first.setVisibility(View.GONE);
                break;
            case R.id.btn_top:
                intent.setClass(this,InputTopActivity.class);
                intent.putExtra("tag",tag);
                startActivity(intent);
                ll_second.setVisibility(View.GONE);
                ll_first.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_bottom:
                intent.setClass(this,InputBottomActivity.class);
                intent.putExtra("tag",tag);
                startActivity(intent);
                ll_second.setVisibility(View.GONE);
                ll_first.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_selectImg:
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.btn_take_photo:
                PictureSelector.create(this)
                        .openCamera(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
