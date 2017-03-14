package fr.opendev.fraispro.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.opendev.fraispro.R;

/**
 * Created by Quentin on 14/03/2017.
 */

public class FraisHolder extends RecyclerView.ViewHolder {
    public TextView name, type, value;
    public ImageView imageView;

    public FraisHolder(View view) {
        super(view);
        name = (TextView) view.findViewById(R.id.name);
        value = (TextView) view.findViewById(R.id.value);
        type = (TextView) view.findViewById(R.id.type);
        imageView = (ImageView) view.findViewById(R.id.image_type);
    }
}
