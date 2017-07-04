package com.donkamillo.test_marvelcomics;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.donkamillo.test_marvelcomics.data.model.ComicModel;
import com.donkamillo.test_marvelcomics.di.App;
import com.donkamillo.test_marvelcomics.ui.comicsDetails.ComicDetailsFragment;
import com.donkamillo.test_marvelcomics.ui.comicsList.ComicListFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements ComicListFragment.OnItemSelectedListener {

    private FragmentManager fragmentManager;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().inject(this);
        unbinder = ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ComicListFragment comicListFragment = ComicListFragment.newInstance();
        ft.add(R.id.comics_fragment_placeholder, comicListFragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        int count = fragmentManager.getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            fragmentManager.popBackStack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onItemSelected(ComicModel.Result data) {
        ComicDetailsFragment detailFragment = ComicDetailsFragment.newInstance(data);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.comics_fragment_placeholder, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
