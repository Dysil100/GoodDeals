package duo.cmr.dysha.boundedContexts.generalresearch;

import java.util.*;

public class MyGeneralSearcher<T> {
    public List<T> searchThisExprIn(String expr, List<T> all) {
        List<T> filteredList = new ArrayList<>();

        for (T obj : all) {
            int matchCount = 0;
            // Parcourir les attributs de l'objet
            for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value != null) {
                    // Convertir la valeur en chaîne de caractères
                    String stringValue = String.valueOf(value).toLowerCase(Locale.ROOT);
                    if (stringValue.contains(expr) || expr.contains(stringValue)) {
                        matchCount++;
                    }
                    if (stringValue.equals(expr)) {
                        matchCount++;
                    }
                }
            }
            // Ajouter l'objet si tous les attributs correspondent
            if (matchCount > 1) {
                filteredList.add(obj);
            }
        }

        return filteredList;
    }
}


