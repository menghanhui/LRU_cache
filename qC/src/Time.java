
public class Time<K extends Comparable> {
    long dueTime;
    long validation;
    K key;

    public Time(K key, long valid){
        this.key = key;
        validation = valid;
        dueTime = System.currentTimeMillis() + valid;
//        System.out.print("The deadline time is: ");
//        System.out.println(dueTime);
    }

    public K getKey(){
        return key;
    }


    public long getDueTime(){
        return dueTime;
    }

    public void update(){
        dueTime = System.currentTimeMillis() + validation;
    }

    public int compareTo(Time<K> t) {
        if(dueTime < t.getDueTime()){
            return -1;
        }else if(dueTime == t.getDueTime()){
            return 0;
        }else{
            return 1;
        }

    }
}
