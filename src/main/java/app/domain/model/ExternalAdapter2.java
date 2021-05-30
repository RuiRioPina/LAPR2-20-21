package app.domain.model;

import com.example2.EMRefValue;
import com.example2.ExternalModule2API;


public class ExternalAdapter2 implements ExternalModule {
    ExternalModule2API externalModule2API = new ExternalModule2API();
    /**
     * The method from the interface ExternalModule overwritten
     * @param param the parameter where the information regarding itself is brought
     * @return the a instance of ReferenceValue containing the information about the metrics used for the parameter and max/min values for itself.
     */
    @Override
    public ReferenceValue getReferenceValue(Parameter param) {

        String metric = externalModule2API.getMetricsFor(param.getCode());
        EMRefValue reference = externalModule2API.getReferenceFor(param.getCode());
        return new ReferenceValue(metric, reference.getMaxValue(), reference.getMinValue());
    }
}
