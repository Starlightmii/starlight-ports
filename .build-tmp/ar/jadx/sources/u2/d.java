package u2;

import android.net.Uri;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f5832a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5833b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5834c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f5835d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f5836e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5837f;

    public d(Uri uri, int i5, int i6, boolean z2, String str, int i7) {
        uri.getClass();
        this.f5832a = uri;
        this.f5833b = i5;
        this.f5834c = i6;
        this.f5835d = z2;
        this.f5836e = str;
        this.f5837f = i7;
    }

    public d(String str, String str2) {
        this.f5832a = new Uri.Builder().scheme("systemfont").authority(str).build();
        this.f5833b = 0;
        this.f5834c = 400;
        this.f5835d = false;
        this.f5836e = str2;
        this.f5837f = 0;
    }
}
