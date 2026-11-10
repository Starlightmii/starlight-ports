package s0;

import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillManager;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends AutofillManager.AutofillCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f5766a = new e();

    public final void a(a aVar) {
        aVar.f5763c.registerCallback(this);
    }

    public final void b(a aVar) {
        aVar.f5763c.unregisterCallback(this);
    }

    @Override // android.view.autofill.AutofillManager.AutofillCallback
    public final void onAutofillEvent(View view, int i5, int i6) {
        String str;
        super.onAutofillEvent(view, i5, i6);
        if (i6 == 1) {
            str = "Autofill popup was shown.";
        } else if (i6 != 2) {
            str = i6 != 3 ? "Unknown status event." : "Autofill popup isn't shown because autofill is not available.\n\nDid you set up autofill?\n1. Go to Settings > System > Languages&input > Advanced > Autofill Service\n2. Pick a service\n\nDid you add an account?\n1. Go to Settings > System > Languages&input > Advanced\n2. Click on the settings icon next to the Autofill Service\n3. Add your account";
        } else {
            str = "Autofill popup was hidden.";
        }
        Log.d("Autofill Status", str);
    }
}
