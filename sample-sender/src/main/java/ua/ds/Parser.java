package ua.ds;

public class Parser {

    public Configuration parse(String[] params) {
        Configuration.Builder confBuilder = new Configuration.Builder();
        for (int i = 0; i < params.length; i += 1) {
            if ("--host".equals(params[i])) {
                confBuilder.withHost(params[i + 1]);
                i += 1;
            }
            else if ("--port".equals(params[i])) {
                confBuilder.withPort(Integer.parseInt(params[i + 1]));
                i += 1;
            }
            else if ("--topology".equals(params[i])) {
                params[i + 1] = params[i + 1].charAt(0) == '/' ? params[i + 1] : "/" + params[i + 1];
                confBuilder.withTopology(params[i + 1]);
                i += 1;
            }
            else if ("--count".equals(params[i])) {
                confBuilder.withCount(Integer.parseInt(params[i + 1]));
                i += 1;
            }
            else if ("--frequency".equals(params[i])) {
                confBuilder.withFrequency(Integer.parseInt(params[i + 1]));
                i += 1;
            }
            else if ("--frequency-period".equals(params[i])) {
                confBuilder.withFrequencyPeriod(params[i + 1].charAt(0));
                i += 1;
            }
        }
        return confBuilder.build();
    }


}
