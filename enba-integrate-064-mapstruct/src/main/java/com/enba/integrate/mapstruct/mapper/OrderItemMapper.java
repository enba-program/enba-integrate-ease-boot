/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package com.enba.integrate.mapstruct.mapper;


import com.enba.integrate.mapstruct.dto.OrderItem;
import com.enba.integrate.mapstruct.dto.OrderItemDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface OrderItemMapper {

    OrderItemMapper MAPPER = Mappers.getMapper(OrderItemMapper.class);

    OrderItem toOrder(OrderItemDto orderItemDto);

    @InheritInverseConfiguration
    OrderItemDto fromOrder(OrderItem orderItem);
}
