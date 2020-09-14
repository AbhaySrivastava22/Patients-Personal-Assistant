package com.example.mytherapy;

import android.content.Context;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ImageAdapter extends Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mListener;
    private List<Upload> mUploads;

    public class ImageViewHolder extends ViewHolder implements OnClickListener, OnCreateContextMenuListener, OnMenuItemClickListener {
        public ImageView imageView;
        public TextView textViewName;

        public ImageViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.text_view_name);
            this.imageView = (ImageView) itemView.findViewById(R.id.image_view_upload);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void onClick(View v) {
            if (ImageAdapter.this.mListener != null) {
                int position = getAdapterPosition();
                if (position != -1) {
                    ImageAdapter.this.mListener.onItemClick(position);
                }
            }
        }

        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatever = menu.add(0, 1, 1, "Do whatever");
            MenuItem delete = menu.add(0, 2, 2, "Delete");
            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        public boolean onMenuItemClick(MenuItem item) {
            if (ImageAdapter.this.mListener != null) {
                int position = getAdapterPosition();
                if (position != -1) {
                    int itemId = item.getItemId();
                    if (itemId == 1) {
                        ImageAdapter.this.mListener.onWhatEverClick(position);
                        return true;
                    } else if (itemId == 2) {
                        ImageAdapter.this.mListener.onDeleteClick(position);
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onDeleteClick(int i);

        void onItemClick(int i);

        void onWhatEverClick(int i);
    }

    public ImageAdapter(Context context, List<Upload> uploads) {
        this.mContext = context;
        this.mUploads = uploads;
    }

    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.image_item, parent, false));
    }

    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = (Upload) this.mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        Picasso.get().load(uploadCurrent.getImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imageView);
    }

    public int getItemCount() {
        return this.mUploads.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
}
