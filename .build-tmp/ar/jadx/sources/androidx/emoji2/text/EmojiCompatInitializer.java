package androidx.emoji2.text;

import a1.d;
import android.content.Context;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.lifecycle.q;
import androidx.lifecycle.v;
import b3.j;
import b3.k;
import b3.u;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import l3.a;
import l3.b;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class EmojiCompatInitializer implements b {
    @Override // l3.b
    public final List a() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    @Override // l3.b
    public final Object b(Context context) {
        Object objB;
        u uVar = new u(new d(context));
        uVar.f652b = 1;
        if (j.f614k == null) {
            synchronized (j.f613j) {
                try {
                    if (j.f614k == null) {
                        j.f614k = new j(uVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        a aVarC = a.c(context);
        aVarC.getClass();
        synchronized (a.f3506e) {
            try {
                objB = aVarC.f3507a.get(ProcessLifecycleInitializer.class);
                if (objB == null) {
                    objB = aVarC.b(ProcessLifecycleInitializer.class, new HashSet());
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        q lifecycle = ((v) objB).getLifecycle();
        lifecycle.a(new k(this, lifecycle));
        return Boolean.TRUE;
    }
}
