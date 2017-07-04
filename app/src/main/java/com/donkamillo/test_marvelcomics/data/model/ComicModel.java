package com.donkamillo.test_marvelcomics.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DonKamillo on 03.07.2017.
 */

public class ComicModel implements Serializable {

    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data {
        private List<Result> results;

        public List<Result> getResults() {
            return results;
        }
    }

    public static class Result implements Serializable {
        private String title;
        private String description;
        private Thumbnail thumbnail;
        private Creator creators;
        private List<Price> prices;
        private int pageCount;

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public Thumbnail getThumbnail() {
            return thumbnail;
        }

        public Creator getCreators() {
            return creators;
        }

        public int getPageCount() {
            return pageCount;
        }

        public List<Price> getPrices() {
            return prices;
        }
    }

    public static class Thumbnail implements Serializable {
        private String path;
        private String extension;

        public String getPath() {
            return path;
        }

        public String getExtension() {
            return extension;
        }

    }

    public static class Creator implements Serializable {
        private List<CreatorItem> items;

        public List<CreatorItem> getItems() {
            return items;
        }
    }

    public static class CreatorItem implements Serializable {
        private String role;
        private String name;

        public String getRole() {
            return role;
        }

        public String getName() {
            return name;
        }
    }

    public static class Price implements Serializable {
        private String type;
        private float price;

        public String getType() {
            return type;
        }

        public float getPrice() {
            return price;
        }
    }


}
