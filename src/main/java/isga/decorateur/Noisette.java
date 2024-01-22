package isga.decorateur;


import isga.produits.Boisson;

public class Noisette extends DecorateurBoisson{
    public Noisette(Boisson boisson) {
        super(boisson);
    }

    @Override
    public String getDescription() {
        return boisson.getDescription() + " Au Noisette";
    }

    @Override
    public double cout() {
        return 1.2+boisson.cout();
    }
}
