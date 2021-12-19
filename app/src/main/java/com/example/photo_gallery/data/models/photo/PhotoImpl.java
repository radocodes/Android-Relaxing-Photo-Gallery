package com.example.photo_gallery.data.models.photo;

public class PhotoImpl implements Photo {
    private final String id;
    private final String author;
    private final int width;
    private final int weight;
    private final String url;
    private final String download_url;

    public PhotoImpl (String id, String author, int width, int weight, String url, String download_url)
    {
        this.id = id;
        this.author = author;
        this.width = width;
        this.weight = weight;
        this.url = url;
        this.download_url = download_url;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public int getWidth() {
        return width;
    }

    public int getWeight() {
        return weight;
    }

    public String getUrl() {
        return url;
    }

    public String getDownload_url() {
        return download_url;
    }
}
