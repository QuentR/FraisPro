package fr.opendev.fraispro.model;

import java.util.Date;

/**
 * Created by Quentin on 13/03/2017.
 */

public class Frais {

    private Date dateFrais;
    private Float valueFrais;
    private String nameFrais;
    private boolean isRefunded;
    private TypeFrais typeFrais;

    public Frais()
    {

    }

    public Frais(Float valueFrais, String nameFrais) {
        this.valueFrais = valueFrais;
        this.nameFrais = nameFrais;
    }

    public Date getDateFrais() {
        return dateFrais;
    }

    public void setDateFrais(Date dateFrais) {
        this.dateFrais = dateFrais;
    }

    public Float getValueFrais() {
        return valueFrais;
    }

    public void setValueFrais(Float valueFrais) {
        this.valueFrais = valueFrais;
    }

    public String getNameFrais() {
        return nameFrais;
    }

    public void setNameFrais(String nameFrais) {
        this.nameFrais = nameFrais;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }

    public TypeFrais getTypeFrais() {
        return typeFrais;
    }

    public void setTypeFrais(TypeFrais typeFrais) {
        this.typeFrais = typeFrais;
    }
}
