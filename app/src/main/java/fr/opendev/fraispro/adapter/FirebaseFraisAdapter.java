package fr.opendev.fraispro.adapter;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.Locale;

import fr.opendev.fraispro.model.Frais;

/**
 * Created by Quentin on 14/03/2017.
 */

public class FirebaseFraisAdapter extends FirebaseRecyclerAdapter<Frais, FraisHolder> {
    private Context context;

    public FirebaseFraisAdapter(Class<Frais> modelClass, int modelLayout, Class<FraisHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
    }

    @Override
    protected void populateViewHolder(FraisHolder holder, Frais frais, int position) {
        holder.name.setText(frais.getNameFrais());
        holder.value.setText(String.format(Locale.FRANCE, "%f€", frais.getValueFrais()));
        if (frais.getTypeFrais() != null) {
            holder.type.setText(frais.getTypeFrais().toString());
        } else {
            holder.type.setText("");
        }
    }

    @Override
    public DatabaseReference getRef(int position) {
        return super.getRef(position);
    }
}
