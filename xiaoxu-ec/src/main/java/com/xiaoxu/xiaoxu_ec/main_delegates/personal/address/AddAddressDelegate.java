package com.xiaoxu.xiaoxu_ec.main_delegates.personal.address;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lljjcoder.city_20170724.CityPickerView;
import com.lljjcoder.city_20170724.bean.CityBean;
import com.lljjcoder.city_20170724.bean.DistrictBean;
import com.lljjcoder.city_20170724.bean.ProvinceBean;
import com.xiaoxu.xiaoxu_core.delegates.LatteDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.util.storage.XiaoXuPreference;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoxu on 2017/9/7.
 */

public class AddAddressDelegate extends LatteDelegate implements CityPickerView.OnCityItemClickListener {

    CityPickerView cityPicker = null;

    @BindView(R2.id.tv_add_address_city)
    TextView textViewCity = null;

    @BindView(R2.id.et_add_address_name)
    EditText editTextName = null;

    @BindView(R2.id.et_add_address_phone)
    EditText editTextPhone = null;

    @BindView(R2.id.et_add_address_detail)
    EditText editTextDetail = null;
    private String province;
    private String city;
    private String district;

    @OnClick(R2.id.tv_add_address_commit)
    void commit(){

      long id =Long.valueOf(XiaoXuPreference.getCustomAppProfile("id"));

        RestClient.builder()
                .url("/shipping/add.do")
                .params("userId",id)
                .params("receiverName",editTextName.getText().toString())
                .params("receiverPhone",editTextPhone.getText().toString())
                .params("receiverProvince",province)
                .params("receiverCity",city+"-"+district)
                .params("receiverAddress",editTextDetail.getText().toString())
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        int code = JSON.parseObject(response).getInteger("status");
                        if (code == 0){
                            Toast.makeText(getContext(),"添加地址成功",Toast.LENGTH_SHORT).show();
                            getSupportDelegate().replaceFragment(new AddressDelegate(),false);


                        }else {
                            Toast.makeText(getContext(),"添加地址失败",Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(),"onFailure",Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();

    }



    @OnClick(R2.id.tv_add_address_city)
    void addCity(View view) {

        //判断输入法的隐藏状态
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        cityPicker.show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_add_adress;
    }


    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        cityPicker = new CityPickerView.Builder(getContext())
                .textSize(20)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#aaaaaa")
                .titleTextColor("#cccccc")
                .confirTextColor("#cccccc")
                .cancelTextColor("#cccccc")
                .province("上海")
                .city("上海")
                .district("浦东区")
                .textColor(Color.parseColor("#888888"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.setOnCityItemClickListener(this);
    }

    @Override
    public void onSelected(ProvinceBean provinceBean, CityBean cityBean, DistrictBean districtBean) {

        province = provinceBean.getName();
        city = cityBean.getName();
        district = districtBean.getName();

        textViewCity.setText(province +"-"+ city +"-" + district);
    }

    @Override
    public void onCancel() {

    }


}
