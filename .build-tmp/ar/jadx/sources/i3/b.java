package i3;

import android.content.res.AssetManager;
import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Executor f2674a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f2675b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f2676c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final File f2677d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2678e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2679f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c[] f2680g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f2681h;

    public b(AssetManager assetManager, Executor executor, e eVar, String str, File file) {
        byte[] bArr;
        this.f2674a = executor;
        this.f2675b = eVar;
        this.f2678e = str;
        this.f2677d = file;
        int i5 = Build.VERSION.SDK_INT;
        if (i5 < 31) {
            switch (i5) {
                case 26:
                    bArr = f.f2697g;
                    break;
                case 27:
                    bArr = f.f2696f;
                    break;
                case 28:
                case 29:
                case 30:
                    bArr = f.f2695e;
                    break;
                default:
                    bArr = null;
                    break;
            }
        } else {
            bArr = f.f2694d;
        }
        this.f2676c = bArr;
    }

    public final FileInputStream a(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e5) {
            String message = e5.getMessage();
            if (message == null || !message.contains("compressed")) {
                return null;
            }
            this.f2675b.h();
            return null;
        }
    }

    public final void b(final int i5, final Serializable serializable) {
        this.f2674a.execute(new Runnable() { // from class: i3.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f2671d.f2675b.i(i5, serializable);
            }
        });
    }
}
