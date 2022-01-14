package com.example.photo_gallery.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photo_gallery.R;
import com.example.photo_gallery.data.models.photo.PhotoData;

import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {

    private List<PhotoData> photoList;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    public PhotoListAdapter(Context context, List<PhotoData> photoList) {
        this.inflater = LayoutInflater.from(context);
        this.photoList = photoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.photos_recycler_view_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PhotoData photoDataObject = photoList.get(position);
        String authorName = photoDataObject.getAuthor();
        holder.authorName.setText(authorName);

        Glide.with(holder.photoImageView.getContext())
                .load(photoDataObject.getDownload_url())
                .into(holder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView authorName;
        ImageView photoImageView;

        ViewHolder(View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.author_name);
            photoImageView = itemView.findViewById(R.id.photo_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public PhotoData getItem(int id) {
        return photoList.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
