package route.usecases;

import route.inputs.UpdateRouteInput;
import route.models.Route;
import route.outputs.UpdateRouteRepository;

public class UpdateRouteUseCase implements UpdateRouteInput {
    private UpdateRouteRepository updateRouteRepository;

    public UpdateRouteUseCase(UpdateRouteRepository updateRouteRepository) {
        this.updateRouteRepository = updateRouteRepository;
    }

    @Override
    public void updateRoute(Route route) {
        updateRouteRepository.update(route);
    }
}
