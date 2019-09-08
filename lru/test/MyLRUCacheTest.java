package com.rohit.lru.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.rohit.lru.MyLRUCache;

public class MyLRUCacheTest {
	
	MyLRUCache<Integer, String> mCache;

	@Before
	public void setUp() {
	    int capacity =3;
		mCache = new MyLRUCache<Integer, String>(capacity);
	}

	@Test
	public void testGet() {
		
		// test non existing object get
		String str1 = mCache.get(1);
		assertEquals(null, str1);
		
		// test a null object
		String str2 =  mCache.get(null);
		assertEquals(null, str2);
		
		// test a normal scenario
		mCache.put(1, "rohit");
		String str3 =  mCache.get(1);
		assertEquals("rohit", str3);
		
		//test another scenario
		mCache.put(2, "rohit2");
		mCache.put(3, "rohit3");
		
		String str4 = mCache.get(2);
		assertEquals("rohit2", str4);

	}

	@Test
	public void testPut() {
		
		// a normal put, non existing key returns null
		String str1 = mCache.put(1, "rohit");
		assertEquals(null, str1);
		
		// existing key, returns the old value
		String str2 = mCache.put(1, "rohit1");
		assertEquals("rohit", str2);
		
		// test after exceeding capacity , the oldest key gets removed. 
		mCache.put(2, "rohit2");
		mCache.put(3, "rohit3");
		mCache.put(4, "rohit4");
		
		String evictedString = mCache.get(1); // the oldest key to be removed is 1 here.
		assertEquals(null, evictedString);  
		
		// test null key
		String nullKeyValue = mCache.put(null, "rohit");
		assertEquals(null, nullKeyValue);
		assertEquals(null, mCache.get(null));
		
		// test null value
		mCache.put(5, null);
		assertEquals(null, mCache.get(5));
		
	}

}
