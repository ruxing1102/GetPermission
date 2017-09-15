package com.example.android.getpermission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态获取权限
 * Created by ruxing on 2017/9/13.
 */

public class PermissionUtils {

    /**
     * 判断权限是否开启
     *
     * @return
     */
    public static boolean permissionIsOpen(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {//表示未授权
            return false;
        }
        return true;
    }

    /**
     * 请求单个授权
     *
     * @param activity
     * @param permission
     */
    public static void openSinglePermission(Activity activity, String permission, int requestCode) {
        if (!permissionIsOpen(activity, permission)) {//先判断该权限是否开启
            //动态申请权限
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, requestCode);
        }
    }

    public static void openMultiPermission(Activity activity, List<String> permissions, int requestCode) {
        List<String> permission_no_open;
        if (permissions != null && permissions.size() > 0) {
            permission_no_open = new ArrayList<>();
            for (int i = 0; i < permissions.size(); i++) {
                if (!permissionIsOpen(activity, permissions.get(i))) {
                    permission_no_open.add(permissions.get(i));
                }
            }
            if (permission_no_open != null && permission_no_open.size() > 0) {
                ActivityCompat.requestPermissions(activity, permission_no_open.toArray(new String[permission_no_open.size()]), requestCode);
            }
        }
    }
}
