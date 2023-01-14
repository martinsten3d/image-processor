package se.kth.martsten.lab_4_v2.model;

/**
 * This class describes the parameters for an image process.
 */
public class ProcessingParams {

    private final ProcessType processType;
    private final double[] parameters;

    /**
     * Constructs new processing parameters with the specified parameters.
     *
     * @param processType The type of the image process.
     * @param parameters An array which represents the parameters that will be used in the image process.
     */
    public ProcessingParams(ProcessType processType, double[] parameters) {
        this.processType = processType;
        this.parameters = parameters;
    }

    ProcessType getType(){
        return processType;
    }

    double[] getParameters(){
        return parameters;
    }
}