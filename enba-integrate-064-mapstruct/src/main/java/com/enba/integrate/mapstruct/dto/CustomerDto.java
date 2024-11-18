/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package com.enba.integrate.mapstruct.dto;

import java.util.List;


public class CustomerDto {

    public Long id;
    public String customerName;
    public List<OrderItemDto> orders;
}
