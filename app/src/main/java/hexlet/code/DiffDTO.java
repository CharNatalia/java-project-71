package hexlet.code;

public record DiffDTO(LineIndicator lineIndicator, String key, Object value) {
    public enum LineIndicator {
        ADDED("+"),
        DELETED("-"),
        NOCHANGES(" ");

        private final String indicator;

        LineIndicator(String indicator) {
            this.indicator = indicator;
        }

        public String getIndicator() {
            return indicator;
        }
    }
}
