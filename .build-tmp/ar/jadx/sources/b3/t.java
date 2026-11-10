package b3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import androidx.lifecycle.k0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t implements i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f642d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u2.c f643e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k0 f644f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f645g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Handler f646h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ThreadPoolExecutor f647i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ThreadPoolExecutor f648j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public h1.c f649k;

    public t(Context context, u2.c cVar) {
        p3.p.h("Context cannot be null", context);
        this.f642d = context.getApplicationContext();
        this.f643e = cVar;
        this.f644f = u.f650d;
    }

    public final void a() {
        synchronized (this.f645g) {
            try {
                this.f649k = null;
                Handler handler = this.f646h;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                this.f646h = null;
                ThreadPoolExecutor threadPoolExecutor = this.f648j;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
                this.f647i = null;
                this.f648j = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final u2.d b() {
        try {
            k0 k0Var = this.f644f;
            Context context = this.f642d;
            u2.c cVar = this.f643e;
            k0Var.getClass();
            Object[] objArr = {cVar};
            ArrayList arrayList = new ArrayList(1);
            Object obj = objArr[0];
            Objects.requireNonNull(obj);
            arrayList.add(obj);
            k0.l lVarA = u2.b.a(context, Collections.unmodifiableList(arrayList));
            int i5 = lVarA.f3377a;
            if (i5 != 0) {
                throw new RuntimeException("fetchFonts failed (" + i5 + ")");
            }
            u2.d[] dVarArr = (u2.d[]) ((List) lVarA.f3378b).get(0);
            if (dVarArr == null || dVarArr.length == 0) {
                throw new RuntimeException("fetchFonts failed (empty result)");
            }
            return dVarArr[0];
        } catch (PackageManager.NameNotFoundException e5) {
            throw new RuntimeException("provider not found", e5);
        }
    }

    @Override // b3.i
    public final void e(h1.c cVar) {
        synchronized (this.f645g) {
            this.f649k = cVar;
        }
        synchronized (this.f645g) {
            try {
                if (this.f649k == null) {
                    return;
                }
                if (this.f647i == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new a("emojiCompat"));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    this.f648j = threadPoolExecutor;
                    this.f647i = threadPoolExecutor;
                }
                this.f647i.execute(new androidx.lifecycle.a0(3, this));
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
