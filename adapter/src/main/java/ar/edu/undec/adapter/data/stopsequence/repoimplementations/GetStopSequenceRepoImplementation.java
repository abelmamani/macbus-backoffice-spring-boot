package ar.edu.undec.adapter.data.stopsequence.repoimplementations;

import ar.edu.undec.adapter.data.stopsequence.crud.StopSequenceCRUD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stopsequence.outputs.StopSequenceRepository;

@AllArgsConstructor
@Service
public class GetStopSequenceRepoImplementation implements StopSequenceRepository {
    private StopSequenceCRUD stopSequenceCRUD;

    @Override
    public boolean isStopUsedInOtherRoutes(String stopName, String routeName) {
        return stopSequenceCRUD.isStopUsedInOtherRoutes(stopName, routeName);
    }
}
