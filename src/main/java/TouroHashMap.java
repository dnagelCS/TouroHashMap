import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TouroHashMap {

    public static final int BUCKET_SIZE = 1024;

    static class Entry {
        String word;
        String definition;

        public Entry(String word, String definition) {
            this.word = word;
            this.definition = definition;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(word, entry.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "word='" + word + '\'' +
                    ", definition='" + definition + '\'' +
                    '}';
        }
    }

    List<Entry> buckets[];

    public TouroHashMap() {
        buckets = new List[BUCKET_SIZE];
        for (int i=0 ; i< BUCKET_SIZE; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public void put(String word, String definition) {
        Entry entry = new Entry(word, definition);
        int hashCode = entry.hashCode();
        int index = Math.abs(hashCode) % BUCKET_SIZE;
        buckets[index].add(entry);
    }

    public String get(String word) {
        Entry find = new Entry(word, "");
        int hashCode = find.hashCode();
        int bucketIndex = Math.abs(hashCode) % BUCKET_SIZE;
        int listIndex = buckets[bucketIndex].indexOf(find);
        if (listIndex == -1) {
            return null;
        }
        return buckets[bucketIndex].get(listIndex).definition;
    }
}