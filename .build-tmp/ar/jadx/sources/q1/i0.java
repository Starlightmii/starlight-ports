package q1;

import android.view.View;
import android.view.translation.ViewTranslationCallback;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i0 implements ViewTranslationCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i0 f5225a = new i0();

    /* JADX WARN: Code duplicated, block: B:26:0x007b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x007d A[LOOP:0: B:5:0x001d->B:27:0x007d, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:30:0x0080 A[EDGE_INSN: B:30:0x0080->B:28:0x0080 BREAK  A[LOOP:0: B:5:0x001d->B:27:0x007d], SYNTHETIC] */
    public final boolean onClearTranslation(View view) {
        a4.a aVar;
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.platform.AndroidComposeView", view);
        t0.c contentCaptureManager$ui_release = ((u) view).getContentCaptureManager$ui_release();
        contentCaptureManager$ui_release.f5789j = 1;
        j.r rVarF = contentCaptureManager$ui_release.f();
        Object[] objArr = rVarF.f3140c;
        long[] jArr = rVarF.f3138a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i5 = 0;
            while (true) {
                long j5 = jArr[i5];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i5 != length) {
                        break;
                        break;
                    }
                    i5++;
                } else {
                    int i6 = 8 - ((~(i5 - length)) >>> 31);
                    for (int i7 = 0; i7 < i6; i7++) {
                        if ((255 & j5) < 128) {
                            LinkedHashMap linkedHashMap = ((j2) objArr[(i5 << 3) + i7]).f5252a.f6318d.f6308d;
                            Object obj = linkedHashMap.get(w1.q.f6357v);
                            if (obj == null) {
                                obj = null;
                            }
                            if (obj != null) {
                                Object obj2 = linkedHashMap.get(w1.h.f6292k);
                                w1.a aVar2 = (w1.a) (obj2 != null ? obj2 : null);
                                if (aVar2 != null && (aVar = (a4.a) aVar2.f6273b) != null) {
                                }
                            }
                        }
                        j5 >>= 8;
                    }
                    if (i6 != 8) {
                        break;
                    }
                    if (i5 != length) {
                        break;
                    }
                    i5++;
                }
            }
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0083 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:28:0x0085 A[LOOP:0: B:5:0x001d->B:28:0x0085, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:31:0x0088 A[EDGE_INSN: B:31:0x0088->B:29:0x0088 BREAK  A[LOOP:0: B:5:0x001d->B:28:0x0085], SYNTHETIC] */
    public final boolean onHideTranslation(View view) {
        a4.c cVar;
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.platform.AndroidComposeView", view);
        t0.c contentCaptureManager$ui_release = ((u) view).getContentCaptureManager$ui_release();
        contentCaptureManager$ui_release.f5789j = 1;
        j.r rVarF = contentCaptureManager$ui_release.f();
        Object[] objArr = rVarF.f3140c;
        long[] jArr = rVarF.f3138a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i5 = 0;
            while (true) {
                long j5 = jArr[i5];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i5 != length) {
                        break;
                        break;
                    }
                    i5++;
                } else {
                    int i6 = 8 - ((~(i5 - length)) >>> 31);
                    for (int i7 = 0; i7 < i6; i7++) {
                        if ((255 & j5) < 128) {
                            LinkedHashMap linkedHashMap = ((j2) objArr[(i5 << 3) + i7]).f5252a.f6318d.f6308d;
                            Object obj = linkedHashMap.get(w1.q.f6357v);
                            if (obj == null) {
                                obj = null;
                            }
                            if (b4.l.a(obj, Boolean.TRUE)) {
                                Object obj2 = linkedHashMap.get(w1.h.f6291j);
                                w1.a aVar = (w1.a) (obj2 != null ? obj2 : null);
                                if (aVar != null && (cVar = (a4.c) aVar.f6273b) != null) {
                                }
                            }
                        }
                        j5 >>= 8;
                    }
                    if (i6 != 8) {
                        break;
                    }
                    if (i5 != length) {
                        break;
                    }
                    i5++;
                }
            }
        }
        return true;
    }

    public final boolean onShowTranslation(View view) {
        a4.c cVar;
        b4.l.c("null cannot be cast to non-null type androidx.compose.ui.platform.AndroidComposeView", view);
        t0.c contentCaptureManager$ui_release = ((u) view).getContentCaptureManager$ui_release();
        contentCaptureManager$ui_release.f5789j = 2;
        j.r rVarF = contentCaptureManager$ui_release.f();
        Object[] objArr = rVarF.f3140c;
        long[] jArr = rVarF.f3138a;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i5 = 0;
        while (true) {
            long j5 = jArr[i5];
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i6 = 8 - ((~(i5 - length)) >>> 31);
                for (int i7 = 0; i7 < i6; i7++) {
                    if ((255 & j5) < 128) {
                        LinkedHashMap linkedHashMap = ((j2) objArr[(i5 << 3) + i7]).f5252a.f6318d.f6308d;
                        Object obj = linkedHashMap.get(w1.q.f6357v);
                        if (obj == null) {
                            obj = null;
                        }
                        if (b4.l.a(obj, Boolean.FALSE)) {
                            Object obj2 = linkedHashMap.get(w1.h.f6291j);
                            w1.a aVar = (w1.a) (obj2 != null ? obj2 : null);
                            if (aVar != null && (cVar = (a4.c) aVar.f6273b) != null) {
                            }
                        }
                    }
                    j5 >>= 8;
                }
                if (i6 != 8) {
                    return true;
                }
            }
            if (i5 == length) {
                return true;
            }
            i5++;
        }
    }
}
