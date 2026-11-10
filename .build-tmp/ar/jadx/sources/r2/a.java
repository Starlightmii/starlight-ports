package r2;

import android.view.WindowInsets;
import android.view.contentcapture.ContentCaptureSession;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ WindowInsets.Builder d() {
        return new WindowInsets.Builder();
    }

    public static /* synthetic */ WindowInsets.Builder e(WindowInsets windowInsets) {
        return new WindowInsets.Builder(windowInsets);
    }

    public static /* bridge */ /* synthetic */ ContentCaptureSession h(Object obj) {
        return (ContentCaptureSession) obj;
    }
}
