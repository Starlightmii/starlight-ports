package t0;

import android.os.Build;
import android.os.Looper;
import android.util.LongSparseArray;
import android.view.translation.TranslationRequestValue;
import android.view.translation.TranslationResponseValue;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import b4.l;
import java.util.List;
import java.util.function.Consumer;
import q1.i;
import q1.j2;
import w1.h;
import w1.n;
import w1.q;
import y1.f;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5777a = new a();

    public static void a(c cVar, LongSparseArray longSparseArray) {
        TranslationResponseValue value;
        CharSequence text;
        j2 j2Var;
        n nVar;
        a4.c cVar2;
        int i5 = 0;
        while (i5 < longSparseArray.size()) {
            int i6 = i5 + 1;
            long jKeyAt = longSparseArray.keyAt(i5);
            ViewTranslationResponse viewTranslationResponseO = i.o(longSparseArray.get(jKeyAt));
            if (viewTranslationResponseO != null && (value = viewTranslationResponseO.getValue("android:text")) != null && (text = value.getText()) != null && (j2Var = (j2) cVar.f().d((int) jKeyAt)) != null && (nVar = j2Var.f5252a) != null) {
                Object obj = nVar.f6318d.f6308d.get(h.f6290i);
                if (obj == null) {
                    obj = null;
                }
                w1.a aVar = (w1.a) obj;
                if (aVar != null && (cVar2 = (a4.c) aVar.f6273b) != null) {
                }
            }
            i5 = i6;
        }
    }

    public final void b(c cVar, long[] jArr, int[] iArr, Consumer<ViewTranslationRequest> consumer) {
        n nVar;
        String strS;
        for (long j5 : jArr) {
            j2 j2Var = (j2) cVar.f().d((int) j5);
            if (j2Var != null && (nVar = j2Var.f5252a) != null) {
                i.q();
                ViewTranslationRequest.Builder builderM = i.m(cVar.f5783d.getAutofillId(), nVar.f6321g);
                Object obj = nVar.f6318d.f6308d.get(q.f6355t);
                if (obj == null) {
                    obj = null;
                }
                List list = (List) obj;
                if (list != null && (strS = h1.c.s(list, "\n")) != null) {
                    builderM.setValue("android:text", TranslationRequestValue.forText(new f(strS, null, 6)));
                    consumer.accept(builderM.build());
                }
            }
        }
    }

    public final void c(c cVar, LongSparseArray<ViewTranslationResponse> longSparseArray) {
        if (Build.VERSION.SDK_INT < 31) {
            return;
        }
        if (l.a(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            a(cVar, longSparseArray);
        } else {
            cVar.f5783d.post(new b.q(cVar, 2, longSparseArray));
        }
    }
}
