package com.example.android.getpermission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int CODE_SINGLE = 1;
    private final int CODE_MULTI = 2;

    private Button btn_get_single;
    private Button btn_get_multi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_get_single = (Button) findViewById(R.id.btn_get_single);
        btn_get_multi = (Button) findViewById(R.id.btn_get_multi);
        btn_get_single.setOnClickListener(this);
        btn_get_multi.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_single://以获取相机权限为例
                if (!PermissionUtils.permissionIsOpen(MainActivity.this, Manifest.permission.CALL_PHONE)) {
                    PermissionUtils.openSinglePermission(MainActivity.this, Manifest.permission.CALL_PHONE, CODE_SINGLE);
                }
                break;
            case R.id.btn_get_multi://获取相机、相册、手机联系人权限
                List<String> list = new ArrayList<>();
                list.add(Manifest.permission.CALL_PHONE);
                list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                list.add(Manifest.permission.READ_CONTACTS);
                list.add(Manifest.permission.WRITE_CONTACTS);
                PermissionUtils.openMultiPermission(MainActivity.this, list, CODE_MULTI);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODE_SINGLE://获取单个权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//权限被打开
                    Log.i("ruxing", "获取" + permissions[0] + "的权限打开了");
                } else {
                    Log.i("ruxing", "获取" + permissions[0] + "的权限被拒了");
                }
                break;
            case CODE_MULTI://获取多个权限
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        if (grantResult == PackageManager.PERMISSION_DENIED) {//权限被拒绝
                            Log.i("ruxing", "获取" + permissions[i] + "的权限被拒了");
                        } else {
                            Log.i("ruxing", "获取" + permissions[i] + "的权限打开了");
                        }
                    }
                }
                break;
        }
    }
}
