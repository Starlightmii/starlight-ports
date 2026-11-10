package b;

import io.github.andrea_lyz.assistrestore.ui.MainActivity;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f322d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ MainActivity f323e;

    public /* synthetic */ e(MainActivity mainActivity, int i5) {
        this.f322d = i5;
        this.f323e = mainActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f322d) {
            case 0:
                this.f323e.invalidateMenu();
                return;
            default:
                try {
                    super/*android.app.Activity*/.onBackPressed();
                    return;
                } catch (IllegalStateException e5) {
                    if (!b4.l.a(e5.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                        throw e5;
                    }
                    return;
                } catch (NullPointerException e6) {
                    if (!b4.l.a(e6.getMessage(), "Attempt to invoke virtual method 'android.os.Handler android.app.FragmentHostCallback.getHandler()' on a null object reference")) {
                        throw e6;
                    }
                    return;
                }
        }
    }
}
