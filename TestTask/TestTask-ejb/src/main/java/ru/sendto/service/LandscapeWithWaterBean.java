package ru.sendto.service;


import lombok.extern.java.Log;
import ru.sendto.crxmarkets.dto.CalculateVolumeRequest;
import ru.sendto.crxmarkets.dto.FilledHoles;
import ru.sendto.crxmarkets.dto.LandscapeNotDefException;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
@LocalBean
@Log
public class LandscapeWithWaterBean {

    /**
     * In a rugged landscape, water will not remain at all levels.
     * Therefore, this class helps maintain the level of the surfaces in which will remain in water.
     * filledHolesLevel save all such levels.
     */
    private List<Integer>           filledSurfacesLevel;


    /**
     *
     * @param req
     * @return
     */
    public FilledHoles getLevelOfSurfacesFilledByWater(CalculateVolumeRequest req)
    {
        //
        FilledHoles result =  new FilledHoles(); // DTO class

        Integer[] landscapeLevel =  req.getLevels();
        int levelsCount = landscapeLevel.length;
        filledSurfacesLevel = new ArrayList<>();

        //initial checking
        if (landscapeLevel == null)
            throw new LandscapeNotDefException();

        if (levelsCount <= 2)
            return result.setLevels(null);


        int leftEdgePointer =      0;
        int rightEdgePointer=      levelsCount-1;

        int leftPathTracer=   landscapeLevel[leftEdgePointer];
        int rightPathTracer=  landscapeLevel[levelsCount-1-leftEdgePointer];

        for(; leftEdgePointer<rightEdgePointer ;) {
            if(leftPathTracer<rightPathTracer) {
                leftPathTracer = calculateSingleHoleAndGetNextBorder
                        (landscapeLevel, ++leftEdgePointer, leftPathTracer);
            }else {
                rightPathTracer = calculateSingleHoleAndGetNextBorder
                        (landscapeLevel, --rightEdgePointer, rightPathTracer);
            }
        }

        result.setLevels(filledSurfacesLevel);
        return result;
    }


    /**
     * @param landscapeLevels - Integer array
     * @param i - landscape index
     * @param lowBorder
     * @return
     */
    int calculateSingleHoleAndGetNextBorder(Integer[] landscapeLevels, int i, int lowBorder) {
        int next = landscapeLevels[i];
        if(lowBorder>next) {
            filledSurfacesLevel.add(next);
        }else {
            lowBorder=next;
        }
        return lowBorder;
    }


}
