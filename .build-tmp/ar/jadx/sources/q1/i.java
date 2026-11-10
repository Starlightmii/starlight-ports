package q1;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.ScrollCaptureCallback;
import android.view.ScrollCaptureSession;
import android.view.ScrollCaptureTarget;
import android.view.View;
import android.view.autofill.AutofillId;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class i {
    public static /* bridge */ /* synthetic */ ScrollCaptureSession h(Object obj) {
        return (ScrollCaptureSession) obj;
    }

    public static /* synthetic */ ScrollCaptureTarget i(View view, Rect rect, Point point, ScrollCaptureCallback scrollCaptureCallback) {
        return new ScrollCaptureTarget(view, rect, point, scrollCaptureCallback);
    }

    public static /* synthetic */ ViewTranslationRequest.Builder m(AutofillId autofillId, long j5) {
        return new ViewTranslationRequest.Builder(autofillId, j5);
    }

    public static /* bridge */ /* synthetic */ ViewTranslationResponse o(Object obj) {
        return (ViewTranslationResponse) obj;
    }

    public static /* synthetic */ void q() {
    }
}
