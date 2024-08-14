package stopsequence.outputs;

public interface StopSequenceRepository {
    boolean isStopUsedInOtherRoutes(String stopName, String routeName);
}
