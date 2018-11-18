package ru.sendto.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.sendto.crxmarkets.dto.CalculateVolumeRequest;
import ru.sendto.crxmarkets.dto.FilledHoles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LandscapeWithWaterBeanTest extends Assert {

    LandscapeWithWaterBean landService = new LandscapeWithWaterBean();

    @DataProvider
    public Object[][] landscapeDescriber() {
        return new Object[][]
                {
                        {new Integer[]{7, 1, 8, 7, 9, 4, 2, 3}, new ArrayList<Integer>(Arrays.asList(2, 1, 7))},
                        {new Integer[]{3, 2, 4, 1, 2},          new ArrayList<Integer>(Arrays.asList(1, 2))},
                        {new Integer[]{2, 1},                   null},
                };
    }

    @Test(groups = "unit" , dataProvider = "landscapeDescriber" , alwaysRun = true)
    public void filledHolesCalculationTest(Integer[] landscapeDescriber , List<Integer> expected)
    {
        FilledHoles filledHoles = null;
        filledHoles = landService.getLevelOfSurfacesFilledByWater(
                new CalculateVolumeRequest().setLevels(landscapeDescriber));
        assertThat(filledHoles.getLevels() , is(equalTo(expected)));
    }

}
