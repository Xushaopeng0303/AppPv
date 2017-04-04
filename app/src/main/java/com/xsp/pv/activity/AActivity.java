package com.xsp.pv.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.xsp.library.activity.BaseActivity;
import com.xsp.pv.R;

public class AActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_text_layout);

        TextView tv = (TextView) findViewById(R.id.id_common_text);

        tv.setText(this.getClass().getSimpleName());
    }

}
