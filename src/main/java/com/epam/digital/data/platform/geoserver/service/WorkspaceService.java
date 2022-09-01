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

import com.epam.digital.data.platform.geoserver.model.SettingsYaml.Settings;
import org.geoserver.openapi.model.catalog.WorkspaceInfo;
import org.geoserver.restconfig.client.WorkspacesClient;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceService {

  private final WorkspacesClient workspacesClient;

  public WorkspaceService(WorkspacesClient workspacesClient) {
    this.workspacesClient = workspacesClient;
  }

  public WorkspaceInfo create(Settings settings) {
    var workspaceName = settings.getGeneral().getRegister();
    var workspaceInfo = new WorkspaceInfo().name(workspaceName);
    if (workspacesClient.findByName(workspaceName).isPresent()) {
      workspacesClient.update(workspaceName, workspaceInfo);
    } else {
      workspacesClient.create(workspaceInfo);
    }
    return workspaceInfo;
  }
}
