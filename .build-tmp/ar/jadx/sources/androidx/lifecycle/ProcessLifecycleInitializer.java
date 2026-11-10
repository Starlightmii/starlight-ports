package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements l3.b {
    @Override // l3.b
    public final List a() {
        return q3.s.f5477d;
    }

    @Override // l3.b
    public final Object b(Context context) {
        b4.l.e("context", context);
        l3.a aVarC = l3.a.c(context);
        b4.l.d("getInstance(...)", aVarC);
        if (!aVarC.f3508b.contains(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml");
        }
        if (!s.f288a.getAndSet(true)) {
            Context applicationContext = context.getApplicationContext();
            b4.l.c("null cannot be cast to non-null type android.app.Application", applicationContext);
            ((Application) applicationContext).registerActivityLifecycleCallbacks(new r());
        }
        d0 d0Var = d0.f239l;
        d0Var.getClass();
        d0Var.f244h = new Handler();
        d0Var.f245i.e(o.ON_CREATE);
        Context applicationContext2 = context.getApplicationContext();
        b4.l.c("null cannot be cast to non-null type android.app.Application", applicationContext2);
        ((Application) applicationContext2).registerActivityLifecycleCallbacks(new c0(d0Var));
        return d0Var;
    }
}
