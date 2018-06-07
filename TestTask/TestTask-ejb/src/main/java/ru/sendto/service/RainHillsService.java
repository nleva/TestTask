/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sendto.service;

import java.util.Arrays;
import java.util.Comparator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Setter;
import lombok.extern.java.Log;
import ru.sendto.crxmarkets.dto.CalculateVolumeRequest;
import ru.sendto.crxmarkets.dto.LandscapeNotDefException;
import ru.sendto.crxmarkets.dto.Level;
import ru.sendto.crxmarkets.dto.Volume;
import ru.sendto.ejb.interceptor.BundleResult;

/**
 * Service to calculate volume of holes (wells)
 *
 * @author Lev Nadeinsky
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
	 * Calculate volume of all "holes" in integer array
	 * 
	 * compute complexity is  O(n) 
	 * it needs about O(1) memory 
	 * 
	 * @param CalculateVolumeRequest
	 *            - request object, contains int[]
	 * @return Volume - dto result that contains long value.
	 * @throws LandscapeNotDefException 
	 */
	@BundleResult
	public Volume calculateVolume(@Observes CalculateVolumeRequest req) {
		Integer[] landscape = req.getLevels();
		if (landscape == null)
			throw new LandscapeNotDefException();
		
		int length = landscape.length;
		Volume result = new Volume();
		if (length <= 2)
			return result.setValue(0); 

		int size = landscape.length;
		
		int i = 0, j=size-1;
		int left=landscape[i];
		int right=landscape[size-1-i];
		
		for(; i<j ;) {
			if(left<right) {
				left = calculateSingleHoleAndGetNextBorder(landscape, result, ++i, left);
			}else {
				right = calculateSingleHoleAndGetNextBorder(landscape, result, --j, right);
			}
		}
		
		log.fine("hills:" + Arrays.toString(req.getLevels()) + ", volume:" + result.getValue());

		return result;
	}

	/**
	 * Calulates single hole, inctements result volume and return lowest border
	 * @param landscape - Integer array
	 * @param result - result dto
	 * @param i - landscape index
	 * @param lowBorder
	 * @return
	 */
	int calculateSingleHoleAndGetNextBorder(Integer[] landscape, Volume result, int i, int lowBorder) {
		int next = landscape[i];
		if(lowBorder>next) {
			result.setValue(result.getValue()+lowBorder-next);
		}else {
			lowBorder=next;
		}
		return lowBorder;
	}

	

}
