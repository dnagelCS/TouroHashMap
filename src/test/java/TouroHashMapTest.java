import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TouroHashMapTest {

    @Test
    public void put() {
        // given
        TouroHashMap map = new TouroHashMap();

        // when
        map.put("HELLO", "WORLD");

        // then
        for (List<TouroHashMap.Entry> list: map.buckets) {
            for (TouroHashMap.Entry entry : list) {
                if (entry != null &&
                        entry.word.equals("HELLO") &&
                        entry.definition.equals("WORLD")) {
                    return;
                }
            }
        }
        Assert.fail("HELLO->WORLD not found");
    }

    @Test
    public void get() {
        // given
        TouroHashMap map = new TouroHashMap();
        TouroHashMap.Entry entry = new TouroHashMap.Entry("HELLO", "WORLD");
        int hashcode = entry.hashCode();
        map.buckets[hashcode % TouroHashMap.BUCKET_SIZE].add(entry);

        // when
        String definition = map.get("HELLO");

        // then
        assertEquals("WORLD", definition);
    }

    @Test
    public void get_null() {
        // given
        TouroHashMap map = new TouroHashMap();

        // when
        String definition = map.get("HELLO");

        // then
        assertNull(definition);
    }

    @Test
    public void get_1000Words() {
        // given
        TouroHashMap map = new TouroHashMap();

        // when
        for (int i=0 ;i<1000; i++) {
            map.put("HELLO", "WORLD");
        }

        // then
    }

    @Test
    public void dictionaryFile() throws FileNotFoundException {
        // given
        TouroHashMap map = new TouroHashMap();
        File file = new File("dictionary.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String word = scanner.next();
            String definition = scanner.nextLine();
            map.put(word, definition.trim());
        }

        // when
        String definition = map.get("CARNIVAL");

        // then
        assertEquals("a traveling amusement show [n -S]", definition);
    }
}