package com.donkamillo.test_marvelcomics.ui.comicsDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donkamillo.test_marvelcomics.R;
import com.donkamillo.test_marvelcomics.data.model.ComicModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DonKamillo on 04.07.2017.
 */

public class ComicDetailsFragment extends Fragment {
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
            ComicModel.Result comicDetail = (ComicModel.Result) bundle.getSerializable(COMIC_DETAILS_ARG);
            updateData(comicDetail);
        }
    }

    public void updateData(ComicModel.Result comicDetail) {
        if (comicDetail == null) return;

        titleTV.setText(comicDetail.getTitle());
        descriptionTV.setText(comicDetail.getDescription() != null ? Html.fromHtml(comicDetail.getDescription()) : "---");
        pageCountTV.setText(comicDetail.getPageCount() + "");
        priceTV.setText(getPrice(comicDetail.getPrices()));
        creatorsTV.setText(Html.fromHtml(getCreatorstList(comicDetail.getCreators())));
        mainLL.setVisibility(View.VISIBLE);
    }

    private String getCreatorstList(ComicModel.Creator creator) {
        if (creator == null || creator.getItems() == null || creator.getItems().isEmpty()) {
            return "---";
        }
        StringBuilder bullets = new StringBuilder();
        for (ComicModel.CreatorItem creatorItem : creator.getItems()) {
            bullets.append("<br/>\n&#8226; ").append(creatorItem.getName()).append(" - ").append(creatorItem.getRole()).append("\n");
        }
        return bullets.toString();
    }

    private String getPrice(List<ComicModel.Price> prices) {
        if (prices != null && !prices.isEmpty() && prices.get(0) != null)
            return prices.get(0).getPrice() + "";
        else
            return "---";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
