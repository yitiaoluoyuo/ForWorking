package com.xiaoxu.xiaoxu_ec.main_delegates.personal.address;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;

import com.xiaoxu.xiaoxu_ec.R;

/**
 * Created by xiaoxu on 2017/9/6.
 */

public class ResetAddressDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private RestDialogListener listener;

    public interface RestDialogListener{
        public void onClick(View view);
    }

    public ResetAddressDialog(@NonNull Context context) {
        super(context);
    }

    public ResetAddressDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_reset_address);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }
}
