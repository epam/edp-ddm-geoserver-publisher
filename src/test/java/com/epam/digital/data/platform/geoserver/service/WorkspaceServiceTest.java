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
import com.epam.digital.data.platform.geoserver.model.GetWorkspaceResponse;
import com.epam.digital.data.platform.geoserver.model.SettingsYaml.General;
import com.epam.digital.data.platform.geoserver.model.SettingsYaml.Settings;
import com.epam.digital.data.platform.geoserver.model.WorkspaceInfo;
import com.epam.digital.data.platform.geoserver.model.WorkspaceWrapper;

import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WorkspaceServiceTest {

  private static final String REGISTER_NAME = "register";

  private WorkspaceService service;

  @Mock private GeoserverFeignClient geoserverFeignClient;

  @BeforeEach
  void beforeEach() {
    service = new WorkspaceService(geoserverFeignClient);
  }

  @Test
  void expectWorkspaceIsCreatedIfNotExists() {
    when(geoserverFeignClient.getWorkspace(REGISTER_NAME, true))
        .thenThrow(FeignException.NotFound.class).thenReturn(new GetWorkspaceResponse());

    var settings = new Settings();
    settings.setGeneral(new General());
    settings.getGeneral().setRegister(REGISTER_NAME);

    var actual = service.create(settings);

    assertThat(actual.getName()).isEqualTo(REGISTER_NAME);

    ArgumentCaptor<WorkspaceWrapper> workspaceCaptor = ArgumentCaptor.forClass(WorkspaceWrapper.class);
    verify(geoserverFeignClient).createWorkspace(workspaceCaptor.capture(), eq(false));
    assertThat(workspaceCaptor.getValue().getWorkspace().getName()).isEqualTo(REGISTER_NAME);
  }

  @Test
  void expectWorkspaceIsUpdatedIfAlreadyExists() {
    when(geoserverFeignClient.getWorkspace(REGISTER_NAME, true))
        .thenReturn(new GetWorkspaceResponse());

    var settings = new Settings();
    settings.setGeneral(new General());
    settings.getGeneral().setRegister(REGISTER_NAME);

    var actual = service.create(settings);

    assertThat(actual.getName()).isEqualTo(REGISTER_NAME);

    ArgumentCaptor<WorkspaceWrapper> workspaceCaptor = ArgumentCaptor.forClass(WorkspaceWrapper.class);
    verify(geoserverFeignClient).modifyWorkspace(eq(REGISTER_NAME), workspaceCaptor.capture());
    assertThat(workspaceCaptor.getValue().getWorkspace().getName()).isEqualTo(REGISTER_NAME);
  }
}
