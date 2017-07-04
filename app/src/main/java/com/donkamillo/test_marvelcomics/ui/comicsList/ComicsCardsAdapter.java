package com.donkamillo.test_marvelcomics.ui.comicsList;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkamillo.test_marvelcomics.R;
import com.donkamillo.test_marvelcomics.data.model.ComicModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DonKamillo on 03.07.2017.
 */

public class ComicsCardsAdapter extends RecyclerView.Adapter<ComicsCardsAdapter.ComicViewHolder> {

    public interface ComicCardsInterface {
        void onItemClick(ComicModel.Result data);
    }

    private ComicCardsInterface comicCardsInterface;
    private List<ComicModel.Result> comics;
    private Context context;

    public ComicsCardsAdapter(Context context, ComicCardsInterface comicCardsInterface) {
        this.comicCardsInterface = comicCardsInterface;
        this.context = context;
    }

    public void swapItems(List<ComicModel.Result> comics) {
        this.comics = comics;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (comics == null) return 0;
        return comics.size();
    }

    @Override
    public void onBindViewHolder(ComicViewHolder holder, int i) {
        ComicModel.Result result = comics.get(i);
        if (result == null)
            return;
        holder.title.setText(result.getTitle());
        holder.setMainLayoutBackground(i);
        holder.setOnClickListener(result);
        if (result.getThumbnail() != null)
            holder.showThumbnail(result.getThumbnail().getPath(), result.getThumbnail().getExtension());

    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.comics_list_item, viewGroup, false);

        return new ComicViewHolder(itemView);
    }


    public class ComicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.main_layout)
        LinearLayout mainLayout;

        public ComicViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        private void setMainLayoutBackground(int position) {
            if (position % 2 == 1) {
                mainLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.light_gray_bg));
            } else {
                mainLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            }
        }

        private void setOnClickListener(final ComicModel.Result result) {
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comicCardsInterface.onItemClick(result);
                }
            });
        }

        private void showThumbnail(String path, String extension) {
            String imageUrl = path + "." + extension;
            Glide.with(context).load(imageUrl).into(thumbnail);
        }
    }

}
