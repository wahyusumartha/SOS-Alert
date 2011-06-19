package com.appspot.sosalert;

//Copyright 2010 Google Inc. All Rights Reserved.


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;


public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
