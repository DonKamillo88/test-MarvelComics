package com.donkamillo.test_marvelcomics.data.model;

import java.util.List;

/**
 * Created by DonKamillo on 03.07.2017.
 */

public class ComicModel {

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

    public static class Result {
        private String title;
        private String description;
        private Thumbnail thumbnail;

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public Thumbnail getThumbnail() {
            return thumbnail;
        }
    }

    public static class Thumbnail {
        private String path;
        private String extension;
        private List<Creator> creators;

        public String getPath() {
            return path;
        }

        public String getExtension() {
            return extension;
        }

        public List<Creator> getCreators() {
            return creators;
        }
    }

    public static class Creator {
        private String path;
        private String extension;
        private CreatorItem items;

        public String getPath() {
            return path;
        }

        public String getExtension() {
            return extension;
        }

        public CreatorItem getItems() {
            return items;
        }
    }

    public static class CreatorItem {
        private String role;
        private String name;

        public String getRole() {
            return role;
        }

        public String getName() {
            return name;
        }
    }


}
