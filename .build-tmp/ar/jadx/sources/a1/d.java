package a1;

import android.R;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.a0;
import androidx.lifecycle.k0;
import androidx.profileinstaller.ProfileInstallReceiver;
import b3.w;
import b4.l;
import e2.f0;
import e2.x;
import f0.b0;
import f0.g3;
import f0.s1;
import io.github.libxposed.service.IXposedService;
import j.m;
import j.t;
import j1.v;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import m.e1;
import m.q;
import m.r;
import m.y;
import m.z;
import m4.p;
import q1.u;
import q3.k;
import w2.n;
import y1.g0;
import z.e0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class d implements b3.i, i3.e, l4.d, r, e1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f30d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f31e;

    public d(float f5, float f6) {
        this.f30d = 14;
        this.f31e = new z(f5, f6, 0.01f);
    }

    public d(float f5, float f6, q qVar) {
        this.f30d = 16;
        this.f31e = new w(qVar != null ? new e1.f(f5, f6, qVar) : new d(f5, f6));
    }

    public d(int i5) {
        this.f30d = i5;
        switch (i5) {
            case 9:
                this.f31e = new m(10);
                break;
            case 10:
                this.f31e = new LinkedHashMap(0, 0.75f, true);
                break;
            default:
                this.f31e = Build.VERSION.SDK_INT >= 28 ? new k0(14) : new k0(15);
                break;
        }
    }

    public /* synthetic */ d(int i5, Object obj) {
        this.f30d = i5;
        this.f31e = obj;
    }

    public /* synthetic */ d(int i5, boolean z2) {
        this.f30d = i5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public d(a4.e eVar) {
        this.f30d = 12;
        this.f31e = (v3.i) eVar;
    }

    public d(Context context) {
        this.f30d = 2;
        this.f31e = context.getApplicationContext();
    }

    public d(View view) {
        this.f30d = 23;
        if (Build.VERSION.SDK_INT < 30) {
            this.f31e = new d(22, view);
            return;
        }
        n nVar = new n(22, view);
        nVar.f6424f = view;
        this.f31e = nVar;
    }

    public d(k2.b bVar) {
        this.f30d = 11;
        this.f31e = new l.e(l.h.f3492a, bVar);
    }

    public d(long[] jArr) {
        t tVar;
        this.f30d = 17;
        if (jArr != null) {
            long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
            tVar = new t(jArrCopyOf.length);
            int i5 = tVar.f3150b;
            if (i5 < 0) {
                k.a.d("");
                throw null;
            }
            if (jArrCopyOf.length != 0) {
                int length = jArrCopyOf.length + i5;
                long[] jArr2 = tVar.f3149a;
                if (jArr2.length < length) {
                    long[] jArrCopyOf2 = Arrays.copyOf(jArr2, Math.max(length, (jArr2.length * 3) / 2));
                    l.d("copyOf(...)", jArrCopyOf2);
                    tVar.f3149a = jArrCopyOf2;
                }
                long[] jArr3 = tVar.f3149a;
                int i6 = tVar.f3150b;
                if (i5 != i6) {
                    k.z(jArr3, jArr3, jArrCopyOf.length + i5, i5, i6);
                }
                k.z(jArrCopyOf, jArr3, i5, 0, jArrCopyOf.length);
                tVar.f3150b += jArrCopyOf.length;
            }
        } else {
            tVar = new t(16);
        }
        this.f31e = tVar;
    }

    @Override // m.e1, m.c1
    public boolean a() {
        ((w) this.f31e).getClass();
        return false;
    }

    @Override // m.c1
    public long b(q qVar, q qVar2, q qVar3) {
        return ((w) this.f31e).b(qVar, qVar2, qVar3);
    }

    @Override // m.c1
    public q d(long j5, q qVar, q qVar2, q qVar3) {
        return ((w) this.f31e).d(j5, qVar, qVar2, qVar3);
    }

    @Override // b3.i
    public void e(final h1.c cVar) {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new b3.a("EmojiCompatInitializer"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new Runnable() { // from class: b3.l
            @Override // java.lang.Runnable
            public final void run() {
                a1.d dVar = this.f625d;
                h1.c cVar2 = cVar;
                ThreadPoolExecutor threadPoolExecutor2 = threadPoolExecutor;
                try {
                    u uVarQ = d4.a.q((Context) dVar.f31e);
                    if (uVarQ == null) {
                        throw new RuntimeException("EmojiCompat font provider not available on this device.");
                    }
                    t tVar = (t) uVarQ.f651a;
                    synchronized (tVar.f645g) {
                        tVar.f647i = threadPoolExecutor2;
                    }
                    uVarQ.f651a.e(new m(cVar2, threadPoolExecutor2));
                } catch (Throwable th) {
                    cVar2.I(th);
                    threadPoolExecutor2.shutdown();
                }
            }
        });
    }

    @Override // m.c1
    public q f(long j5, q qVar, q qVar2, q qVar3) {
        return ((w) this.f31e).f(j5, qVar, qVar2, qVar3);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r6v4, types: [a4.e, v3.i] */
    @Override // l4.d
    public Object g(l4.e eVar, t3.c cVar) throws Throwable {
        l4.a aVar;
        Throwable th;
        p pVar;
        if (cVar instanceof l4.a) {
            aVar = (l4.a) cVar;
            int i5 = aVar.f3513g;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                aVar.f3513g = i5 - Integer.MIN_VALUE;
            } else {
                aVar = new l4.a(this, cVar);
            }
        } else {
            aVar = new l4.a(this, cVar);
        }
        Object obj = aVar.f3511e;
        int i6 = aVar.f3513g;
        p3.n nVar = p3.n.f5026a;
        if (i6 != 0) {
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            pVar = aVar.f3510d;
            try {
                p3.j.O(obj);
                pVar.releaseIntercepted();
                return nVar;
            } catch (Throwable th2) {
                th = th2;
                pVar.releaseIntercepted();
                throw th;
            }
        }
        p3.j.O(obj);
        p pVar2 = new p(eVar, aVar.getContext());
        try {
            aVar.f3510d = pVar2;
            aVar.f3513g = 1;
            Object objInvoke = ((v3.i) this.f31e).invoke(pVar2, aVar);
            u3.a aVar2 = u3.a.f5838d;
            if (objInvoke != aVar2) {
                objInvoke = nVar;
            }
            if (objInvoke == aVar2) {
                return aVar2;
            }
            pVar = pVar2;
            pVar.releaseIntercepted();
            return nVar;
        } catch (Throwable th3) {
            th = th3;
            pVar = pVar2;
            pVar.releaseIntercepted();
            throw th;
        }
    }

    @Override // m.r
    public y get(int i5) {
        switch (this.f30d) {
            case 14:
                return (z) this.f31e;
            default:
                return (y) this.f31e;
        }
    }

    @Override // i3.e
    public void h() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // i3.e
    public void i(int i5, Object obj) {
        String str;
        switch (i5) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = "";
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i5 == 6 || i5 == 7 || i5 == 8) {
            Log.e("ProfileInstaller", str, (Throwable) obj);
        } else {
            Log.d("ProfileInstaller", str);
        }
        ((ProfileInstallReceiver) this.f31e).setResultCode(i5);
    }

    public void j() {
        ((b0) this.f31e).getClass();
    }

    public void k(byte b5) {
        ((Parcel) this.f31e).writeByte(b5);
    }

    public void m(float f5) {
        ((Parcel) this.f31e).writeFloat(f5);
    }

    @Override // m.c1
    public q o(q qVar, q qVar2, q qVar3) {
        return ((w) this.f31e).o(qVar, qVar2, qVar3);
    }

    public void p(long j5) {
        long jB = k2.m.b(j5);
        byte b5 = 0;
        if (!k2.n.a(jB, 0L)) {
            if (k2.n.a(jB, 4294967296L)) {
                b5 = 1;
            } else if (k2.n.a(jB, 8589934592L)) {
                b5 = 2;
            }
        }
        k(b5);
        if (k2.n.a(k2.m.b(j5), 0L)) {
            return;
        }
        m(k2.m.c(j5));
    }

    public g3 q() {
        b3.j jVarA = b3.j.a();
        if (jVarA.b() == 1) {
            return new g2.l(true);
        }
        s1 s1VarS = f0.l.s(Boolean.FALSE);
        g2.h hVar = new g2.h(s1VarS, this);
        jVarA.f615a.writeLock().lock();
        try {
            if (jVarA.f617c == 1 || jVarA.f617c == 2) {
                jVarA.f618d.post(new b3.h(Arrays.asList(hVar), jVarA.f617c, null));
            } else {
                jVarA.f616b.add(hVar);
            }
            return s1VarS;
        } finally {
            jVarA.f615a.writeLock().unlock();
        }
    }

    public void r() {
        View view = (View) this.f31e;
        if (view != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void s(float f5, float f6, float f7, float f8) {
        b bVar = (b) this.f31e;
        y0.r rVarR = bVar.r();
        long jF = p3.j.f(x0.f.d(bVar.t()) - (f7 + f5), x0.f.b(bVar.t()) - (f8 + f6));
        if (x0.f.d(jF) < 0.0f || x0.f.b(jF) < 0.0f) {
            throw new IllegalArgumentException("Width and height must be greater than or equal to zero");
        }
        bVar.D(jF);
        rVarR.k(f5, f6);
    }

    public void t() {
    }

    public a2.h u(a2.h hVar, u uVar) {
        Object obj;
        long jC;
        long j5;
        boolean z2;
        Object obj2 = j.n.f3122a;
        m mVar = (m) this.f31e;
        List list = (List) hVar.f49e;
        m mVar2 = new m(list.size());
        int size = list.size();
        int i5 = 0;
        while (i5 < size) {
            j1.w wVar = (j1.w) list.get(i5);
            long j6 = wVar.f3290a;
            int iB = k.a.b(mVar.f3119e, mVar.f3121g, j6);
            if (iB < 0 || (obj = mVar.f3120f[iB]) == obj2) {
                obj = null;
            }
            v vVar = (v) obj;
            if (vVar == null) {
                j5 = wVar.f3291b;
                jC = wVar.f3293d;
                z2 = false;
            } else {
                long j7 = vVar.f3287a;
                boolean z3 = vVar.f3289c;
                jC = uVar.C(vVar.f3288b);
                j5 = j7;
                z2 = z3;
            }
            long j8 = wVar.f3290a;
            List list2 = list;
            int i6 = size;
            mVar2.b(j8, new j1.u(j8, wVar.f3291b, wVar.f3293d, wVar.f3294e, wVar.f3295f, j5, jC, z2, wVar.f3296g, wVar.f3298i, wVar.f3299j, wVar.f3300k));
            boolean z4 = wVar.f3294e;
            if (z4) {
                mVar.b(j6, new v(wVar.f3291b, wVar.f3292c, z4));
            } else {
                int iB2 = k.a.b(mVar.f3119e, mVar.f3121g, j6);
                if (iB2 >= 0) {
                    Object[] objArr = mVar.f3120f;
                    if (objArr[iB2] != obj2) {
                        objArr[iB2] = obj2;
                        mVar.f3118d = true;
                    }
                }
            }
            i5++;
            list = list2;
            size = i6;
        }
        return new a2.h(mVar2, 11, hVar);
    }

    public void v(float f5, float f6, long j5) {
        y0.r rVarR = ((b) this.f31e).r();
        rVarR.k(x0.c.d(j5), x0.c.e(j5));
        rVarR.d(f5, f6);
        rVarR.k(-x0.c.d(j5), -x0.c.e(j5));
    }

    public void w() {
        View viewFindViewById;
        View view = (View) this.f31e;
        if (view == null) {
            return;
        }
        if (view.isInEditMode() || view.onCheckIsTextEditor()) {
            view.requestFocus();
            viewFindViewById = view;
        } else {
            viewFindViewById = view.getRootView().findFocus();
        }
        if (viewFindViewById == null) {
            viewFindViewById = view.getRootView().findViewById(R.id.content);
        }
        if (viewFindViewById == null || !viewFindViewById.hasWindowFocus()) {
            return;
        }
        viewFindViewById.post(new a0(8, viewFindViewById));
    }

    public void x(float f5, float f6) {
        ((b) this.f31e).r().k(f5, f6);
    }

    public void y(x xVar, long j5, boolean z2, f0 f0Var) {
        e0 e0Var = (e0) this.f31e;
        e0Var.n(g0.b(e0.a(e0Var, xVar, j5, z2, false, f0Var, false)) ? v.f0.f5919f : v.f0.f5918e);
    }
}
