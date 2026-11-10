package r2;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import p3.j;
import p3.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class e extends j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Class f5703c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Constructor f5704d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f5705e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Method f5706f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Method f5707g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Method f5708h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Method f5709i;

    public e() throws NoSuchMethodException {
        Method methodU;
        Constructor<?> constructor;
        Method methodT;
        Method method;
        Method method2;
        Method method3;
        Class<?> cls = null;
        try {
            Class<?> cls2 = Class.forName("android.graphics.FontFamily");
            constructor = cls2.getConstructor(null);
            methodT = T(cls2);
            Class cls3 = Integer.TYPE;
            method = cls2.getMethod("addFontFromBuffer", ByteBuffer.class, cls3, FontVariationAxis[].class, cls3, cls3);
            method2 = cls2.getMethod("freeze", null);
            method3 = cls2.getMethod("abortCreation", null);
            methodU = U(cls2);
            cls = cls2;
        } catch (ClassNotFoundException | NoSuchMethodException e5) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(e5.getClass().getName()), e5);
            methodU = null;
            constructor = null;
            methodT = null;
            method = null;
            method2 = null;
            method3 = null;
        }
        this.f5703c = cls;
        this.f5704d = constructor;
        this.f5705e = methodT;
        this.f5706f = method;
        this.f5707g = method2;
        this.f5708h = method3;
        this.f5709i = methodU;
    }

    public static Method T(Class cls) {
        Class cls2 = Boolean.TYPE;
        Class cls3 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls3, cls2, cls3, cls3, cls3, FontVariationAxis[].class);
    }

    public Typeface S(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) this.f5703c, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.f5709i.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public Method U(Class cls) throws NoSuchMethodException {
        Class<?> cls2 = Array.newInstance((Class<?>) cls, 1).getClass();
        Class cls3 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", cls2, cls3, cls3);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    @Override // p3.j
    public final Typeface s(Context context, u2.d[] dVarArr) throws IOException {
        Object objNewInstance;
        boolean zBooleanValue;
        Typeface typefaceS;
        boolean zBooleanValue2;
        if (dVarArr.length >= 1) {
            Method method = this.f5705e;
            if (method == null) {
                Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
            }
            try {
                if (method != null) {
                    HashMap map = new HashMap();
                    for (u2.d dVar : dVarArr) {
                        if (dVar.f5837f == 0) {
                            Uri uri = dVar.f5832a;
                            if (!map.containsKey(uri)) {
                                map.put(uri, p.z(context, uri));
                            }
                        }
                    }
                    Map mapUnmodifiableMap = Collections.unmodifiableMap(map);
                    try {
                        objNewInstance = this.f5704d.newInstance(null);
                    } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
                        objNewInstance = null;
                    }
                    if (objNewInstance != null) {
                        int length = dVarArr.length;
                        int i5 = 0;
                        boolean z2 = false;
                        while (true) {
                            Method method2 = this.f5708h;
                            if (i5 >= length) {
                                if (!z2) {
                                    method2.invoke(objNewInstance, null);
                                    break;
                                }
                                try {
                                    zBooleanValue = ((Boolean) this.f5707g.invoke(objNewInstance, null)).booleanValue();
                                } catch (IllegalAccessException | InvocationTargetException unused2) {
                                    zBooleanValue = false;
                                }
                                if (!zBooleanValue || (typefaceS = S(objNewInstance)) == null) {
                                    break;
                                    break;
                                }
                                return Typeface.create(typefaceS, 0);
                            }
                            u2.d dVar2 = dVarArr[i5];
                            ByteBuffer byteBuffer = (ByteBuffer) mapUnmodifiableMap.get(dVar2.f5832a);
                            if (byteBuffer != null) {
                                try {
                                    zBooleanValue2 = ((Boolean) this.f5706f.invoke(objNewInstance, byteBuffer, Integer.valueOf(dVar2.f5833b), null, Integer.valueOf(dVar2.f5834c), Integer.valueOf(dVar2.f5835d ? 1 : 0))).booleanValue();
                                } catch (IllegalAccessException | InvocationTargetException unused3) {
                                    zBooleanValue2 = false;
                                }
                                if (!zBooleanValue2) {
                                    method2.invoke(objNewInstance, null);
                                    break;
                                }
                                z2 = true;
                            }
                            i5++;
                            z2 = z2;
                        }
                    }
                } else {
                    u2.d dVar3 = null;
                    int i6 = Integer.MAX_VALUE;
                    for (u2.d dVar4 : dVarArr) {
                        int iAbs = (Math.abs(dVar4.f5834c - 400) * 2) + (dVar4.f5835d ? 1 : 0);
                        if (dVar3 == null || i6 > iAbs) {
                            dVar3 = dVar4;
                            i6 = iAbs;
                        }
                    }
                    ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(dVar3.f5832a, "r", null);
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        try {
                            Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(dVar3.f5834c).setItalic(dVar3.f5835d).build();
                            parcelFileDescriptorOpenFileDescriptor.close();
                            return typefaceBuild;
                        } catch (Throwable th) {
                            try {
                                parcelFileDescriptorOpenFileDescriptor.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    }
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                        return null;
                    }
                }
            } catch (IOException | IllegalAccessException | InvocationTargetException unused4) {
            }
        }
        return null;
    }
}
