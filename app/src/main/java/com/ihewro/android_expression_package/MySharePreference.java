package com.ihewro.android_expression_package;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ihewro.android_expression_package.activity.MyActivity;
import com.ihewro.android_expression_package.bean.UserPreference;
import com.ihewro.android_expression_package.util.APKVersionCodeUtils;
import com.ihewro.android_expression_package.util.UIUtil;

import org.litepal.LitePal;

import java.util.List;

/**
 * <pre>
 *     author : hewro
 *     e-mail : ihewro@163.com
 *     time   : 2018/07/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MySharePreference {

    public static boolean setIsFistEnter(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("baby", false);
        editor.apply();
        return true;
    }

    public static boolean getIsFirstEnter(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("baby", true);
    }

    /**
     * 返回用户使用某种新功能的情况，1表示已经用过了，0表示没用过
     * @param key
     * @return
     */
    public static int getUserUsedStatus(String key){
        List<UserPreference> userPreferenceList = LitePal.findAll(UserPreference.class);
        UserPreference currentUserPreference = null;
        UserPreference newUserPreference = null;

        int status;
        boolean flag = false;


        if (userPreferenceList.size() == 1){
            currentUserPreference = userPreferenceList.get(0);
            flag = true;
        }else {
            flag = false;
            newUserPreference = new UserPreference();
        }


        switch (key){
            case "isAddNew" :
                if (flag){
                    status =  currentUserPreference.getIsAddNew();
                    currentUserPreference.setIsAddNew(1);
                }else {
                    status = 0;
                    newUserPreference.setIsAddNew(1);
                    newUserPreference.save();
                }
                break;

            default:
                status =  0;
                break;
        }




        return status;
    }

    /**
     * 设置用户使用某种新功能的情况
     */
    private static void setUserUsedStatus(){

    }
}
