package i3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import androidx.lifecycle.k0;
import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o2.h f2707a = new o2.h();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f2708b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static k0 f2709c = null;

    public static long a(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return Build.VERSION.SDK_INT >= 33 ? h.a(packageManager, context).lastUpdateTime : packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static k0 b() {
        k0 k0Var = new k0(22);
        f2709c = k0Var;
        o2.h hVar = f2707a;
        hVar.getClass();
        if (o2.g.f4392i.i(hVar, null, k0Var)) {
            o2.g.b(hVar);
        }
        return f2709c;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:109:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:20:0x002c  */
    /* JADX WARN: Code duplicated, block: B:21:0x002e  */
    /* JADX WARN: Code duplicated, block: B:43:0x006f  */
    /* JADX WARN: Code duplicated, block: B:49:0x0092  */
    /* JADX WARN: Code duplicated, block: B:58:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:67:0x00c3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:68:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:70:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:71:0x00cc A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x00ce  */
    public static void c(Context context, boolean z2) {
        int i5;
        boolean z3;
        int i6;
        File file;
        boolean z4;
        File file2;
        long length;
        boolean z5;
        File file3;
        i iVarA;
        i iVar;
        int i7;
        AssetFileDescriptor assetFileDescriptorOpenFd;
        if (z2 || f2709c == null) {
            synchronized (f2708b) {
                if (z2) {
                    i5 = 0;
                    assetFileDescriptorOpenFd = context.getAssets().openFd("dexopt/baseline.prof");
                    if (assetFileDescriptorOpenFd.getLength() > 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    assetFileDescriptorOpenFd.close();
                    i6 = Build.VERSION.SDK_INT;
                    if (i6 >= 28) {
                        file = new File(new File("/data/misc/profiles/ref/", context.getPackageName()), "primary.prof");
                        long length2 = file.length();
                        if (file.exists()) {
                            z4 = false;
                        } else {
                            z4 = false;
                        }
                        file2 = new File(new File("/data/misc/profiles/cur/0/", context.getPackageName()), "primary.prof");
                        length = file2.length();
                        if (file2.exists()) {
                            z5 = false;
                        } else {
                            z5 = false;
                        }
                        long jA = a(context);
                        file3 = new File(context.getFilesDir(), "profileInstalled");
                        if (file3.exists()) {
                            iVarA = i.a(file3);
                        } else {
                            iVarA = null;
                        }
                        if (iVarA == null) {
                            if (!z3) {
                                i5 = 327680;
                            } else if (z4) {
                                i5 = 1;
                            } else if (z5) {
                                i5 = 2;
                            }
                        } else if (!z3) {
                            i5 = 327680;
                        } else if (z4) {
                            i5 = 1;
                        } else if (z5) {
                            i5 = 2;
                        }
                        if (z2) {
                            i5 = 2;
                        }
                        if (iVarA != null) {
                            i5 = 3;
                        }
                        iVar = new i(1, i5, jA, length);
                        if (iVarA != null) {
                            iVar.b(file3);
                        } else {
                            iVar.b(file3);
                        }
                        b();
                        return;
                    }
                    b();
                    return;
                }
                if (f2709c != null) {
                    return;
                }
                i5 = 0;
                try {
                    assetFileDescriptorOpenFd = context.getAssets().openFd("dexopt/baseline.prof");
                    try {
                        if (assetFileDescriptorOpenFd.getLength() > 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        assetFileDescriptorOpenFd.close();
                    } catch (Throwable th) {
                        if (assetFileDescriptorOpenFd == null) {
                            throw th;
                        }
                        try {
                            assetFileDescriptorOpenFd.close();
                            throw th;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            throw th;
                        }
                    }
                } catch (IOException unused) {
                    z3 = false;
                }
                i6 = Build.VERSION.SDK_INT;
                if (i6 >= 28 && i6 != 30) {
                    file = new File(new File("/data/misc/profiles/ref/", context.getPackageName()), "primary.prof");
                    long length3 = file.length();
                    if (file.exists() || length3 <= 0) {
                        z4 = false;
                    } else {
                        z4 = true;
                    }
                    file2 = new File(new File("/data/misc/profiles/cur/0/", context.getPackageName()), "primary.prof");
                    length = file2.length();
                    if (file2.exists() || length <= 0) {
                        z5 = false;
                    } else {
                        z5 = true;
                    }
                    try {
                        long jA2 = a(context);
                        file3 = new File(context.getFilesDir(), "profileInstalled");
                        if (file3.exists()) {
                            try {
                                iVarA = i.a(file3);
                            } catch (IOException unused2) {
                                b();
                                return;
                            }
                        } else {
                            iVarA = null;
                        }
                        if (iVarA == null && iVarA.f2705c == jA2 && (i7 = iVarA.f2704b) != 2) {
                            i5 = i7;
                        } else if (!z3) {
                            i5 = 327680;
                        } else if (z4) {
                            i5 = 1;
                        } else if (z5) {
                            i5 = 2;
                        }
                        if (z2 && z5 && i5 != 1) {
                            i5 = 2;
                        }
                        if (iVarA != null && iVarA.f2704b == 2 && i5 == 1 && length3 < iVarA.f2706d) {
                            i5 = 3;
                        }
                        iVar = new i(1, i5, jA2, length);
                        if (iVarA != null || !iVarA.equals(iVar)) {
                            try {
                                iVar.b(file3);
                            } catch (IOException unused3) {
                            }
                        }
                        b();
                        return;
                    } catch (PackageManager.NameNotFoundException unused4) {
                        b();
                        return;
                    }
                }
                b();
                return;
                throw th;
            }
        }
    }
}
