package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class GomokuFactory {

    static Map<String, GomokuGame> gomokuMap = new HashMap<>();

    static {
        gomokuMap.put("Standard", new GomokuStd());
        gomokuMap.put("Renju", new GomokuRenju());
        gomokuMap.put("Omok", new GomokuOm());
        gomokuMap.put("Caro", new GomokuCaro());
        gomokuMap.put("Free", new GomokuFree());
    }

    public static Optional<GomokuGame> getGame(String game) { return Optional.ofNullable(gomokuMap.get(game)); }
}
