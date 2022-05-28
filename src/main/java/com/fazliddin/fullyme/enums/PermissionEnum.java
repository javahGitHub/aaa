package com.fazliddin.fullyme.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PermissionEnum {
    ADD_PRODUCT(RoleTypeEnum.SYSTEM_ADMIN),
    ADD_PROMOTION(RoleTypeEnum.SYSTEM_ADMIN),
    ADD_STAFF(RoleTypeEnum.SYSTEM_ADMIN),

    CAN_ADD_USER(RoleTypeEnum.SYSTEM_ADMIN);

    private RoleTypeEnum roleType;
}
