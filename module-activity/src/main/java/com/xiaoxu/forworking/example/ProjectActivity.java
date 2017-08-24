package com.xiaoxu.forworking.example;

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
               .url("")
               .params("","")
               .success(new ISuccess() {
                   @Override
                   public void onSuccess(String response) {

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
