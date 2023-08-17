/*
 * Copyright 2023 EPAM Systems.
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

import com.epam.digital.data.platform.geoserver.client.GeoserverFeignClient;
import com.epam.digital.data.platform.geoserver.model.DataStoreInfo;
import com.epam.digital.data.platform.geoserver.model.DataStoreInfoWrapper;
import com.epam.digital.data.platform.geoserver.model.DataStoreResponse;
import com.epam.digital.data.platform.geoserver.model.DataStoreWrapper;
import com.epam.digital.data.platform.geoserver.model.WorkspaceInfo;
import feign.FeignException;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class StoreService {

  private final GeoserverFeignClient geoserverFeignClient;
  private final Map<String, String> storeConnectionParameters;

  public StoreService(
      GeoserverFeignClient geoserverFeignClient, Map<String, String> storeConnectionParameters) {
    this.geoserverFeignClient = geoserverFeignClient;
    this.storeConnectionParameters = storeConnectionParameters;
  }

  public DataStoreResponse create(WorkspaceInfo workspaceInfo) {
    DataStoreInfo dataStoreInfo =
        new DataStoreInfo()
            .enabled(true)
            .name(workspaceInfo.getName())
            .workspace(workspaceInfo)
            .connectionParameters(storeConnectionParameters);
    Optional<DataStoreWrapper> existingDataStore;
    try {
      existingDataStore = Optional.of(geoserverFeignClient.getDataStore(workspaceInfo.getName(), workspaceInfo.getName(), true));
    } catch (FeignException.NotFound ex) {
      existingDataStore = Optional.empty();
    }
    if (existingDataStore.isPresent()) {
      geoserverFeignClient.modifyDataStore(workspaceInfo.getName(), dataStoreInfo.getName(), (new DataStoreInfoWrapper()).dataStore(dataStoreInfo));
    } else {
      geoserverFeignClient.createDatastore(workspaceInfo.getName(), (new DataStoreInfoWrapper()).dataStore(dataStoreInfo));
    }
    var dataStore = geoserverFeignClient.getDataStore(workspaceInfo.getName(), workspaceInfo.getName(), true).getDataStore();
    return dataStore;
  }
}
