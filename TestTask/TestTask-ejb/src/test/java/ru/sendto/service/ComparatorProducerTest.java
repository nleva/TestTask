package ru.sendto.service;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.sendto.crxmarkets.dto.Level;
import ru.sendto.service.utils.ComparatorProducer;
/**
 * Comparators for different different purposes
 * @author Lev Nadeinsky
 *
 */
public class ComparatorProducerTest  extends Assert{

	Comparator<Level> descLevelsComparator = ComparatorProducer.descLevelsComparator;
	Comparator<Level> ascIndexComparator = ComparatorProducer.ascIndexComparator;
	
	@DataProvider
	public Object[][] levels() {
		return new Object[][]{
			{new Level().setHeight(0).setIndex(0),new Level().setHeight(0).setIndex(0),	0,0},
			{new Level().setHeight(1).setIndex(0),new Level().setHeight(2).setIndex(0),	0,0},
			{new Level().setHeight(1).setIndex(0),new Level().setHeight(1).setIndex(1),	-1,-1},
			{new Level().setHeight(1).setIndex(1),new Level().setHeight(1).setIndex(0),	1,1},
			{new Level().setHeight(1).setIndex(2),new Level().setHeight(2).setIndex(0),	1,1},
			{new Level().setHeight(2).setIndex(2),new Level().setHeight(1).setIndex(0),	-1,1},
			{new Level().setHeight(1).setIndex(0),new Level().setHeight(2).setIndex(2),	1,-1},
			{new Level().setHeight(2).setIndex(0),new Level().setHeight(1).setIndex(2),	-1,-1},
				//	    	{null,						0},
		};
	}

	@Test(groups="unit",dataProvider = "levels")
	void comapreTest(Level l1, Level l2, int descExpected, int ascExpected) {
		assertThat(descLevelsComparator.compare(l1, l2), is(equalTo(descExpected)));
		assertThat(ascIndexComparator.compare(l1, l2), is(equalTo(ascExpected)));
		
	}

}
