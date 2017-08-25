package com.xiaoxu.forworking.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.xiaoxu.xiaoxu_core.activities.ProxyActivity;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_ec.sign.SignUpDelegate;

public class ProjectActivity extends ProxyActivity {

    @Override
    public XiaoXuDelegate setRootDelegate() {

        return new SignUpDelegate();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s = "import Context is ready";

        Toast.makeText(XiaoXu.getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }*/



   void testRestClient(){
       RestClient
               .builder()
               .url("/index")
               //.params("","")
               .loader(this)
               .success(new ISuccess() {
                   @Override
                   public void onSuccess(String response) {
                       Toast.makeText(getBaseContext(),response,Toast.LENGTH_LONG).show();
                   }
               })
               .error(new IError() {
           @Override
           public void onError(int code, String msg) {
               Toast.makeText(getBaseContext(),"error",Toast.LENGTH_LONG).show();
           }
       })
               .failure(new IFailure() {
                   @Override
                   public void onFailure() {
                       Toast.makeText(getBaseContext(),"failure",Toast.LENGTH_LONG).show();
                   }
               })
               .build()
               .get();





   }







}
