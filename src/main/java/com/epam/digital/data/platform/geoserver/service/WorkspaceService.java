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
import com.epam.digital.data.platform.geoserver.model.GetWorkspaceResponse;
import com.epam.digital.data.platform.geoserver.model.SettingsYaml.Settings;
import com.epam.digital.data.platform.geoserver.model.WorkspaceInfo;
import com.epam.digital.data.platform.geoserver.model.WorkspaceWrapper;
import feign.FeignException;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceService {

  private final GeoserverFeignClient geoserverFeignClient;

  public WorkspaceService(GeoserverFeignClient geoserverFeignClient) {
    this.geoserverFeignClient = geoserverFeignClient;
  }

  public WorkspaceInfo create(Settings settings) {
    var workspaceName = settings.getGeneral().getRegister();
    var workspaceInfo = new WorkspaceInfo().name(workspaceName);
    Optional<GetWorkspaceResponse> existingWorkspace;
    try {
      existingWorkspace = Optional.of(geoserverFeignClient.getWorkspace(workspaceName, true));
    } catch (FeignException.NotFound ex) {
      existingWorkspace = Optional.empty();
    }
    if (existingWorkspace.isPresent()) {
      geoserverFeignClient.modifyWorkspace(workspaceName, (new WorkspaceWrapper()).workspace(workspaceInfo));
    } else {
      geoserverFeignClient.createWorkspace((new WorkspaceWrapper()).workspace(workspaceInfo), false);
    }
    return workspaceInfo;
  }
}
