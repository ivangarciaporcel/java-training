package org.example.effectiveJava.chapter2.item2;

public class CompleteComputer extends AbstractComputer {

    private MonitorSize monitorSize;

    private CompleteComputer(Builder builder) {
        super(builder);
        this.monitorSize = builder.monitorSize;
    }

    public MonitorSize getMonitorSize() {
        return this.monitorSize;
    }

    public static class Builder extends AbstractComputer.Builder<Builder> {

        private MonitorSize monitorSize;

        public Builder addMonitorSize(MonitorSize monitorSize) {
            this.monitorSize = monitorSize;
            return self();
        }

        @Override
        public CompleteComputer build() {
            return new CompleteComputer(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
