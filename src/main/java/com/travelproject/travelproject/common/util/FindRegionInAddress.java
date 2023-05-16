package com.travelproject.travelproject.common.util;

import com.travelproject.travelproject.util.FindRegion;

public class FindRegionInAddress {

    public static String findRegionInAddress(String writeTouristSpotAddress) {
        int writeRegionIndex = writeTouristSpotAddress.indexOf(" ");

        String removeAddress = writeTouristSpotAddress.substring(writeRegionIndex);
        String blank = "";
        String writeRegion = writeTouristSpotAddress.replace(removeAddress, blank);
        String writeRegionResult= FindRegion.findRegion(writeRegion);

        return writeRegionResult;
        
    }
}
