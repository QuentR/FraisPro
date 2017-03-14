package fr.opendev.fraispro.model;

import static android.R.attr.id;
import static android.R.attr.type;

/**
 * Created by Quentin on 13/03/2017.
 */

public enum TypeFrais {

    RESTAURATION(0),
    HEBERGEMENT(1),
    PEAGE(2),
    PARKING(3),
    KILOMETRAGE(4);

    private final int type;

    TypeFrais(final int type)
    {
        this.type = type;
    }

    private int getType(){
        return type;
    }


}
