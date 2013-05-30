package com.orange.contextviewer.dao;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: throdo
 * Date: 20/05/13
 * Time: 10:50
 */
public class ClustersDAOTest {

    private ClustersDAO clustersDAO = null;

    @Before
    public void setUp() throws Exception {

        this.clustersDAO = new ClustersDAO();
    }

    @After
    public void tearDown() throws Exception {
        this.clustersDAO = null;

    }

    @Test
    public void testGetClusterList() throws Exception {
        Assert.assertNotNull(this.clustersDAO);
    }

    @Test
    public void testSetClusterList() throws Exception {

    }
}
