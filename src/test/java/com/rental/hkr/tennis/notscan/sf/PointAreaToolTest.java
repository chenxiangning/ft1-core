package com.rental.hkr.tennis.notscan.sf;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PointAreaToolTest {
    @Test
    public void isPtInPoly() throws Exception {
        List l = Arrays.asList(
                new PointAreaTool.Points(123.407123, 41.768606),
                new PointAreaTool.Points(123.381717, 41.743763),
                new PointAreaTool.Points(123.434932, 41.741458)
        );

        List ll = Arrays.asList(
                new PointAreaTool.Points(123.309282, 41.756102),
                new PointAreaTool.Points(123.309282, 41.768138),
                new PointAreaTool.Points(123.333658, 41.769162),
                new PointAreaTool.Points(123.35151, 41.763273),
                new PointAreaTool.Points(123.340524, 41.75687),
                new PointAreaTool.Points(123.348421, 41.745344)
        );


        Assert.assertEquals(true, PointAreaTool.isPtInPoly(123.402317, 41.750935, l));
        Assert.assertEquals(true, PointAreaTool.isPtInPoly(123.41399, 41.757851, l));
        Assert.assertEquals(false, PointAreaTool.isPtInPoly(123.410203, 41.74233, l));
        Assert.assertEquals(false, PointAreaTool.isPtInPoly(123.406024, 41.742662, l));

        Assert.assertEquals(true, PointAreaTool.isPtInPoly(123.311599, 41.766602, ll));
        Assert.assertEquals(true, PointAreaTool.isPtInPoly(123.326019, 41.760712, ll));
        Assert.assertEquals(true, PointAreaTool.isPtInPoly(123.344386, 41.76052, ll));

        Assert.assertEquals(false, PointAreaTool.isPtInPoly(123.342327, 41.75623, ll));
        Assert.assertEquals(false, PointAreaTool.isPtInPoly(123.349536, 41.758663, ll));
        Assert.assertEquals(false, PointAreaTool.isPtInPoly(123.309797, 41.753541, ll));

    }

}
