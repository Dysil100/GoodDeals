package duo.cmr.deuxKolos.boundedContexts.analysealimentaire.domain.apportNutritifs;

public class Lysine extends ResultatEnergetique {

    public Lysine(Double valeur, String appreciation) {
        super(valeur, "Lysine", appreciation);
    }

    public Lysine(Double lysine) {
        super(lysine, "Lysine");
    }
}
