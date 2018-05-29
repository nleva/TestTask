package ru.sendto.service.utils;

import java.util.Comparator;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import ru.sendto.crxmarkets.dto.Level;
/**
 * Comparators for different different purposes
 * Util class
 * @author Lev Nadeinsky
 *
 */
public class ComparatorProducer {
	
	private ComparatorProducer() {
	}
	
	/**
	 * Descending comparator for levels of landscape
	 * if indeces are equal then returns 0
	 * Comparator may sort levels desc, 
	 * levels with same height will be sorted asc by indeces
	 */
	@Produces
	@Named
	static public Comparator<Level> descLevelsComparator = (l1, l2) -> {
		if(l1.getIndex()==l2.getIndex()) return 0;
		
		if (l1.getHeight() == l2.getHeight()) {
			return l1.getIndex() > l2.getIndex() ? 1 : -1;
		} else {
			return l1.getHeight() < l2.getHeight() ? 1 : -1;
		}
	};
	
	/**
	 * Ascending comparator by index
	 */
	@Produces
	@Named
	static public Comparator<Level> ascIndexComparator = (l1, l2) -> {
		if(l1.getIndex()==l2.getIndex()) return 0;
		return l1.getIndex() > l2.getIndex() ? 1 : -1;
	};

}
