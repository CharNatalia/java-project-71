package hexlet.code;

public record DiffDTO(LineIndicator status, String key, Object value) {
    public enum LineIndicator {
        ADDED("  +"),
        DELETED("  -"),
        UNCHANGED("   ");

        private final String indicator;

        LineIndicator(String indicator) {
            this.indicator = indicator;
        }

        public String getIndicator() {
            return indicator;
        }
    }
}
