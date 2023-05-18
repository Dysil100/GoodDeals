package duo.cmr.dysha.boundedContexts.generalhelpers.generalresearch;

import java.util.*;

public class MyGeneralSearcher<T> {
    public List<T> searchThisExprIn(String querry, List<T> all) {
        List<T> filteredList = new ArrayList<>();
        Map<T, Integer> mapResult = new HashMap<>();
        String expr = querry.toLowerCase(Locale.ROOT);

        for (T obj : all) {
            int matchCount = 0;
            // Parcourir les attributs de l'objet
            for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = null;
                try {value = field.get(obj);} catch (IllegalAccessException e) {e.printStackTrace();}
                if (value != null) {
                    // Convertir la valeur en chaîne de caractères
                    String stringValue = String.valueOf(value).toLowerCase(Locale.ROOT);
                    if (stringValue.contains(expr) || expr.contains(stringValue)) matchCount++;
                    if (stringValue.equals(expr)) matchCount++;
                }
            }
            // Ajouter l'objet si tous les attributs correspondent
            if (matchCount > 1) mapResult.put(obj, matchCount);
        }
        ArrayList<T> ts = new ArrayList<>(mapResult.keySet());
        return ts.stream().sorted().toList();
    }
}


