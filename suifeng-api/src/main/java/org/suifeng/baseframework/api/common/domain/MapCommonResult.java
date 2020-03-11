package org.suifeng.baseframework.api.common.domain;

import java.util.LinkedHashMap;
import java.util.Map;


public class MapCommonResult extends CommonResult<Map<String, Object>> {

    public MapCommonResult() {
        data(new LinkedHashMap<>());
    }

    public MapCommonResult put(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public static MapCommonResult ok() {
        return new MapCommonResult();
    }

    public static MapCommonResult ok(String msg) {
        MapCommonResult mapResult = new MapCommonResult();
        mapResult.msg = msg;
        return mapResult;
    }

    public static MapCommonResult error() {
        return new MapCommonResult();
    }


    public static MapCommonResult error(String msg) {
        MapCommonResult mapResult = new MapCommonResult();
        mapResult.msg = msg;
        return mapResult;
    }

    public static MapCommonResult error(int status, String msg) {
        MapCommonResult mapResult = new MapCommonResult();
        mapResult.msg = msg;
        mapResult.status = status;
        return mapResult;
    }
}
