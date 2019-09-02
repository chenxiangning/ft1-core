package com.reachauto.hkr.tennis.notscan.sf;

import java.math.BigDecimal;
import java.util.LinkedList;
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
     * @param ALon              1
     * @param ALat              2
     * @param jingWeiDuJiHeList 3
     * @return b
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

    public static void main(String[] args) {
        System.out.println(straightLine(new Points(123.433684, 41.704365), new Points(123.434376, 41.70213), 5));
    }

    public final static List<Points> straightLine(Points pointsa, Points pointsb, int duanshu) {
        duanshu = duanshu + 1;
        boolean xbs = true;
        double xdengfen;
        double[] x;
        if (pointsa.lngX > pointsb.lngX) {
            xdengfen = pointsa.lngX - pointsb.lngX;
            double df = xdengfen / duanshu;

            x = new double[duanshu];

            for (int i = 0; i < duanshu; i++) {
                x[i] = new BigDecimal(pointsb.lngX + (i + 1) * df).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();

            }

            double[] temp = new double[duanshu];
            for (int i = 0; i < duanshu; i++) {
                temp[i] = x[x.length - i - 1];
            }
            x = temp;

        } else {
            xdengfen = pointsb.lngX - pointsa.lngX;
            double df = xdengfen / duanshu;

            x = new double[duanshu];

            for (int i = 0; i < duanshu; i++) {

                x[i] = new BigDecimal(pointsa.lngX + (i + 1) * df).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            xbs = false;
        }

        ////////
        boolean ybs = true;
        double ydengfen;
        double[] y;
        if (pointsa.latY > pointsb.latY) {
            ydengfen = pointsa.latY - pointsb.latY;
            double df = ydengfen / duanshu;

            y = new double[duanshu];

            for (int i = 0; i < duanshu; i++) {

                y[i] = new BigDecimal(pointsb.latY + (i + 1) * df).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
            }

            double[] temp = new double[duanshu];
            for (int i = 0; i < duanshu; i++) {
                temp[i] = y[y.length - i - 1];
            }
            y = temp;

        } else {
            ydengfen = pointsb.latY - pointsa.latY;
            double df = ydengfen / duanshu;

            y = new double[duanshu];

            for (int i = 0; i < duanshu; i++) {

                y[i] = new BigDecimal(pointsa.latY + (i + 1) * df).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            ybs = false;
        }

        LinkedList<Points> points = new LinkedList<>();

        for (int i = 0; i < duanshu; i++) {
            points.add(new Points(x[i], y[i]));
        }

        return points;
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

        @Override
        public String toString() {
            return String.format("[%s,%s]", lngX, latY);
        }
    }
}