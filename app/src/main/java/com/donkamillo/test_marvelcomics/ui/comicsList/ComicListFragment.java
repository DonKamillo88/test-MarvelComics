package com.donkamillo.test_marvelcomics.ui.comicsList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.donkamillo.test_marvelcomics.R;
import com.donkamillo.test_marvelcomics.data.model.ComicModel;
import com.donkamillo.test_marvelcomics.di.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DonKamillo on 03.07.2017.
 */


public class ComicListFragment extends Fragment implements ComicsContract.View {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.list_view)
    RecyclerView recyclerView;
    @BindView(R.id.max_comics_no)
    TextView maxComicsNoTV;
    @BindView(R.id.max_pages_no)
    TextView maxPagesNoTV;
    @BindView(R.id.budget_view)
    LinearLayout budgetView;
    @BindView(R.id.budget)
    EditText budgetET;

    @Inject
    ComicsPresenter comicsPresenter;

    private OnItemSelectedListener onItemSelectedListener;
    private ComicsCardsAdapter adapter;
    private Unbinder unbinder;
    private ComicModel comicModel;

    public interface OnItemSelectedListener {
        void onItemSelected(ComicModel.Result data);
    }

    public static ComicListFragment newInstance() {
        return new ComicListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            onItemSelectedListener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implemenet ComicListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            onItemSelectedListener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + " must implemenet ComicListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        App.getComponent().inject(this);
        View view = inflater.inflate(R.layout.comics_list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new ComicsCardsAdapter(getContext(), new ComicsCardsAdapter.ComicCardsInterface() {
            @Override
            public void onItemClick(ComicModel.Result result) {
                onItemSelectedListener.onItemSelected(result);
            }
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        comicsPresenter.setView(this);

        if (savedInstanceState != null) {
            //probably orientation change
            comicModel = (ComicModel) savedInstanceState.getSerializable("list");
        }
        if (comicModel != null) {
            setProgressBarVisible(false);
            setBudgetViewVisible(true);
            updateComicsData(comicModel);
            //returning from backstack, data is fine, do nothing
        } else {
            //newly created, compute data
            comicsPresenter.getComics();
        }


        budgetET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && !s.toString().isEmpty())
                    comicsPresenter.onBudgetTextChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void updateComicsData(ComicModel comicModel) {
        this.comicModel = comicModel;
        if (comicModel != null) {
            adapter.swapItems(comicModel.getData().getResults());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setBudgetViewVisible(boolean visible) {
        budgetView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setProgressBarVisible(boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setMaxPagesNo(int maxPagesNo) {
        maxPagesNoTV.setText(maxPagesNo + "");
    }

    @Override
    public void setMaxComicsNo(int maxComicsNo) {
        maxComicsNoTV.setText(maxComicsNo + "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        comicsPresenter.unSubscribe();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("list", comicModel);
    }
}

