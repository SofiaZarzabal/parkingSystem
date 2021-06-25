package com.example.parkingsystem.mvp.view.base;

import android.content.Context;
import android.icu.util.Freezable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import java.lang.ref.WeakReference;

public class FragmentView {
    private WeakReference<Fragment> fragmentRef;

    public FragmentView(Fragment fragment){
        fragmentRef= new WeakReference<>(fragment);
    }

    public Fragment getFragment(){
        return fragmentRef.get();
    }

    public Context getContext(){
        return getFragment().getContext();
    }
}
