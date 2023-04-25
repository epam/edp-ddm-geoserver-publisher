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

package com.epam.digital.data.platform.geoserver;

import com.epam.digital.data.platform.geoserver.cli.CommandLineArg;
import com.epam.digital.data.platform.geoserver.model.SettingsYaml;
import com.epam.digital.data.platform.geoserver.service.GeoMetadataService;
import com.epam.digital.data.platform.geoserver.service.LayersService;
import com.epam.digital.data.platform.geoserver.service.StoreService;
import com.epam.digital.data.platform.geoserver.service.WorkspaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@SpringBootApplication
public class GeoserverPublisherApplication implements ApplicationRunner {

  private final ObjectMapper yamlObjectMapper;
  private final WorkspaceService workspaceService;
  private final StoreService storeService;

  private final LayersService layersService;
  private final GeoMetadataService metadataService;

  public GeoserverPublisherApplication(
      ObjectMapper yamlObjectMapper,
      WorkspaceService workspaceService,
      StoreService storeService,
      LayersService layersService,
      GeoMetadataService metadataService) {
    this.yamlObjectMapper = yamlObjectMapper;
    this.workspaceService = workspaceService;
    this.storeService = storeService;
    this.layersService = layersService;
    this.metadataService = metadataService;
  }

  public static void main(String[] args) {
    SpringApplication.run(GeoserverPublisherApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws IOException {
    var settings =
        yamlObjectMapper
            .readValue(getFile(args, CommandLineArg.SETTINGS), SettingsYaml.class)
            .getSettings();
    var workspace = workspaceService.create(settings);
    var dataStore = storeService.create(workspace);
    metadataService.processMetadata();
    layersService.create(dataStore);
  }

  private File getFile(ApplicationArguments args, CommandLineArg argumentName) {
    var argumentValue = args.getOptionValues(argumentName.getArgOptionName());
    return Optional.ofNullable(argumentValue)
        .filter(argument -> argument.size() == 1)
        .map(argument -> argument.get(0))
        .map(File::new)
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    String.format(
                        "Cannot get file value for argument %s from value %s",
                        argumentName.getArgOptionName(), argumentValue)));
  }
}
