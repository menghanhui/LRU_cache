import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LRUCache<K extends Comparable, V> implements Cache<K, V>{


    int cp;  //capacity
    HashMap<K,V> mp = null; //map the key to the node in the linked list
    HashMap<K, Time> times = null;
    List<K> keys = null;
    List<Time> sortedTime = null;


    public LRUCache(int cap){
        cp = cap;
        mp =  new HashMap<>();
        times =  new HashMap<>();
        keys = new ArrayList<>();
        sortedTime = new ArrayList<>();
    }


    @Override
    public V get(K key) {

        if(keys.contains(key)){//the key exist:
            if(!times.containsKey(key)){//There is no expiry time for this key"

                move_to_front(key);
                return mp.get(key);

            } else if(times.get(key).getDueTime()>= System.currentTimeMillis()) {
                //The key does not expire:
                sortedTime.remove(times.get(key));
                times.get(key).update();//Update the due time of this key
                move_to_front(key);

                insertTime(times.get(key));
                return mp.get(key);
            } else { //This key already expires:
                mp.remove(key);
                keys.remove(key);
                times.remove(key);
                sortedTime.remove(times.get(key));

                System.out.println("This key already expires:" + String.valueOf(key));
                return null;
            }


        }else{//the key does not exist
            System.out.println("This key doesn't exist:" + String.valueOf(key));
            return null;
        }
    }


    public void set(K key, V value, long valid){
        if(valid<=0){
            System.out.println("The validation has to be greater than 0");
            return;
        }else{
            Time newDDL = new Time(key, valid);
            times.put(key, newDDL);
            insertTime(newDDL);//put newDDL into sortedTime
            set(key, value);
        }

    }


    private void insertTime(Time newDDL) {
        for(int i=0; i<sortedTime.size(); i++){
            if(newDDL.compareTo(sortedTime.get(0)) <= 0){
            //newTime is smaller or equal to current time, which means it will expire first
                sortedTime.add(i, newDDL);
                return;
                }
            }

        sortedTime.add(sortedTime.size(), newDDL);
    }


    @Override
    public void set(K key, V value) {
        if (!keys.contains(key)) {
            keys.add(0, key);
            mp.put(key, value);

        }else{
            mp.put(key, value);
            move_to_front(key);
        }

        clear_expire();
        removeLastKey();
    }



    private void clear_expire() {
        for(int i=0; i<sortedTime.size(); i++){
            if(sortedTime.get(i).getDueTime()<System.currentTimeMillis()){
                K to_remove = (K) sortedTime.get(i).getKey();
                removeKey(to_remove);
//                sortedTime
                sortedTime.remove(i); //remove this Time from sortedTime
                i--;
            }else{
                break;//No time expires for the rest of array
            }
        }
    }



    public void getAll(){
//        System.out.println(keys.size());

        for(K key: keys){
            System.out.println(String.valueOf(key)+": "+String.valueOf(mp.get(key)));
        }
    }


    //This operation assumes that the key already exists:
    private void move_to_front(K key){
        keys.remove(key);
        keys.add(0, key);
    }


    //Remove the Least-Recently-Used key when the capacity is full:
    private void removeKey(K to_remove) {
        mp.remove(to_remove);
        keys.remove(to_remove);
        times.remove(to_remove);
    }



    //Remove the Least-Recently-Used key when the capacity is full:
    private void removeLastKey() {
        if(keys.size()>cp){
            K to_remove = keys.get(cp);//get the last key
        mp.remove(to_remove);
        keys.remove(to_remove);
        times.remove(to_remove);
        }
    }


}



