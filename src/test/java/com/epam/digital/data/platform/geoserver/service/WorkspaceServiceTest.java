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

import com.epam.digital.data.platform.geoserver.model.SettingsYaml.General;
import com.epam.digital.data.platform.geoserver.model.SettingsYaml.Settings;
import org.geoserver.openapi.model.catalog.WorkspaceInfo;
import org.geoserver.openapi.v1.model.WorkspaceSummary;
import org.geoserver.restconfig.client.WorkspacesClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkspaceServiceTest {

  private static final String REGISTER_NAME = "register";

  private WorkspaceService service;

  @Mock
  private WorkspacesClient workspacesClient;

  @BeforeEach
  void beforeEach() {
    service = new WorkspaceService(workspacesClient);
  }

  @Test
  void expectWorkspaceIsCreatedIfNotExists() {
    when(workspacesClient.findByName(REGISTER_NAME)).thenReturn(Optional.empty());

    var settings = new Settings();
    settings.setGeneral(new General());
    settings.getGeneral().setRegister(REGISTER_NAME);

    var actual = service.create(settings);

    assertThat(actual.getName()).isEqualTo(REGISTER_NAME);

    ArgumentCaptor<WorkspaceInfo> workspaceCaptor = ArgumentCaptor.forClass(WorkspaceInfo.class);
    verify(workspacesClient).create(workspaceCaptor.capture());
    assertThat(workspaceCaptor.getValue().getName()).isEqualTo(REGISTER_NAME);
  }

  @Test
  void expectWorkspaceIsUpdatedIfAlreadyExists() {
    when(workspacesClient.findByName(REGISTER_NAME))
        .thenReturn(Optional.of(new WorkspaceSummary()));

    var settings = new Settings();
    settings.setGeneral(new General());
    settings.getGeneral().setRegister(REGISTER_NAME);

    var actual = service.create(settings);

    assertThat(actual.getName()).isEqualTo(REGISTER_NAME);

    ArgumentCaptor<WorkspaceInfo> workspaceCaptor = ArgumentCaptor.forClass(WorkspaceInfo.class);
    verify(workspacesClient).update(eq(REGISTER_NAME), workspaceCaptor.capture());
    assertThat(workspaceCaptor.getValue().getName()).isEqualTo(REGISTER_NAME);
  }
}
