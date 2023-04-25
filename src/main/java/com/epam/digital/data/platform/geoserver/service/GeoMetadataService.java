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

import com.epam.digital.data.platform.geoserver.repository.GeoMetadataRepository;
import com.epam.digital.data.platform.geoserver.utils.TableUtils;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import schemacrawler.schema.Catalog;
import schemacrawler.schema.NamedObject;
import schemacrawler.schema.Table;

@Service
public class GeoMetadataService {

  private final Catalog catalog;
  private final GeoMetadataRepository repository;

  public GeoMetadataService(GeoMetadataRepository repository, Catalog catalog) {
    this.repository = repository;
    this.catalog = catalog;
  }

  public void processMetadata() {
    repository.deleteGeoMetadata();

    var tablesWithGeometry =
        catalog.getTables().stream()
            .filter(TableUtils::isView)
            .filter(TableUtils::isTableContainingGeometryColumn)
            .collect(Collectors.toList());

    tablesWithGeometry.forEach(table -> {
      final String tableNameToGetPk = repository.getTableNameLinkedWithSC(table.getName(),
          TableUtils.getColumnWithGeometry(table));
      final Optional<Table> tableByName = getTableByName(tableNameToGetPk);

      tableByName.ifPresent(
          tbl -> repository.saveMetadata(table.getName(), getPrimaryKeyFromTable(tbl)));
    });
  }


  private Optional<Table> getTableByName(String tableName) {
    return catalog.getTables().stream()
        .filter(c -> c.getName().equals(tableName))
        .findFirst();
  }

  private String getPrimaryKeyFromTable(Table table) {
    return table.getPrimaryKey()
        .getColumns().stream()
        .map(NamedObject::getName)
        .findFirst()
        .orElse(null);
  }
}
