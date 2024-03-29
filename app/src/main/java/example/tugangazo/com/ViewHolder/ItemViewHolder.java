package example.tugangazo.com.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import example.tugangazo.com.Interface.ItemClickListener;
import example.tugangazo.com.R;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtProductName, txtProductDescription, txtProductPrice, txtProductStatus;
    public ImageView imageView;
    public ItemClickListener listener;

    public ItemViewHolder(View itemView) {
        super(itemView);


        imageView = (ImageView) itemView.findViewById(R.id.product_seller_image);
        txtProductName = (TextView) itemView.findViewById(R.id.product_seller_name);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_seller_description);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_seller_price);
        txtProductStatus = (TextView) itemView.findViewById(R.id.product_seller_state);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(), false);
    }
}
