package com.example.mytherapy;

import androidx.recyclerview.widget.RecyclerView;

public class searchmedname  {
    private String Disease;
    private String Name;

    public String getChemical_Composition() {
        return Chemical_Composition;
    }

    public void setChemical_Composition(String chemical_Composition) {
        Chemical_Composition = chemical_Composition;
    }

    private String Chemical_Composition;

    public searchmedname()
    {

    }

    public searchmedname(String disease, String name, String chemical_Composition)
    {

        Disease = disease;
        Name = name;
        Chemical_Composition=chemical_Composition;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }
}
