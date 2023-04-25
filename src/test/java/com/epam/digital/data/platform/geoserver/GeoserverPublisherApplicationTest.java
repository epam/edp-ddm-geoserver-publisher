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

package com.epam.digital.data.platform.geoserver;

import com.epam.digital.data.platform.geoserver.config.GeneralConfig;
import com.epam.digital.data.platform.geoserver.model.SettingsYaml.Settings;
import com.epam.digital.data.platform.geoserver.service.GeoMetadataService;
import com.epam.digital.data.platform.geoserver.service.LayersService;
import com.epam.digital.data.platform.geoserver.service.StoreService;
import com.epam.digital.data.platform.geoserver.service.WorkspaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geoserver.openapi.model.catalog.WorkspaceInfo;
import org.geoserver.openapi.v1.model.DataStoreResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {GeneralConfig.class})
class GeoserverPublisherApplicationTest {

  private GeoserverPublisherApplication application;

  @Autowired
  private ObjectMapper yamlObjectMapper;
  @MockBean
  private WorkspaceService workspaceService;
  @MockBean
  private StoreService storeService;
  @MockBean
  private LayersService layersService;
  @MockBean
  private GeoMetadataService metadataService;

  @BeforeEach
  void beforeEach() {
    application =
        new GeoserverPublisherApplication(
            yamlObjectMapper, workspaceService, storeService, layersService, metadataService);
  }

  @Test
  void expectSettingsAreProcessedByApp() throws URISyntaxException, IOException {
    var workspace = new WorkspaceInfo();
    when(workspaceService.create(any())).thenReturn(workspace);
    var dataStore = new DataStoreResponse();
    when(storeService.create(workspace))
            .thenReturn(dataStore);

    application.run(
        new DefaultApplicationArguments(
            String.format("--settings-file=%s", getFile("settings.yaml"))));

    ArgumentCaptor<Settings> settingsCaptor = ArgumentCaptor.forClass(Settings.class);
    verify(workspaceService).create(settingsCaptor.capture());
    assertThat(settingsCaptor.getValue().getGeneral().getRegister()).isEqualTo("register");
    verify(storeService).create(workspace);
    verify(layersService).create(dataStore);
  }

  private Path getFile(String resourceName) throws URISyntaxException {
    var classLoader = getClass().getClassLoader();
    return Paths.get(Objects.requireNonNull(classLoader.getResource(resourceName)).toURI());
  }
}
