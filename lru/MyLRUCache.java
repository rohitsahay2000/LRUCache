package com.rohit.lru;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author rsahay
 * 
 * A concurrent LRU cache Implementation.
 * @param <K> Key
 * @param <V> Value
 */
public class MyLRUCache<K, V> {

	/* Concurrent hashmap for thread safe map operations */
	ConcurrentHashMap<K, CacheNode<K, V>> myHashMap = new ConcurrentHashMap<>();
	
	/* Concurrent Linked queue for thread safe list operations
	 * The head of the queue is that element that has been on the queue the longest time. 
	 * The tail of the queue is that element that has been on the queue the shortest time. 
	 * New elements are inserted at the tail of the queue, and the queue retrieval operations obtain elements at the head of the queue. 
     */
	ConcurrentLinkedQueue<CacheNode<K, V>> concurrentList = new ConcurrentLinkedQueue<>();

	private int capacity; // capacity of the cache

	public MyLRUCache(int capacity) {
		this.capacity = capacity;
	}

	
	public V get(K key) {
		
		if(key==null)
			return null;

		if (myHashMap.get(key) == null) {
			return null;
		}

		CacheNode<K, V> node = myHashMap.get(key); // O(1)
		removeNode(node); 
		offerNode(node); // O(1)

		return node.value;

	}

	public V put(K key, V value) {
		
		if (key==null)
			return null;
		
		if (myHashMap.containsKey(key)) {
			CacheNode<K, V> node = myHashMap.get(key);
			V oldValue=node.value;
			node.value = value;
			removeNode(node);
			offerNode(node);
			return oldValue;
		} else {
			if (myHashMap.size() >= capacity) {
				CacheNode<K, V> lruNode = concurrentList.poll(); 
				myHashMap.remove(lruNode.key);
			}
		}

		CacheNode<K, V> node = new CacheNode<>(key, value);
		offerNode(node);
		myHashMap.put(key, node);
		return null;
	}

	/* removes the node from the queue*/
	void removeNode(CacheNode<K, V> node) {

		concurrentList.remove(node); // O(1) operation.

	}

	/* Inserts the specified element at the tail of the queue. */
	void offerNode(CacheNode<K, V> node) {

		concurrentList.offer(node); // O(1) operation

	}

}

