package x;

import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import q1.o2;
import v.o0;
import y1.g0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f6501a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f6502b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o0 f6505e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public e0 f6506f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public o2 f6507g;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Rect f6512l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final r f6513m;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b4.m f6503c = a.f6459g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a4.c f6504d = a.f6460h;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public e2.x f6508h = new e2.x("", g0.f6768b, 4);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public e2.n f6509i = e2.n.f2038g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f6510j = new ArrayList();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Object f6511k = h1.c.D(new b0.a(28, this));

    public u(View view, b bVar, p pVar) {
        this.f6501a = view;
        this.f6502b = pVar;
        this.f6513m = new r(bVar, pVar);
    }

    public final w a(EditorInfo editorInfo) {
        int i5;
        int i6;
        e2.x xVar = this.f6508h;
        String str = xVar.f2062a.f6759d;
        long j5 = xVar.f2063b;
        e2.n nVar = this.f6509i;
        int i7 = nVar.f2043e;
        int i8 = nVar.f2042d;
        boolean z2 = nVar.f2039a;
        if (i7 == 1) {
            i5 = z2 ? 6 : 0;
        } else if (i7 == 0) {
            i5 = 1;
        } else if (i7 == 2) {
            i5 = 2;
        } else if (i7 == 6) {
            i5 = 5;
        } else if (i7 == 5) {
            i5 = 7;
        } else if (i7 == 3) {
            i5 = 3;
        } else if (i7 == 4) {
            i5 = 4;
        } else {
            if (i7 != 7) {
                throw new IllegalStateException("invalid ImeAction");
            }
        }
        editorInfo.imeOptions = i5;
        v.f6514a.a(editorInfo, nVar.f2044f);
        if (i8 == 1) {
            i6 = 1;
        } else if (i8 == 2) {
            editorInfo.imeOptions |= Integer.MIN_VALUE;
            i6 = 1;
        } else if (i8 == 3) {
            i6 = 2;
        } else if (i8 == 4) {
            i6 = 3;
        } else if (i8 == 5) {
            i6 = 17;
        } else if (i8 == 6) {
            i6 = 33;
        } else if (i8 == 7) {
            i6 = 129;
        } else if (i8 == 8) {
            i6 = 18;
        } else {
            if (i8 != 9) {
                throw new IllegalStateException("Invalid Keyboard Type");
            }
            i6 = 8194;
        }
        editorInfo.inputType = i6;
        if (!z2 && (i6 & 1) == 1) {
            editorInfo.inputType = 131072 | i6;
            if (nVar.f2043e == 1) {
                editorInfo.imeOptions |= 1073741824;
            }
        }
        int i9 = editorInfo.inputType;
        if ((i9 & 1) == 1) {
            int i10 = nVar.f2040b;
            if (i10 == 1) {
                editorInfo.inputType = i9 | 4096;
            } else if (i10 == 2) {
                editorInfo.inputType = i9 | 8192;
            } else if (i10 == 3) {
                editorInfo.inputType = i9 | 16384;
            }
            if (nVar.f2041c) {
                editorInfo.inputType |= 32768;
            }
        }
        int i11 = g0.f6769c;
        editorInfo.initialSelStart = (int) (j5 >> 32);
        editorInfo.initialSelEnd = (int) (j5 & 4294967295L);
        q.f.s(editorInfo, str);
        editorInfo.imeOptions |= 33554432;
        if (!w.c.f6222a || i8 == 7 || i8 == 8) {
            q.f.t(editorInfo, false);
        } else {
            q.f.t(editorInfo, true);
            k.f6473a.a(editorInfo);
        }
        s sVar = t.f6500a;
        if (b3.j.c()) {
            b3.j.a().f(editorInfo);
        }
        w wVar = new w(this.f6508h, new a1.d(24, this), this.f6509i.f2041c, this.f6505e, this.f6506f, this.f6507g);
        this.f6510j.add(new WeakReference(wVar));
        return wVar;
    }
}
