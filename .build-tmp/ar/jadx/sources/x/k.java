package x;

import android.view.inputmethod.EditorInfo;
import java.util.LinkedHashSet;
import q1.z0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f6473a = new k();

    public final void a(EditorInfo editorInfo) {
        editorInfo.setSupportedHandwritingGestures(p3.j.H(z0.m(), z0.x(), z0.t(), z0.v(), z0.z(), z0.B(), z0.D()));
        Class[] clsArr = {z0.m(), z0.x(), z0.t(), z0.v()};
        LinkedHashSet linkedHashSet = new LinkedHashSet(q3.v.F(4));
        for (int i5 = 0; i5 < 4; i5++) {
            linkedHashSet.add(clsArr[i5]);
        }
        editorInfo.setSupportedHandwritingGesturePreviews(linkedHashSet);
    }
}
