import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleTelephoneDirectory {
    private Map<String, ArrayList<String>> directory = new HashMap<>();

    public void add(String name, String phoneNumber){
        if (!directory.containsKey(name)){
            directory.put(name, new ArrayList<>());
        }

        directory.get(name).add(phoneNumber);
    }

    public ArrayList<String> get(String name){
        return directory.get(name);
    }

    public void printPhoneNumbers(String name){
        System.out.println(name);
        for (String phoneNumber : get(name)) {
            System.out.println("\t" + phoneNumber);
        }
    }
}

class SimpleTelephoneDirectoryTest{
    public static void main(String[] args) {
        SimpleTelephoneDirectory directory = new SimpleTelephoneDirectory();

        directory.add("Brown", "888-999");
        directory.add("Black", "555-444");
        directory.add("Brown", "777-666");
        directory.add("White", "111-222");
        directory.add("Brown", "333-444");
        directory.add("Black", "000-111");

        directory.printPhoneNumbers("Brown");
        directory.printPhoneNumbers("Black");
        directory.printPhoneNumbers("White");
    }
}
