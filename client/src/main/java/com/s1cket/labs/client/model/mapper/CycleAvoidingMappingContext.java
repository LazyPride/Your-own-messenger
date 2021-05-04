/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package com.s1cket.labs.client.model.mapper;

import java.util.IdentityHashMap;
import java.util.Map;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

/**
 * @author Andreas Gudian
 */
public class CycleAvoidingMappingContext {
    private Map<Object, Object> knownInstances = new IdentityHashMap<Object, Object>();

    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        return (T) knownInstances.get( source );
    }

    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        knownInstances.put( source, target );
    }
}
