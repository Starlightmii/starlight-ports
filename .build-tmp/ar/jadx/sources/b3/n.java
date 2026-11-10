package b3;

import android.os.Trace;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        try {
            int i5 = t2.d.f5802a;
            Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
            if (j.c()) {
                j.a().d();
            }
        } finally {
            int i6 = t2.d.f5802a;
            Trace.endSection();
        }
    }
}
