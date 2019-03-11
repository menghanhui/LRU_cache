import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyTest {

    @Test
    public void LRU_empty_base_case() {
        LRUCache LRU = new LRUCache(0);
        LRU.set(1, "one");

        assertEquals(null, LRU.get(1));
        LRU.getAll();
    }



    @Test
    public void LRU_different_key_type() {
        LRUCache LRU = new LRUCache(3);
        LRU.set(1, "one");
        LRU.set("two", "two");
        LRU.set('3', "three");

        assertEquals(null, LRU.get(2));
        assertEquals("three", LRU.get('3'));
        assertEquals(null, LRU.get("four"));
        LRU.getAll();
    }

    @Test
    public void LRU_key_more_than_capacity() {
        LRUCache LRU = new LRUCache(3);
        LRU.set(1, "one");
        LRU.set(2, "two");
        LRU.set(3, "three");

        LRU.set(4, "four");

        assertEquals(null, LRU.get(1));
    }

    @Test
    public void LRU_same_key_diffrent_values() {
        LRUCache LRU = new LRUCache(3);
        LRU.set(1, "one");
        LRU.set(2, "two");
        LRU.set(3, "three");

        LRU.set(1, 1);

        assertEquals(1, LRU.get(1));
    }

    @Test
    public void LRU_expired_key() {
        LRUCache LRU = new LRUCache(3);
        LRU.set(1, "one");
        LRU.set(2, "two");
        LRU.set(3, "three", 1);

        int count = 0;
        while(count<10000000){
            count ++;
        }

        assertEquals(null, LRU.get(3));
        LRU.getAll();
    }


    @Test
    public void LRU_not_expired_key() {
        LRUCache LRU = new LRUCache(3);
        LRU.set(1, "one", 1);
        LRU.set(2, "two");
        LRU.set(3, "three");


        assertEquals("one", LRU.get(1));
        LRU.getAll();
    }


    @Test
    public void LRU_clear_expired_key_when_full() {
        LRUCache LRU = new LRUCache(3);
        LRU.set(1, "one");
        LRU.set(2, "two");
        LRU.set(3, "three",1);

        //wait until expire:
        int count = 0;
        while(count<100000000){
            count ++;
        }

        LRU.set(4, "four");

        assertEquals(null, LRU.get(3));
        LRU.getAll();
    }



    @Test
    public void LRU_clear_last_key_when_no_expires() {
        LRUCache LRU = new LRUCache(3);
        LRU.set(1, "one");
        LRU.set(2, "two");
        LRU.set(3, "three",1);

        LRU.set(4, "four");

        assertEquals("three", LRU.get(3));
        assertEquals(null, LRU.get(1));
        LRU.getAll();
    }





}
