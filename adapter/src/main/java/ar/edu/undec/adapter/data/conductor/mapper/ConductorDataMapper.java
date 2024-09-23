package ar.edu.undec.adapter.data.conductor.mapper;

import conductor.models.ConductorResponseModel;
import java.util.Map;

public class ConductorDataMapper {
    public static ConductorResponseModel mapToConductorResponseModel(Map<String, Object> map) {
        return new ConductorResponseModel(
                (String) map.get("id"),
                (String) map.get("name"),
                (String) map.get("lastName"),
                (String) map.get("email"));
    }
}
