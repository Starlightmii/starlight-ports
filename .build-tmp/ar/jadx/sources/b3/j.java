package b3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.inputmethod.EditorInfo;
import androidx.lifecycle.k0;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Object f613j = new Object();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static volatile j f614k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ReentrantReadWriteLock f615a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j.g f616b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f617c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f618d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f f619e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i f620f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final k0 f621g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f622h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d f623i;

    public j(u uVar) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.f615a = reentrantReadWriteLock;
        this.f617c = 3;
        i iVar = uVar.f651a;
        this.f620f = iVar;
        int i5 = uVar.f652b;
        this.f622h = i5;
        this.f623i = uVar.f653c;
        this.f618d = new Handler(Looper.getMainLooper());
        this.f616b = new j.g();
        this.f621g = new k0(7);
        f fVar = new f(this);
        this.f619e = fVar;
        reentrantReadWriteLock.writeLock().lock();
        if (i5 == 0) {
            try {
                this.f617c = 0;
            } catch (Throwable th) {
                this.f615a.writeLock().unlock();
                throw th;
            }
        }
        reentrantReadWriteLock.writeLock().unlock();
        if (b() == 0) {
            try {
                iVar.e(new e(fVar));
            } catch (Throwable th2) {
                e(th2);
            }
        }
    }

    public static j a() {
        j jVar;
        synchronized (f613j) {
            try {
                jVar = f614k;
                if (!(jVar != null)) {
                    throw new IllegalStateException("EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return jVar;
    }

    public static boolean c() {
        return f614k != null;
    }

    public final int b() {
        this.f615a.readLock().lock();
        try {
            return this.f617c;
        } finally {
            this.f615a.readLock().unlock();
        }
    }

    public final void d() {
        if (!(this.f622h == 1)) {
            throw new IllegalStateException("Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        }
        if (b() == 1) {
            return;
        }
        this.f615a.writeLock().lock();
        try {
            if (this.f617c == 0) {
                this.f615a.writeLock().unlock();
                return;
            }
            this.f617c = 0;
            this.f615a.writeLock().unlock();
            f fVar = this.f619e;
            j jVar = fVar.f608a;
            try {
                jVar.f620f.e(new e(fVar));
            } catch (Throwable th) {
                jVar.e(th);
            }
        } catch (Throwable th2) {
            this.f615a.writeLock().unlock();
            throw th2;
        }
    }

    public final void e(Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.f615a.writeLock().lock();
        try {
            this.f617c = 2;
            arrayList.addAll(this.f616b);
            this.f616b.clear();
            this.f615a.writeLock().unlock();
            this.f618d.post(new h(arrayList, this.f617c, th));
        } catch (Throwable th2) {
            this.f615a.writeLock().unlock();
            throw th2;
        }
    }

    public final void f(EditorInfo editorInfo) {
        if (b() != 1 || editorInfo == null) {
            return;
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        f fVar = this.f619e;
        fVar.getClass();
        Bundle bundle = editorInfo.extras;
        c3.b bVar = (c3.b) fVar.f610c.f656d;
        int iA = bVar.a(4);
        bundle.putInt("android.support.text.emoji.emojiCompat_metadataVersion", iA != 0 ? ((ByteBuffer) bVar.f1504g).getInt(iA + bVar.f1501d) : 0);
        editorInfo.extras.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", false);
    }
}
