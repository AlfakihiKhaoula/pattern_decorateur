package isga.decorateur;

import isga.produits.Boisson;

public class Chocolat extends DecorateurBoisson{
    public Chocolat(Boisson boisson) {
        super(boisson);
    }

    @Override
    public String getDescription() {
        return boisson.getDescription() + " Au Chocolat";
    }

    @Override
    public double cout() {
        return 1.6+boisson.cout();
    }
}