package com.wy.work.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.wy.work.R;

/**
 * Created by Administrator on 2018/3/7.
 */

public class LoginDialogFragment extends DialogFragment{

    private EditText etUserName;
    private EditText etPassWorld;

    /**
     * 输入完成的回调接口
     */
    public interface LoginInputCompleteListener{

        void onLoginInputComplete(String userName,String passWorld);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /**
         * 在 dialogFragment 中实现 alertDialog
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_login,null);

        etUserName = (EditText) view.findViewById(R.id.id_txt_username);
        etPassWorld = (EditText) view.findViewById(R.id.id_txt_password);

        builder.setView(view).setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                LoginInputCompleteListener loginInputCompleteListener = (LoginInputCompleteListener) getActivity();

                loginInputCompleteListener.onLoginInputComplete(etUserName.getText().toString(),etPassWorld.getText().toString());
            }
        }).setNegativeButton("Cancel",null);

        return builder.create();
    }
}
