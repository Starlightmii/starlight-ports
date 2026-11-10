package b3;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.emoji2.text.EmojiCompatInitializer;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k implements androidx.lifecycle.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ androidx.lifecycle.q f624d;

    public k(EmojiCompatInitializer emojiCompatInitializer, androidx.lifecycle.q qVar) {
        this.f624d = qVar;
    }

    @Override // androidx.lifecycle.f
    public final void a(androidx.lifecycle.v vVar) {
        (Build.VERSION.SDK_INT >= 28 ? b.a(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new n(), 500L);
        this.f624d.b(this);
    }
}
