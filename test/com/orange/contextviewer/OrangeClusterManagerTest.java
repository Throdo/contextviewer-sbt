package com.orange.contextviewer;

import junit.framework.Assert;
import models.OrangeCluster;
import models.OrangeClusterManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 20/05/13
 * Time: 09:00
 */
public class OrangeClusterManagerTest {

    private static OrangeClusterManager orangeClusterManager;
    private static OrangeCluster orangeCluster;

    @BeforeClass
    public static void setUp() throws Exception {
        OrangeClusterManagerTest.orangeClusterManager = new OrangeClusterManager();
        OrangeClusterManagerTest.orangeCluster = new OrangeCluster("localhostTest", "OrangeCluster local virtuel pour les tests jUnit", "http://localhost:8091/pools/", "Administrator", "Administrator");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        OrangeClusterManagerTest.orangeClusterManager = null;
    }

    @Test
    public void testGetClusterMap() throws Exception {
        Assert.assertNotNull(OrangeClusterManagerTest.orangeClusterManager.getClusterMap());
    }

    @Test
    public void testSetClusterMap() throws Exception {
        OrangeClusterManager newOrangeClusterManager = new OrangeClusterManager();
        Map<String, OrangeCluster> clusterMap = new HashMap<String, OrangeCluster>();
        newOrangeClusterManager.setClusterMap(clusterMap);
        Assert.assertEquals(clusterMap, newOrangeClusterManager.getClusterMap());
    }

    @Test
    public void testEquals() throws Exception {
        OrangeClusterManager newOrangeClusterManager = new OrangeClusterManager();
        Map<String, OrangeCluster> clusterMap = new HashMap<String, OrangeCluster>();
        newOrangeClusterManager.setClusterMap(clusterMap);

        Assert.assertTrue(OrangeClusterManagerTest.orangeClusterManager.equals(OrangeClusterManagerTest.orangeClusterManager));
        Assert.assertFalse(OrangeClusterManagerTest.orangeClusterManager.equals(newOrangeClusterManager));
    }

    @Test
    public void testHashCode() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }
}
