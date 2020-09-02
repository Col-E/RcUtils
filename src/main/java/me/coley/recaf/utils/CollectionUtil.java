package me.coley.recaf.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Misc collection utilities.
 */
public class CollectionUtil {
	/**
	 * Copies a collection into a set.
	 *
	 * @param original
	 * 		Original collection.
	 * @param <T>
	 * 		Type of item in collection.
	 *
	 * @return Copied set.
	 */
	public static <T> Set<T> copySet(Collection<T> original) {
		return new HashSet<>(original);
	}

	/**
	 * Copies a collection into a list.
	 *
	 * @param original
	 * 		Original collection.
	 * @param <T>
	 * 		Type of item in collection.
	 *
	 * @return Copied list
	 */
	public static <T> Set<T> copyList(Collection<T> original) {
		return new HashSet<>(original);
	}

	/**
	 * Copies a map into another map.
	 *
	 * @param original
	 * 		Original collection.
	 * @param <K>
	 * 		Type of key items.
	 * @param <V>
	 * 		Type of value items.
	 *
	 * @return Copied map.
	 */
	public static <K, V> Map<K, V> copyMap(Map<K, V> original) {
		return new HashMap<>(original);
	}
}
