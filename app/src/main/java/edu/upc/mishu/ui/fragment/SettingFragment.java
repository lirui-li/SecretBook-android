package edu.upc.mishu.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xutil.tip.ToastUtils;

import java.util.Objects;

import edu.upc.mishu.R;

public class SettingFragment extends Fragment {
    private static SettingFragment instance  = null;

    public static SettingFragment newInstance(){
        if(instance == null){
            instance = new SettingFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lefthead,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Button button1 = Objects.requireNonNull(getActivity()).findViewById(R.id.button_setting);
//        button1.setOnClickListener(v -> Toast.makeText(getActivity(),"setting",Toast.LENGTH_SHORT).show());
//
//        Button button2 = getActivity().findViewById(R.id.btn_chkupdate);
//        button2.setOnClickListener(v -> {
//            ToastUtils.toast(getString(R.string.update_checking));
//            XUpdate.newBuild(getActivity())
//                    .updateUrl(getString(R.string.update_url))
//                    .update();
//        });


    }
}
