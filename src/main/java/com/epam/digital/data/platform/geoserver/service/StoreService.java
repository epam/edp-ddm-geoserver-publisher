/*
 * Copyright 2022 EPAM Systems.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.digital.data.platform.geoserver.service;

import org.geoserver.openapi.model.catalog.DataStoreInfo;
import org.geoserver.openapi.model.catalog.WorkspaceInfo;
import org.geoserver.openapi.v1.model.DataStoreResponse;
import org.geoserver.restconfig.client.DataStoresClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StoreService {

  private final DataStoresClient dataStoresClient;
  private final Map<String, String> storeConnectionParameters;

  public StoreService(
      DataStoresClient dataStoresClient,
      Map<String, String> storeConnectionParameters) {
    this.dataStoresClient = dataStoresClient;
    this.storeConnectionParameters = storeConnectionParameters;
  }

  public DataStoreResponse create(WorkspaceInfo workspaceInfo) {
    DataStoreInfo dataStoreInfo =
        new DataStoreInfo()
            .enabled(true)
            .name(workspaceInfo.getName())
            .workspace(workspaceInfo)
            .connectionParameters(storeConnectionParameters);
    DataStoreResponse dataStore;
    if (dataStoresClient.findByWorkspaceAndName(workspaceInfo.getName(), workspaceInfo.getName()).isPresent()) {
      dataStore = dataStoresClient.update(workspaceInfo.getName(), dataStoreInfo);
    } else {
      dataStore = dataStoresClient.create(workspaceInfo.getName(), dataStoreInfo);
    }
    return dataStore;
  }
}
