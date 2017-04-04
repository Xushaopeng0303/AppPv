package com.xsp.pv.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xsp.library.fragment.BaseFragment;
import com.xsp.pv.R;

public class CommonFragment extends BaseFragment {
    private static final String TAG = "tag";

    public CommonFragment() {

    }

    public static CommonFragment newInstance(String arg){
        CommonFragment fragment = new CommonFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAG, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_text_layout, container, false);
        TextView tv = (TextView) view.findViewById(R.id.id_common_text);
        tv.setText(getArguments().getString(TAG));
        setClassName(getArguments().getString(TAG));
        return view;
    }

}
