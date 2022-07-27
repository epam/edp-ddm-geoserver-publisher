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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

  private static final String REGISTER_NAME = "register";

  private StoreService service;

  @Mock
  private DataStoresClient dataStoresClient;
  private final Map<String, String> storeConnectionParameters = Map.of("key", "value");

  @BeforeEach
  void beforeEach() {
    service = new StoreService(dataStoresClient, storeConnectionParameters);
  }

  @Test
  void expectStoreIsCreatedIfNotExists() {
    when(dataStoresClient.findByWorkspaceAndName(REGISTER_NAME, REGISTER_NAME))
            .thenReturn(Optional.empty());

    var workspace = new WorkspaceInfo().name(REGISTER_NAME);

    service.create(workspace);

    ArgumentCaptor<DataStoreInfo> dataStoreCaptor = ArgumentCaptor.forClass(DataStoreInfo.class);
    verify(dataStoresClient).create(eq(REGISTER_NAME), dataStoreCaptor.capture());
    var actualDataStore = dataStoreCaptor.getValue();
    assertThat(actualDataStore.getEnabled()).isTrue();
    assertThat(actualDataStore.getName()).isEqualTo(REGISTER_NAME);
    assertThat(actualDataStore.getWorkspace()).isEqualTo(workspace);
    assertThat(actualDataStore.getConnectionParameters()).isEqualTo(storeConnectionParameters);
  }

  @Test
  void expectStoreIsUpdatedIfAlreadyExists() {
    when(dataStoresClient.findByWorkspaceAndName(REGISTER_NAME, REGISTER_NAME))
            .thenReturn(Optional.of(new DataStoreResponse()));

    var workspace = new WorkspaceInfo().name(REGISTER_NAME);

    service.create(workspace);

    ArgumentCaptor<DataStoreInfo> dataStoreCaptor = ArgumentCaptor.forClass(DataStoreInfo.class);
    verify(dataStoresClient).update(eq(REGISTER_NAME), dataStoreCaptor.capture());
    var actualDataStore = dataStoreCaptor.getValue();
    assertThat(actualDataStore.getEnabled()).isTrue();
    assertThat(actualDataStore.getName()).isEqualTo(REGISTER_NAME);
    assertThat(actualDataStore.getWorkspace()).isEqualTo(workspace);
    assertThat(actualDataStore.getConnectionParameters()).isEqualTo(storeConnectionParameters);
  }
}
