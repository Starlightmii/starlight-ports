package q1;

import android.content.Context;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AccessibilityManager f5207a;

    public g(Context context) {
        Object systemService = context.getSystemService("accessibility");
        b4.l.c("null cannot be cast to non-null type android.view.accessibility.AccessibilityManager", systemService);
        this.f5207a = (AccessibilityManager) systemService;
    }
}
