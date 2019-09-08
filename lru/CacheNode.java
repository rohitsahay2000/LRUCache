package com.rohit.lru;

public class CacheNode <K, V> {

	K key;
	V value;

	public CacheNode(K key, V value) {
		this.key = key;
		this.value = value;

	}
}