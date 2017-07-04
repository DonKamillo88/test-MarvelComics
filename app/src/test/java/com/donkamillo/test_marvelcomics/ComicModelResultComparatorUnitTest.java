package com.donkamillo.test_marvelcomics;

import com.donkamillo.test_marvelcomics.data.model.ComicModel;
import com.donkamillo.test_marvelcomics.util.ComicModelResultComparator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ComicModelResultComparatorUnitTest {
    @Test
    public void comparePrices_correctEqualInput() throws Exception {
        ComicModel.Result r1 = createBasicComicModelResult(0);
        ComicModel.Result r2 = createBasicComicModelResult(0);

        ComicModelResultComparator comparator = new ComicModelResultComparator();
        comparator.setSortingBy(ComicModelResultComparator.Order.PRICE);

        int result = comparator.compare(r1, r2);
        assertEquals(0, result);

        r1 = createBasicComicModelResult(5.123F);
        r2 = createBasicComicModelResult(5.123F);
        result = comparator.compare(r1, r2);
        assertEquals(0, result);

        r1 = createBasicComicModelResult(-0.1F);
        r2 = createBasicComicModelResult(-0.1F);
        result = comparator.compare(r1, r2);
        assertEquals(0, result);

        r1 = createBasicComicModelResult(999999999F);
        r2 = createBasicComicModelResult(999999999F);
        result = comparator.compare(r1, r2);
        assertEquals(0, result);
    }

    @Test
    public void comparePrices_correctNotEqualInput() throws Exception {
        ComicModel.Result r1 = createBasicComicModelResult(0);
        ComicModel.Result r2 = createBasicComicModelResult(1);

        ComicModelResultComparator comparator = new ComicModelResultComparator();
        comparator.setSortingBy(ComicModelResultComparator.Order.PRICE);

        int result = comparator.compare(r1, r2);
        assertEquals(-1, result);

        r1 = createBasicComicModelResult(5.124F);
        r2 = createBasicComicModelResult(5.123F);
        result = comparator.compare(r1, r2);
        assertEquals(1, result);

        r1 = createBasicComicModelResult(-0.12F);
        r2 = createBasicComicModelResult(-0.1F);
        result = comparator.compare(r1, r2);
        assertEquals(-1, result);
    }

    @Test
    public void comparePrices_incorrectInput() throws Exception {
        ComicModel.Result r1 = createBasicComicModelResult(1);
        ComicModel.Result r2 = createBasicComicModelResult(1);

        ComicModelResultComparator comparator = new ComicModelResultComparator();
        comparator.setSortingBy(ComicModelResultComparator.Order.PRICE);

        r1.getPrices().set(0, null);
        int result = comparator.compare(r1, r2);
        assertEquals(-1, result);

        r1.setPrices(null);
        result = comparator.compare(r1, r2);
        assertEquals(-1, result);


        r1.setPrices(null);
        r2.setPrices(new ArrayList<ComicModel.Price>());
        result = comparator.compare(r1, r2);
        assertEquals(0, result);

        r1.setPrices(null);
        r2.setPrices(null);
        result = comparator.compare(r1, r2);
        assertEquals(0, result);
    }

    private ComicModel.Result createBasicComicModelResult(float p) {
        ComicModel.Result r = new ComicModel.Result();
        List<ComicModel.Price> priceList = new ArrayList<>();
        ComicModel.Price price = new ComicModel.Price();
        price.setPrice(p);
        priceList.add(price);
        r.setPrices(priceList);
        return r;
    }
}