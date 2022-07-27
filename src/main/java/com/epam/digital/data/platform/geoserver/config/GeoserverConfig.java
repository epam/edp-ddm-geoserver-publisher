/*
 * Copyright 2022 EPAM Systems.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.digital.data.platform.geoserver.config;

import com.epam.digital.data.platform.geoserver.config.properties.GeoserverConfigProperties;
import com.epam.digital.data.platform.geoserver.config.properties.StoreConfigProperties;
import org.geoserver.restconfig.client.DataStoresClient;
import org.geoserver.restconfig.client.FeatureTypesClient;
import org.geoserver.restconfig.client.GeoServerClient;
import org.geoserver.restconfig.client.WorkspacesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GeoserverConfig {

  private static final String GEOSERVER_REST_SUFFIX = "/geoserver/rest";

  @Bean
  public GeoServerClient geoServerClient(GeoserverConfigProperties geoserverConfigProperties) {
    return new GeoServerClient()
        .setBasePath(geoserverConfigProperties.getUrl() + GEOSERVER_REST_SUFFIX)
        .setBasicAuth(
            geoserverConfigProperties.getLogin(), geoserverConfigProperties.getPassword());
  }

  @Bean
  public WorkspacesClient workspacesClient(GeoServerClient geoServerClient) {
    return geoServerClient.workspaces();
  }

  @Bean
  public DataStoresClient dataStoresClient(GeoServerClient geoServerClient) {
    return geoServerClient.dataStores();
  }

  @Bean
  public Map<String, String> storeConnectionParameters(
      StoreConfigProperties storeConfigProperties) {
    Map<String, String> params = new HashMap<>();
    params.put("host", storeConfigProperties.getHost());
    params.put("port", storeConfigProperties.getPort());
    params.put("database", storeConfigProperties.getDatabase());
    params.put("schema", storeConfigProperties.getSchema());
    params.put("user", storeConfigProperties.getUser());
    params.put("passwd", storeConfigProperties.getPasswd());
    params.put("dbtype", storeConfigProperties.getDbtype());
    return params;
  }

  @Bean
  public FeatureTypesClient featureTypesClient(GeoServerClient geoServerClient) {
    return geoServerClient.featureTypes();
  }
}
