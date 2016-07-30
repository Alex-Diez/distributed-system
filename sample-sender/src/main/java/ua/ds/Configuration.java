package ua.ds;

public class Configuration {
    final String host;
    final int port;
    final String topology;
    final int counts;
    final int frequency;
    final char frequencyPeriod;

    private Configuration(String host, int port, String topology, int counts, int frequency, char frequencyPeriod) {
        this.host = host;
        this.port = port;
        this.topology = topology;
        this.counts = counts;
        this.frequency = frequency;
        this.frequencyPeriod = frequencyPeriod;
    }

    public static class Builder {
        private String host = "localhost";
        private int port = 1090;
        private String topology = "";
        private int count = 1;
        private int frequency = 0;
        private char frequencyPeriod = 's';

        public Builder withHost(String host) {
            this.host = host;
            return this;
        }

        public Builder withPort(int port) {
            this.port = port;
            return this;
        }

        public Builder withTopology(String topology) {
            this.topology = topology;
            return this;
        }

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withFrequency(int frequency) {
            this.frequency = frequency;
            return this;
        }

        public Builder withFrequencyPeriod(char frequencyPeriod) {
            this.frequencyPeriod = frequencyPeriod;
            return this;
        }

        public Configuration build() {
            return new Configuration(host, port, topology, count, frequency, frequencyPeriod);
        }
    }
}
