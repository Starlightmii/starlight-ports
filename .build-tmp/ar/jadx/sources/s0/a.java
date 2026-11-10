package s0;

import android.view.autofill.AutofillManager;
import q1.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f5761a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f5762b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AutofillManager f5763c;

    public a(u uVar, f fVar) {
        this.f5761a = uVar;
        this.f5762b = fVar;
        AutofillManager autofillManager = (AutofillManager) uVar.getContext().getSystemService(AutofillManager.class);
        if (autofillManager == null) {
            throw new IllegalStateException("Autofill service could not be located.");
        }
        this.f5763c = autofillManager;
        uVar.setImportantForAutofill(1);
    }
}
