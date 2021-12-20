package com.example.photo_gallery.data.models.photo;

public class PhotoImpl implements Photo {
    private final String id;
    private final String author;
    private final int width;
    private final int height;
    private final String url;
    private final String download_url;

    public PhotoImpl (String id, String author, int width, int height, String url, String download_url)
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
