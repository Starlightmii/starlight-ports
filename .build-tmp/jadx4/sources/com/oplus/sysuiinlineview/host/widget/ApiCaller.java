package com.oplus.sysuiinlineview.host.widget;

import com.oplus.sysuiinlineview.host.SysUiInlineServiceWrapper;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ApiCaller {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ApiCallerImpl extends ApiCaller {
        private final Consumer<Object> apiExecutor;
        private final String description;
        private final Consumer<Throwable> exceptionReceiver;
        private final Object identifier;

        public ApiCallerImpl(Object obj, String str, Consumer<Throwable> consumer, Consumer<Object> consumer2) {
            this.identifier = obj;
            this.description = str;
            this.exceptionReceiver = consumer;
            this.apiExecutor = consumer2;
        }

        public boolean equals(Object obj) {
            ApiCaller apiCaller;
            Object identifier;
            String description;
            if (obj == this) {
                return true;
            }
            return (obj instanceof ApiCaller) && (identifier = (apiCaller = (ApiCaller) obj).getIdentifier()) != null && identifier.equals(getIdentifier()) && (description = apiCaller.getDescription()) != null && description.equals(getDescription());
        }

        @Override // com.oplus.sysuiinlineview.host.widget.ApiCaller
        public String getDescription() {
            return this.description;
        }

        @Override // com.oplus.sysuiinlineview.host.widget.ApiCaller
        public Object getIdentifier() {
            return this.identifier;
        }

        public final int hashCode() {
            Object obj = this.identifier;
            int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
            String str = this.description;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        @Override // com.oplus.sysuiinlineview.host.widget.ApiCaller
        public final void performApiCall(SysUiInlineServiceWrapper sysUiInlineServiceWrapper) {
            this.apiExecutor.accept(sysUiInlineServiceWrapper);
        }

        @Override // com.oplus.sysuiinlineview.host.widget.ApiCaller
        public void receiveApiException(Throwable th) {
            Consumer<Throwable> consumer = this.exceptionReceiver;
            if (consumer == null) {
                return;
            }
            consumer.accept(th);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Builder {
        private Consumer<Object> apiExecutor;
        private String description;
        private Consumer<Throwable> exceptionReceiver;
        private Object identifier;

        public Builder(Object obj, String str, Consumer<Throwable> consumer, Consumer<Object> consumer2) {
            this.identifier = obj;
            this.description = str;
            this.exceptionReceiver = consumer;
            this.apiExecutor = consumer2;
        }

        public final ApiCaller build() {
            return new ApiCallerImpl(this.identifier, this.description, this.exceptionReceiver, this.apiExecutor);
        }

        public final Builder setApiExecutor(Consumer<Object> consumer) {
            this.apiExecutor = consumer;
            return this;
        }

        public final Builder setDescription(String str) {
            this.description = str;
            return this;
        }

        public final Builder setExceptionReceiver(Consumer<Throwable> consumer) {
            this.exceptionReceiver = consumer;
            return this;
        }

        public final Builder setIdentifier(Object obj) {
            this.identifier = obj;
            return this;
        }
    }

    public abstract String getDescription();

    public abstract Object getIdentifier();

    public abstract void performApiCall(SysUiInlineServiceWrapper sysUiInlineServiceWrapper);

    public abstract void receiveApiException(Throwable th);
}
