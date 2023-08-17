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

package com.epam.digital.data.platform.geoserver.client;

import com.epam.digital.data.platform.geoserver.model.DataStoreInfoWrapper;
import com.epam.digital.data.platform.geoserver.model.DataStoreWrapper;
import com.epam.digital.data.platform.geoserver.model.FeatureTypeInfoWrapper;
import com.epam.digital.data.platform.geoserver.model.FeatureTypeResponseWrapper;
import com.epam.digital.data.platform.geoserver.model.GetWorkspaceResponse;
import com.epam.digital.data.platform.geoserver.model.WorkspaceWrapper;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geoserver-client", url = "${geoserver.url}/geoserver/rest")
public interface GeoserverFeignClient {

  @GetMapping(value = "/workspaces/{workspace}", produces = "application/json")
  GetWorkspaceResponse getWorkspace(
      @PathVariable("workspace") String workspace,
      @RequestParam("quietOnNotFound") Boolean quietOnNotFound);

  @PostMapping("/workspaces")
  void createWorkspace(
      @RequestBody WorkspaceWrapper workspaceWrapper, @RequestParam("default") Boolean _default);

  @PutMapping("/workspaces/{workspace}")
  void modifyWorkspace(
      @PathVariable("workspace") String workspace, @RequestBody WorkspaceWrapper workspaceWrapper);

  @GetMapping(
      value = "/workspaces/{workspace}/datastores/{storeName}",
      produces = "application/json")
  DataStoreWrapper getDataStore(
      @PathVariable("workspace") String workspace,
      @PathVariable("storeName") String storeName,
      @RequestParam("quietOnNotFound") Boolean quietOnNotFound);

  @PostMapping("/workspaces/{workspace}/datastores")
  void createDatastore(
      @PathVariable("workspace") String workspace,
      @RequestBody DataStoreInfoWrapper dataStoreInfoWrapper);

  @PutMapping("/workspaces/{workspace}/datastores/{storeName}")
  void modifyDataStore(
      @PathVariable("workspace") String workspace,
      @PathVariable("storeName") String storeName,
      @RequestBody DataStoreInfoWrapper dataStoreInfoWrapper);

  @GetMapping(
      value = "/workspaces/{workspace}/datastores/{storeName}/featuretypes/{featureTypeName}.",
      produces = "application/json")
  FeatureTypeResponseWrapper getFeatureType(
      @PathVariable("workspace") String workspace,
      @PathVariable("storeName") String storeName,
      @PathVariable("featureTypeName") String featureTypeName,
      @RequestParam("quietOnNotFound") Boolean quietOnNotFound);

  @PostMapping("/workspaces/{workspace}/datastores/{storeName}/featuretypes")
  void createFeatureTypeOnStore(
      @PathVariable("workspace") String workspace,
      @PathVariable("storeName") String storeName,
      @RequestBody FeatureTypeInfoWrapper featureTypeInfoWrapper);

  @PutMapping("/workspaces/{workspace}/datastores/{storeName}/featuretypes/{featureTypeName}.")
  void modifyFeatureTypeByStore(
      @PathVariable("workspace") String workspace,
      @PathVariable("storeName") String storeName,
      @PathVariable("featureTypeName") String featureTypeName,
      @RequestBody FeatureTypeInfoWrapper featureTypeInfoWrapper,
      @RequestParam("recalculate") List<String> recalculate);
}
