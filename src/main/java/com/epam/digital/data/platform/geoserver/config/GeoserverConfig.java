/*
 * Copyright 2023 EPAM Systems.
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

import com.epam.digital.data.platform.geoserver.config.properties.StoreConfigProperties;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeoserverConfig {

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
    params.put("Primary key metadata table", "public.ddm_geoserver_pk_metadata");
    params.put("Expose primary keys", Boolean.TRUE.toString());
    return params;
  }
}
