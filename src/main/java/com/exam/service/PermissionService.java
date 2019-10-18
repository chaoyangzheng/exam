package com.exam.service;

import com.exam.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    Map<String,Object> findPermsByIdCard(String idCard);
}
