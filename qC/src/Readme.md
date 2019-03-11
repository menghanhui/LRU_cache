Features that I implemented:
1 - Simplicity. Integration needs to be dead simple.
6 - Flexible Schema
7 - Cache can expire

It is easy to initialise an LRUCache, just to define the maximum size of the cache.
For example, LRUCache lru =  new LRUCache(5);
It will initialise an LRUCache with size 5, which means it can hold up to 5 pairs of (key, value).

And it can accept any type of keys and values.(which shows in our tests)

My design of LRUCache involves 4 main data structures:
1. HashMap<K,V> mp;  //The map which stores all available (key, value)

2. List<K> keys;  //A list which specifies the order of all keys; the key located at the
                  //beginning is the key MRU; the key at the end is LRU.

3. HashMap<K, Time> times;  //Stores the expiry time of all keys; if the key is not in this map,
                            //it means this key does not have expiry time, it will not get expired.

4. List<Time> sortedTime; //Sorted list with all expiry time of keys; if the capacity is full,
                          //this list will help us clear all expiry keys first; if no key is expired,
                          //and the capacity is still full, then we will remove the LRU key.


Logic of set(key, value):
1. If the key already exists, put this key to beginning of "keys", update the value
2. Otherwise, put this new key to beginning of "keys", remove any old key if necessary.


Logic of set(key, value, Valid):
1. If the key already exists, put this key to beginning of "keys", update the value, set Valid time
2. Otherwise, put this new key to beginning of "keys", remove any old key if necessary, set Valid time


Logic of remove_key:
When the capacity is full, but we want to insert a new key, we have to remove some old keys from the cache.
1. First, we check whether there are some keys already expired. We go through sortedTime to do this checking, 
and remove all keys which are already expired.
2. Second, if there is no key expired, we want to clear the LRU key, which is the key located at the end of "keys".


Logic of get(key):
1. If the key doesn't exist, return null
2. If the key exists but already expire, remove this key, return null
3. If the key exists but doesn't expire, move it to front of "keys", update validation time, return corresponding value


I also implemented several JUnit test cases in MyTest.java, where you can see how I realize those functions.
