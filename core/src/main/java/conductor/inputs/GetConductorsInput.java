package conductor.inputs;

import conductor.models.ConductorResponseModel;
import java.util.Collection;

public interface GetConductorsInput{
    Collection<ConductorResponseModel> getConductors();
}
