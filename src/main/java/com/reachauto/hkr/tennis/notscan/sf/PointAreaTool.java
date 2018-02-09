package com.reachauto.hkr.tennis.notscan.sf;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-29 14:20
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description: 点区域判断
 */
public class PointAreaTool {
    /**
     * 判断一个指定的经纬度点是否落在一个多边形区域内
     *
     * @param ALon 1
     * @param ALat 2
     * @param jingWeiDuJiHeList 3
     * @return
     */
    public final static boolean isPtInPoly(double ALon, double ALat, List<Points> jingWeiDuJiHeList) {
        int iSum = 0, iCount = jingWeiDuJiHeList.size();
        double dLon1, dLon2, dLat1, dLat2, dLon;
        if (iCount < 3) {
            return false;
        }

        for (int i = 0; i < iCount - 1; i++) {
            if (i == iCount - 1) {
                dLon1 = jingWeiDuJiHeList.get(i).lngX;
                dLat1 = jingWeiDuJiHeList.get(i).latY;
                dLon2 = jingWeiDuJiHeList.get(0).lngX;
                dLat2 = jingWeiDuJiHeList.get(0).latY;
            } else {
                dLon1 = jingWeiDuJiHeList.get(i).lngX;
                dLat1 = jingWeiDuJiHeList.get(i).latY;
                dLon2 = jingWeiDuJiHeList.get(i + 1).lngX;
                dLat2 = jingWeiDuJiHeList.get(i + 1).latY;

            }

            //以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上
            if (((ALat >= dLat1) && (ALat < dLat2)) || ((ALat >= dLat2) && (ALat < dLat1))) {
                if (Math.abs(dLat1 - dLat2) > 0) {
                    //得到 A点向左射线与边的交点的x坐标：
                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - ALat)) / (dLat1 - dLat2);

                    // 如果交点在A点左侧（说明是做射线与 边的交点），则射线与边的全部交点数加一：
                    if (dLon < ALon)
                        iSum++;
                }
            }
        }
        if (iSum % 2 != 0) {
            return true;
        }

        return false;
    }


    public static class Points {
        //lngX
        public final double lngX;
        //laty
        public final double latY;

        public Points(double lngX, double latY) {
            this.lngX = lngX;
            this.latY = latY;
        }
    }
}