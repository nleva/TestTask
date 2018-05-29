/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sendto.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Setter;
import lombok.extern.java.Log;
import ru.sendto.dto.CalculateVolumeRequest;
import ru.sendto.dto.LandscapeNotDefException;
import ru.sendto.dto.Level;
import ru.sendto.dto.Volume;
import ru.sendto.ejb.interceptor.BundleResult;

/**
 * Service to calculate volume of holes (wells)
 *
 * @author Lev Nadeinsky
 * @date 2017-06-05
 */
@Stateless
@LocalBean
@Log
public class RainHillsService {

	@Inject
	@Named
	@Setter
	Comparator<Level> descLevelsComparator;
	
	@Inject
	@Named
	@Setter
	Comparator<Level> ascIndexComparator;

	/**
	 * Calculate volume of all holes in integer array Algorithm sorts array and
	 * calculates well volume between the first and the second element then removes
	 * all calculated elements and repeats until sorted set is empty
	 * 
	 * compute complexity is about O(4*n*log(n)) = 2*( TreeSet addAll and removeAll)
	 * it needs about 14n memory because of
	 * using collections (TreeSet takes 40B per element)
	 * 
	 * @param CalculateVolumeRequest
	 *            - request object, contains int[]
	 * @return Volume - dto result that contains long value.
	 * @throws LandscapeNotDefException 
	 */
	@BundleResult
	public Volume calculateVolume(@Observes CalculateVolumeRequest req) {
		if (req.getLevels() == null)
			throw new LandscapeNotDefException();
		
		if (req.getLevels().length <= 2)
			return new Volume().setValue(0); 

		List<Integer> list = Arrays.asList(req.getLevels());

		List<Level> levels = mapToLevels(list);

		TreeSet<Level> ascByIndex = new TreeSet<>(ascIndexComparator);
		TreeSet<Level> descByHeight = new TreeSet<>(descLevelsComparator);
		descByHeight.addAll(levels);
		ascByIndex.addAll(levels);
		levels=null; // <---- gc
		
		long volume = 0;

		Level hight = descByHeight.first();
		for (Level low; descByHeight.size() > 2;) {

			descByHeight.remove(hight);
			ascByIndex.remove(hight);
			
			low = descByHeight.first();
			volume += removeHole(ascByIndex, descByHeight, hight, low);
			
			hight = low;
		}

		log.fine("hills:" + list + ", volume:" + volume);

		return new Volume().setValue(volume);
	}

	/**
	 * maps list of Integer to list of Levels
	 */
	List<Level> mapToLevels(List<Integer> list) {
		List<Level> levels = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			levels.add(new Level().setHeight(list.get(i)).setIndex(i));
		}
		return levels;
	}

	/**
	 * removes elements inside a hole
	 * @param ascByIndex sorted by index Levels
	 * @param descByHeight desc sorted by height Levels
	 * @param hight boundary of hole
	 * @param low boundary of hole
	 * @return
	 */
	int removeHole(TreeSet<Level> ascByIndex, TreeSet<Level> descByHeight, Level hight, Level low) {
		int holeVolume = 0;
		
		Level from 	= hight.getIndex() < low.getIndex()?hight:low;
		Level to 	= hight.getIndex() > low.getIndex()?hight:low;
		
		from=ascByIndex.higher(from);
		for(;from!=null && from.getIndex()<to.getIndex(); from=ascByIndex.higher(from)) {
			holeVolume += (low.getHeight() - from.getHeight());
			descByHeight.remove(from);
			ascByIndex.remove(from);
		}
		return holeVolume;
	}

}
