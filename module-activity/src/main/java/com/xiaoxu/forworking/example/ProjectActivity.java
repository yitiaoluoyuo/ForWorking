package com.xiaoxu.forworking.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.xiaoxu.xiaoxu_core.activities.ProxyActivity;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;

public class ProjectActivity extends ProxyActivity {

    @Override
    public XiaoXuDelegate setRootDelegate() {
        return new ProjectDelegate();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testRestClient();
    }

    /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s = "import Context is ready";

        Toast.makeText(XiaoXu.getApplication(),s,Toast.LENGTH_LONG).show();
    }*/



   void testRestClient(){
       RestClient
               .builder()
               .url("/")
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

           }
       })
               .failure(new IFailure() {
                   @Override
                   public void onFailure() {

                   }
               })
               .build()
               .get();





   }







}
