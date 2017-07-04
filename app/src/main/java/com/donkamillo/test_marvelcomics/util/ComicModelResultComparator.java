package com.donkamillo.test_marvelcomics.util;

import com.donkamillo.test_marvelcomics.data.model.ComicModel;

import java.util.Comparator;

import static com.donkamillo.test_marvelcomics.util.ComicModelResultComparator.Order.PAGE;

/**
 * Created by DonKamillo on 04.07.2017.
 */

public class ComicModelResultComparator implements Comparator<ComicModel.Result> {
    public enum Order {PRICE, PAGE}

    private Order sortingBy = PAGE;

    @Override
    public int compare(ComicModel.Result comic1, ComicModel.Result comic2) {
        switch (sortingBy) {
            case PRICE:
                return comparePrices(comic1, comic2);
            case PAGE:
                return comic2.getPageCount() - comic1.getPageCount();
        }
        throw new RuntimeException("Practically unreachable code, can't be thrown");
    }

    public void setSortingBy(Order sortBy) {
        this.sortingBy = sortBy;
    }

    private int comparePrices(ComicModel.Result comic1, ComicModel.Result comic2) {

        double price1 = 0;
        if (comic1.getPrices() != null && !comic1.getPrices().isEmpty() && comic1.getPrices().get(0) != null) {
            price1 = comic1.getPrices().get(0).getPrice();
        }
        double price2 = 0;
        if (comic2.getPrices() != null && !comic2.getPrices().isEmpty() && comic2.getPrices().get(0) != null) {
            price2 = comic2.getPrices().get(0).getPrice();
        }
        return (int) (price1 - price2);

    }
}
