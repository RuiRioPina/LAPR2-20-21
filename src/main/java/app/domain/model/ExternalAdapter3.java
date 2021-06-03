package app.domain.model;

import com.example1.ExternalModule3API;

public class ExternalAdapter3 implements ExternalModule {
    ExternalModule3API externalModule = new ExternalModule3API();
    static final int  API_KEY = 12345;
    /**
     * The method from the interface ExternalModule overwritten
     * @param param the parameter where the information regarding itself is brought
     * @return the a instance of ReferenceValue containing the information about the metrics used for the parameter and max/min values for itself.
     */
    @Override
    public ReferenceValue getReferenceValue(Parameter param) {

        double minReference = externalModule.getMinReferenceValue(param.getCode(), API_KEY);
        double maxReference = externalModule.getMaxReferenceValue(param.getCode(), API_KEY);
        String usedMetric = externalModule.usedMetric(param.getCode(), API_KEY);
        return new ReferenceValue(usedMetric, maxReference, minReference);
    }

    @Override
    public String getMetricFor(String parameterCode) {
        return externalModule.usedMetric(parameterCode,API_KEY);
    }
}
