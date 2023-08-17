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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.digital.data.platform.geoserver.client.GeoserverFeignClient;
import com.epam.digital.data.platform.geoserver.model.DataStoreInfoWrapper;
import com.epam.digital.data.platform.geoserver.model.DataStoreWrapper;
import com.epam.digital.data.platform.geoserver.model.WorkspaceInfo;
import feign.FeignException;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

  private static final String REGISTER_NAME = "register";

  private StoreService service;

  @Mock private GeoserverFeignClient geoserverFeignClient;
  private final Map<String, String> storeConnectionParameters = Map.of("key", "value");

  @BeforeEach
  void beforeEach() {
    service = new StoreService(geoserverFeignClient, storeConnectionParameters);
  }

  @Test
  void expectStoreIsCreatedIfNotExists() {
    when(geoserverFeignClient.getDataStore(REGISTER_NAME, REGISTER_NAME, true))
        .thenThrow(FeignException.NotFound.class).thenReturn(new DataStoreWrapper());

    var workspace = new WorkspaceInfo().name(REGISTER_NAME);

    service.create(workspace);

    ArgumentCaptor<DataStoreInfoWrapper> dataStoreCaptor =
        ArgumentCaptor.forClass(DataStoreInfoWrapper.class);
    verify(geoserverFeignClient).createDatastore(eq(REGISTER_NAME), dataStoreCaptor.capture());
    var actualDataStore = dataStoreCaptor.getValue();
    assertThat(actualDataStore.getDataStore().getEnabled()).isTrue();
    assertThat(actualDataStore.getDataStore().getName()).isEqualTo(REGISTER_NAME);
    assertThat(actualDataStore.getDataStore().getWorkspace()).isEqualTo(workspace);
    assertThat(actualDataStore.getDataStore().getConnectionParameters())
        .isEqualTo(storeConnectionParameters);
  }

  @Test
  void expectStoreIsUpdatedIfAlreadyExists() {
    when(geoserverFeignClient.getDataStore(REGISTER_NAME, REGISTER_NAME, true))
        .thenReturn(new DataStoreWrapper());

    var workspace = new WorkspaceInfo().name(REGISTER_NAME);

    service.create(workspace);

    ArgumentCaptor<DataStoreInfoWrapper> dataStoreCaptor =
        ArgumentCaptor.forClass(DataStoreInfoWrapper.class);
    verify(geoserverFeignClient)
        .modifyDataStore(eq(REGISTER_NAME), eq(REGISTER_NAME), dataStoreCaptor.capture());
    var actualDataStore = dataStoreCaptor.getValue();
    assertThat(actualDataStore.getDataStore().getEnabled()).isTrue();
    assertThat(actualDataStore.getDataStore().getName()).isEqualTo(REGISTER_NAME);
    assertThat(actualDataStore.getDataStore().getWorkspace()).isEqualTo(workspace);
    assertThat(actualDataStore.getDataStore().getConnectionParameters())
        .isEqualTo(storeConnectionParameters);
  }
}
