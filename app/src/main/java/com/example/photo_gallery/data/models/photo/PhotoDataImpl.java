package com.example.photo_gallery.data.models.photo;

public class PhotoDataImpl implements PhotoData {
    private String id;
    private final String author;
    private final int width;
    private final int height;
    private final String url;
    private final String download_url;

    public PhotoDataImpl(String id, String author, int width, int height, String url, String download_url)
    {
        this.id = id;
        this.author = author;
        this.width = width;
        this.height = height;
        this.url = url;
        this.download_url = download_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getAuthor() {
        return author;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public String getDownload_url() {
        return download_url;
    }
}
