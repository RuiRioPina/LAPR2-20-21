//package app.domain.model;
//
//import com.example1.ExternalModule3API;
//
//public class ExternalAdapter3 implements ExternalModule {
//    ExternalModule3API externalModule = new ExternalModule3API();
//
//    @Override
//    public ReferenceValue getReferenceValue(Parameter param) {
//        final int API_KEY = 12345;
//        double minReference = externalModule.getMinReferenceValue(param.getCode(), API_KEY);
//        double maxReference = externalModule.getMaxReferenceValue(param.getCode(), API_KEY);
//        String usedMetric = externalModule.usedMetric(param.getCode(), API_KEY);
//        return new ReferenceValue(usedMetric, maxReference, minReference);
//    }
//}
