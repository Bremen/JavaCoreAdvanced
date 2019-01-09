import java.util.HashMap;
import java.util.Map;

public class RepeatCounter {
    public static void main(String[] args) {
        String words = "морковь, репа, редька, редис, петрушка,пастернак,сельдерей, хрен,брюква, редька,морковь, свёкла, репа, брюква, редька, редис, петрушка, пастернак, сельдерей, хрен, сельдерей, хрен, редис, петрушка, пастернак, морковь, сельдерей, хрен, сельдерей, хрен";
        String[] wordArray = words.split(",\\s?");

        repeatsReport(findRepeats(wordArray));
    }

    private static void repeatsReport(Map<String, Integer> repeats) {
        for (String word : repeats.keySet()) {
            System.out.println(word + "  " + repeats.get(word));
        }
    }

    private static Map<String, Integer> findRepeats(final String[] wordArray) {
        Map<String, Integer> unicValueRepeats = new HashMap<>();

        for (String word : wordArray) {
            if (unicValueRepeats.containsKey(word)){
                unicValueRepeats.computeIfPresent(word, (k, v) -> v + 1);
            }else{
                unicValueRepeats.put(word, 1);
            }
        }

        return unicValueRepeats;
    }
}
