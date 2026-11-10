package io.github.libxposed.service;

import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class HotReloadResult extends b.b {
    private final String message;
    private final Status status;

    /* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
    public enum Status {
        SUCCEEDED,
        FAILED,
        UNSUPPORTED,
        IN_PROGRESS,
        PROCESS_DIED
    }

    public HotReloadResult(Status status, String str) {
        this.status = status;
        this.message = str;
    }

    public static HotReloadResult from(int i5, String str) {
        Status status;
        if (i5 == 0) {
            status = Status.SUCCEEDED;
        } else if (i5 == 1) {
            status = Status.FAILED;
        } else if (i5 == 2) {
            status = Status.UNSUPPORTED;
        } else if (i5 == 3) {
            status = Status.IN_PROGRESS;
        } else {
            if (i5 != 4) {
                throw new XposedService.ServiceException("Invalid hot reload status code: " + i5);
            }
            status = Status.PROCESS_DIED;
        }
        return new HotReloadResult(status, str);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof HotReloadResult)) {
            return false;
        }
        HotReloadResult hotReloadResult = (HotReloadResult) obj;
        return Objects.equals(this.status, hotReloadResult.status) && Objects.equals(this.message, hotReloadResult.message);
    }

    public final int hashCode() {
        Status status = this.status;
        String str = this.message;
        return Objects.hashCode(str) + (Objects.hashCode(status) * 31);
    }

    public String message() {
        return this.message;
    }

    public Status status() {
        return this.status;
    }

    public final String toString() {
        Object[] objArr = {this.status, this.message};
        String[] strArrSplit = "status;message".length() == 0 ? new String[0] : "status;message".split(";");
        StringBuilder sb = new StringBuilder("HotReloadResult[");
        for (int i5 = 0; i5 < strArrSplit.length; i5++) {
            sb.append(strArrSplit[i5]);
            sb.append("=");
            sb.append(objArr[i5]);
            if (i5 != strArrSplit.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
