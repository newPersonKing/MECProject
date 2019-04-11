package mec.com.mecprojection;

import android.app.Application;
import android.support.multidex.MultiDex;

import mec.com.mecprojection.dao.DaoUntils;
import mec.com.mecprojection.until.SPUtils;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaoUntils.initGreenDao(this);
        MultiDex.install(this);
        SPUtils.init(this);
    }
}
