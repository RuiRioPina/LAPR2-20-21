//package app.domain.model;
//
//import com.example3.CovidReferenceValues1API;
//
//
//public class ExternalAdapter1 implements ExternalModule {
//    CovidReferenceValues1API covidReferenceValues1API = new CovidReferenceValues1API();
//
//
//    @Override
//    public ReferenceValue getReferenceValue(Parameter param) {
//        final int API_KEY = 12345;
//        String usedMetric = covidReferenceValues1API.usedMetric(param.getCode(), API_KEY);
//        double minValue = covidReferenceValues1API.getMinReferenceValue(param.getCode(), API_KEY);
//        double maxValue = covidReferenceValues1API.getMaxReferenceValue(param.getCode(), API_KEY);
//
//        return new ReferenceValue(usedMetric, maxValue, minValue);
//    }
//
//    //covidAPI.usedMetric("HB001",123456); // exemplo de como usar a A*O
//}
