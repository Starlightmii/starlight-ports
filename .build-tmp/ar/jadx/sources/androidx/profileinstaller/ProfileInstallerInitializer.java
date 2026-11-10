package androidx.profileinstaller;

import android.content.Context;
import android.view.Choreographer;
import androidx.lifecycle.k0;
import e2.c0;
import java.util.Collections;
import java.util.List;
import l3.b;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class ProfileInstallerInitializer implements b {
    @Override // l3.b
    public final List a() {
        return Collections.EMPTY_LIST;
    }

    @Override // l3.b
    public final Object b(Context context) {
        Choreographer.getInstance().postFrameCallback(new c0(this, context.getApplicationContext()));
        return new k0(21);
    }
}
