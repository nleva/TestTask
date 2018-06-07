/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sendto.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.sendto.crxmarkets.dto.CalculateVolumeRequest;
import ru.sendto.crxmarkets.dto.LandscapeNotDefException;
import ru.sendto.crxmarkets.dto.Volume;
import ru.sendto.service.utils.ComparatorProducer;

/**
 * Tests for RainHillsService
 *
 * @author Lev Nadeinsky
 */
//@Log
public class RainHillsServiceTest extends Assert {
	
	
	RainHillsService service = new RainHillsService()
			.setDescLevelsComparator(ComparatorProducer.descLevelsComparator)
			.setAscIndexComparator(ComparatorProducer.ascIndexComparator);
	
	@DataProvider
	public Object[][] landscape() {
		return new Object[][]{
			{new Integer[]{1,2,3,4,5,6,7},	0L},
			{new Integer[]{7,6,5,4,4,3},	0L},
			{new Integer[]{3,2,4,1,2},		2L},
			{new Integer[]{4,1,1,0,2,3},	8L},
			{new Integer[]{4,1,1,7,2,3},	7L},
			{new Integer[]{7,1,1,4,2,3},	7L},
			{new Integer[]{7,1,8,7,9,4,2,3},8L},
			{new Integer[]{-5,7},			0L},
			{new Integer[]{1},				0L},
			{new Integer[]{},				0L},
		//	    	{null,						0},
		};
	}

	@Test(groups="unit",dataProvider = "landscape", alwaysRun=true)
	public void volumeCalculationTest(Integer[] landscape, Long expected) {
		Volume vol = null;
		vol = service.calculateVolume(
				new CalculateVolumeRequest()
					.setLevels(landscape));
		assertThat(vol.getValue(), is( equalTo( expected )));

		//reverse test
		vol = service.calculateVolume(
				new CalculateVolumeRequest()
					.setLevels(
							reverse(landscape)));
		assertThat(vol.getValue(), is( equalTo( expected )));

		//moved test
		vol = service.calculateVolume(
				new CalculateVolumeRequest()
					.setLevels(
							moveY(landscape,landscape.length)));
		assertThat(vol.getValue(), is( equalTo( expected )));

		//Duplicated test
		vol = service.calculateVolume(
				new CalculateVolumeRequest()
					.setLevels(
							duplicated(landscape)));
		assertThat(vol.getValue(), is( equalTo( expected*2 )));
		

		//scaled test
		vol = service.calculateVolume(
				new CalculateVolumeRequest()
					.setLevels(
							scale(landscape,2)));
		assertThat(vol.getValue(), is( equalTo( expected*2 )));
	}
	
	private Integer[] reverse(Integer[] array) {
		for(int i = 0; i < array.length / 2; i++){
		    int temp = array[i];
		    array[i] = array[array.length - i - 1];
		    array[array.length - i - 1] = temp;
		}
		return array;
	}


	private Integer[] scale(Integer[] array, int factor) {
		for(int i = 0; i < array.length; i++){
			array[i]=array[i]*factor;
		}
		return array;
	}

	private Integer[] moveY(Integer[] array, int deltaY) {
		for(int i = 0; i < array.length; i++){
			array[i]+=deltaY;
		}
		return array;
	}
	private Integer[] duplicated(Integer[] array) {
		Integer[] dub = new Integer[array.length*2];
		for(int i = 0; i < dub.length; i++){
			dub[i]=array[i/2];
		}
		return dub;
	}
	
	
	@Test(groups="unit", expectedExceptions = LandscapeNotDefException.class)
	public void landscapeNotDefTest() {
		service.calculateVolume(new CalculateVolumeRequest());
	}

}
