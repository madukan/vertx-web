/*
 * Copyright 2024 Red Hat, Inc.
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
open module io.vertx.web.client.tests {
  requires static org.hamcrest;
  requires io.netty.codec;
  requires io.netty.codec.http;
  requires io.vertx.auth.htdigest;
  requires io.vertx.core.tests;
  requires io.vertx.auth.oauth2;
  requires io.vertx.uritemplate;
  requires io.vertx.testing.unit;
  requires io.vertx.web.client;
  requires io.vertx.web.common;
  requires junit;
}
