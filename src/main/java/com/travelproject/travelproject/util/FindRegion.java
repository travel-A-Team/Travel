package com.travelproject.travelproject.util;

public class FindRegion {
    
    public static String findRegion(String writeRegion) {
        boolean includChungBuk = writeRegion.contains("충") && writeRegion.contains("북");
        boolean includChungNam = writeRegion.contains("충") && writeRegion.contains("남");
        boolean includGyeongBuk = writeRegion.contains("경") && writeRegion.contains("북");
        boolean includGyeongNam =writeRegion.contains("경") && writeRegion.contains("남");
        boolean includJeonBuk = writeRegion.contains("전") && writeRegion.contains("북");
        boolean includJeonNam = writeRegion.contains("전") && writeRegion.contains("남");

        
        if (includChungBuk) return writeRegion = "충청북도";
        if (includChungNam) return writeRegion = "충청남도";
        if (includGyeongBuk) return writeRegion = "경상북도";
        if (includGyeongNam) return writeRegion = "경상남도";
        if (includJeonBuk) return writeRegion = "전라북도";
        if (includJeonNam) return writeRegion = "전라남도";


        if (writeRegion.length() != 2)  {
            int startIndex = 0;
            int lastIndex = 2;
            writeRegion = writeRegion.substring(startIndex, lastIndex);
        }

        return writeRegion;
    }
}
