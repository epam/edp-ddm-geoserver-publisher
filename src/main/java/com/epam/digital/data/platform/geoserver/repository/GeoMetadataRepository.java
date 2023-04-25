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

package com.epam.digital.data.platform.geoserver.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GeoMetadataRepository {

  static final String SELECT_QUERY = "SELECT change_name FROM public.ddm_liquibase_metadata WHERE change_type=? and attribute_name=?";
  static final String INSERT_QUERY = "INSERT INTO public.ddm_geoserver_pk_metadata (table_schema, table_name, pk_column) VALUES(?,?,?)";
  private final String storeSchema;
  private final JdbcTemplate jdbcTemplate;

  public GeoMetadataRepository(JdbcTemplate jdbcTemplate,
      @Value("${geoserver.store.schema}") String storeSchema) {
    this.jdbcTemplate = jdbcTemplate;
    this.storeSchema = storeSchema;
  }

  public void saveMetadata(String table, String column) {
    if (column != null) {
      jdbcTemplate.update(INSERT_QUERY, storeSchema, table, column);
    }
  }

  public String getTableNameLinkedWithSC(String scName, String columnName) {
    try {
      return jdbcTemplate.
          queryForObject(SELECT_QUERY, String.class,
              scName.substring(0, scName.length() - 2),
              columnName);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public void deleteGeoMetadata() {
    jdbcTemplate.update("DELETE FROM public.ddm_geoserver_pk_metadata");
  }

}
