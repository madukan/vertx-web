/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.ext.web.tests.handler;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.tests.WebTestBase;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 *
 * Kind of hard to test this!
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class LoggerHandlerTest extends WebTestBase {

  @Override
  public void setUp() throws Exception {
    super.setUp();

  }

  @Test
  public void testLogger1() throws Exception {
    LoggerHandler logger = LoggerHandler.create();
    testLogger(logger);
  }

  @Test
  public void testLoggerCombined() throws Exception {
    LoggerHandler logger = LoggerHandler.create(LoggerFormat.COMBINED);
    testLogger(logger);
  }

  @Test
  public void testLogger2() throws Exception {
    LoggerHandler logger = LoggerHandler.create(LoggerFormat.TINY);
    testLogger(logger);
  }

  @Test
  public void testLogger3() throws Exception {
    LoggerHandler logger = LoggerHandler.create(true, LoggerFormat.TINY);
    testLogger(logger);
  }

  @Test
  public void testLogger4() throws Exception {
    final CountDownLatch latch = new CountDownLatch(1);
    LoggerHandler logger = LoggerHandler.create(true, LoggerFormat.CUSTOM).customFormatter((req, ms) -> {
      latch.countDown();
      return "custom log message";
    });
    testLogger(logger);
    latch.await();
  }

  @Test
  public void testLogger5() throws Exception {
    final CountDownLatch latch = new CountDownLatch(1);
    LoggerHandler logger = LoggerHandler.create(true, LoggerFormat.CUSTOM).customFormatter((ctx, ms) -> {
      latch.countDown();
      return "custom log message";
    });
    testLogger(logger);
    latch.await();
  }

  private void testLogger(LoggerHandler logger) throws Exception {
    router.route().handler(logger);
    router.route().handler(rc -> rc.response().end());
    testRequest(HttpMethod.GET, "/somedir", 200, "OK");
  }


}
