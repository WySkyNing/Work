package com.wy.work;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wy.work.dialog.LoginDialogFragment;
import com.wy.work.dialog.SetNameDialogFragment;
import com.wy.work.encryption.EncryptionUtil;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements LoginDialogFragment.LoginInputCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("wy__base64_密文: ", EncryptionUtil.base64ToCode("待加密文字"));

        Log.e("wy__base64_明文: ", EncryptionUtil.base64ToDecode("5b6F5Yqg5a+G5paH5a2X"));

        Log.e("wy__base64_密文: ", EncryptionUtil.md5ToCode("待加密文字"));
    }

    public void setNameDialogFragment(View view) {

        SetNameDialogFragment setNameDialogFragment = new SetNameDialogFragment();
        setNameDialogFragment.show(getFragmentManager(), "setNameDialogFragment");
    }

    public void loginDialogFragment(View view) {

        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();

        loginDialogFragment.show(getFragmentManager(), "loginDialogFragment");

    }

    @Override
    public void onLoginInputComplete(String userName, String passWorld) {

        Toast.makeText(this, userName + "  " + passWorld, Toast.LENGTH_SHORT).show();
    }
}
