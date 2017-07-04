package com.donkamillo.test_marvelcomics.ui.comicsDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donkamillo.test_marvelcomics.R;
import com.donkamillo.test_marvelcomics.data.model.ComicModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DonKamillo on 04.07.2017.
 */

public class ComicDetailsFragment  extends Fragment {
    public static final String COMIC_DETAILS_ARG = "comic_detail";

    @BindView(R.id.main_layout)
    LinearLayout mainLL;
    @BindView(R.id.title)
    TextView titleTV;
    @BindView(R.id.page_count)
    TextView pageCountTV;
    @BindView(R.id.price)
    TextView priceTV;
    @BindView(R.id.creators)
    TextView creatorsTV;
    @BindView(R.id.description)
    TextView descriptionTV;

    private Unbinder unbinder;

    public static ComicDetailsFragment newInstance(ComicModel.Result comicDetail) {
        Bundle b = new Bundle();
        b.putSerializable(COMIC_DETAILS_ARG, comicDetail);

        final ComicDetailsFragment fragment = new ComicDetailsFragment();
        fragment.setArguments(b);
        return fragment;
    }

    public static ComicDetailsFragment newInstance() {
        return new ComicDetailsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comics_detail_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            ComicModel.Result comicDetail = (ComicModel.Result  ) bundle.getSerializable(COMIC_DETAILS_ARG);
            updateData(comicDetail);
        }
    }

    public void updateData(ComicModel.Result comicDetail) {
        titleTV.setText(comicDetail.getTitle());
        descriptionTV.setText(comicDetail.getDescription());
        pageCountTV.setText(comicDetail.getPageCount()+"");
        priceTV.setText(comicDetail.getPrices().get(0).getPrice()+"");
        creatorsTV.setText(comicDetail.getCreators().getItems().get(0).getName()+"");
        mainLL.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
