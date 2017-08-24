package com.xiaoxu.forworking.example;

import com.xiaoxu.xiaoxu_core.activities.ProxyActivity;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;

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
}
