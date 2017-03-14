package fr.opendev.fraispro.ui;

import android.view.View;

/**
 * Created by Quentin on 13/03/2017.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
