package com.xiaoxu.xiaoxu_core.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by xiaoxu on 2017/8/24.
 */

public abstract class XiaoXuDelegate extends PermissionCheckerDelegate {


    public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

    public abstract void onClick(View v);
}
