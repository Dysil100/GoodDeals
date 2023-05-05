package duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.security;


import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class ChatDiscussionHash {

    public ChatDiscussionHash() {
    }

    public List<Long> getUserIdsFromChatDiscussionHash(String chatDiscussionHash) {
        byte[] hashBytes = new BigInteger(chatDiscussionHash, 16).toByteArray();

        // Extraire les deux IDs à partir du hash
        long id1 = ByteBuffer.wrap(Arrays.copyOfRange(hashBytes, 1, 9)).getLong();
        long id2 = ByteBuffer.wrap(Arrays.copyOfRange(hashBytes, 9, 17)).getLong();

        List<Long> ids = Arrays.asList(id1, id2);
        System.out.println(ids);
        return ids;
    }

    public String getChatDiscussionHashFor(Long id1, Long id2) {
        String input = id1.compareTo(id2) < 0 ? id1 + "" + id2 : id2 + "" + id1; // Concaténer les chaînes dans l'ordre alphabétique
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
