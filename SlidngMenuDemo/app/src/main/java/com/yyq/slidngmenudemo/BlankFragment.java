package com.yyq.slidngmenudemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    private String msg;
    private TextView textView;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        msg = getArguments().getString("msg");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        textView = ((TextView)view.findViewById(R.id.showTv));
//        textView.setText(msg);
        return view;
    }

    public BlankFragment setMsg(String msg){
        BlankFragment fragment = new BlankFragment();
        this.msg = msg;
        return fragment;
    }

}
