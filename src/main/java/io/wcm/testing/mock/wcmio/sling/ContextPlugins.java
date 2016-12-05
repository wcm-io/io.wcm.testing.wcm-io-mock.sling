/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2016 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.testing.mock.wcmio.sling;

import org.apache.sling.testing.mock.osgi.context.AbstractContextPlugin;
import org.apache.sling.testing.mock.osgi.context.ContextPlugin;

import com.google.common.collect.ImmutableMap;

import io.wcm.sling.commons.request.RequestContext;
import io.wcm.sling.models.injectors.impl.AemObjectInjector;
import io.wcm.sling.models.injectors.impl.ModelsImplConfiguration;
import io.wcm.sling.models.injectors.impl.SlingObjectOverlayInjector;
import io.wcm.testing.mock.aem.junit.AemContext;

/**
 * Mock context plugins.
 */
public final class ContextPlugins {

  private ContextPlugins() {
    // constants only
  }

  /**
   * Context plugin for wcm.io Sling Extensions.
   */
  public static final ContextPlugin<AemContext> WCMIO_SLING = new AbstractContextPlugin<AemContext>() {
    @Override
    public void afterSetUp(AemContext context) throws Exception {
      setUp(context);
    }
  };

  /**
   * Set up request context and Sling Models Extensions.
   * @param context Aem context
   */
  static void setUp(AemContext context) {

    // register request context
    context.registerService(RequestContext.class, new MockRequestContext());

    // register sling models extensions
    context.registerInjectActivateService(new ModelsImplConfiguration(),
        ImmutableMap.<String, Object>of("requestThreadLocal", true));

    context.registerInjectActivateService(new AemObjectInjector());
    context.registerInjectActivateService(new SlingObjectOverlayInjector());
  }

}
