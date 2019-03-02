package com.a1magway.bgg.common.shre;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enid on 2018/6/12.
 */

public class ShareData implements Serializable{
    private String type;
    private String url;
    private String title = "";
    private String description = "";
    private List<String> mediaPath = new ArrayList<>();
    private String shardQRcodeImageUrl;
    private String shardAddHeadImageUrl;

    public ShareData(Builder builder){
        this.type = builder.getType();
        this.url = builder.getUrl();
        this.title = builder.getTitle();
        this.description = builder.getDescription();
        this.mediaPath = builder.getMediaPath();
        this.shardQRcodeImageUrl = builder.getShardQRcodeImageUrl();
        this.shardAddHeadImageUrl = builder.getShardAddHeadImageUrl();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private String url;
        private String title;
        private String description;
        private List<String> mediaPath = new ArrayList<>();
        private String shardQRcodeImageUrl;
        private String shardAddHeadImageUrl;

        public String getShardAddHeadImageUrl() {
            return shardAddHeadImageUrl;
        }

        public Builder setShardAddHeadImageUrl(String shardAddHeadImageUrl) {
            this.shardAddHeadImageUrl = shardAddHeadImageUrl;
            return this;
        }

        public String getShardQRcodeImageUrl() {
            return shardQRcodeImageUrl;
        }

        public Builder setShardQRcodeImageUrl(String shardQRcodeImageUrl) {
            this.shardQRcodeImageUrl = shardQRcodeImageUrl;
            return this;
        }

        public String getType() {
            return type;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public List<String> getMediaPath() {
            return mediaPath;
        }

        public Builder setMediaPath(List<String> mediaPath) {
            this.mediaPath = mediaPath;
            return this;
        }

        public ShareData build(){
            return new ShareData(this);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(List<String> mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getShardQRcodeImageUrl() {
        return shardQRcodeImageUrl;
    }

    public void setShardQRcodeImageUrl(String shardQRcodeImageUrl) {
        this.shardQRcodeImageUrl = shardQRcodeImageUrl;
    }

    public String getShardAddHeadImageUrl() {
        return shardAddHeadImageUrl;
    }

    public void setShardAddHeadImageUrl(String shardAddHeadImageUrl) {
        this.shardAddHeadImageUrl = shardAddHeadImageUrl;
    }
}
