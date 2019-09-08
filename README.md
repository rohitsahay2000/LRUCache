# LRUCache

* This cache uses a concurrent Hashmap and a concurrent linked queue.
* ConcurrentHashMap is used to store the node location. The access and put to the map is O(1).
* ConcurrentLinkedQueue stores all the actual nodes in a queue fashion. 
* ConcurrentLinkedQueue ensures that the head of the queue is that element that has been on the queue the longest time. 
* ConcurrentLinkedQueue ensures tail of the queue is that element that has been on the queue the shortest time. 
* Any get operation on the cache, first checks if the key is present. If key is present the value is returned and then the node containing key and value 
is moved to the tail indicating the node has been accessed most recently. GET in worst case might be O(n) because to remove a node from actual location in the 
queue, a iteration is needed to reach there to remove. So, removal of a node in worst case will be O(n) and adding t the tail will be O(1).
* Any put operation, adds the element to the tail of the queue. If the capacity increases, then the head of the queue (the oldest node) is removed and the 
new node is added to tail. Put is O(1) .


# Concurrency
* Concurrent hashmap ensures thread safe operations for put and get.
* Concurrent Linked queue ensures thread safe operations for node poll, peek, offer, remove etc.

