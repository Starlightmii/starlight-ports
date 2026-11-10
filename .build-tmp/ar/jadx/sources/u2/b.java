package u2;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Log;
import f0.w;
import j.o;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import k0.l;
import p3.j;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f5824a = new o(2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w f5825b = new w(3);

    /* JADX WARN: Code duplicated, block: B:18:0x003f  */
    /* JADX WARN: Code duplicated, block: B:23:0x0057 A[Catch: all -> 0x0090, TryCatch #0 {all -> 0x0090, blocks: (B:3:0x0005, B:4:0x000c, B:6:0x0012, B:8:0x001f, B:10:0x0025, B:13:0x002c, B:15:0x0038, B:20:0x0042, B:22:0x0048, B:29:0x0080, B:23:0x0057, B:25:0x0065, B:28:0x0077, B:30:0x0083), top: B:36:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x0077 A[Catch: all -> 0x0090, TRY_ENTER, TryCatch #0 {all -> 0x0090, blocks: (B:3:0x0005, B:4:0x000c, B:6:0x0012, B:8:0x001f, B:10:0x0025, B:13:0x002c, B:15:0x0038, B:20:0x0042, B:22:0x0048, B:29:0x0080, B:23:0x0057, B:25:0x0065, B:28:0x0077, B:30:0x0083), top: B:36:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x0065 A[SYNTHETIC] */
    public static l a(Context context, List list) {
        ProviderInfo providerInfoB;
        Typeface typefaceCreate;
        d4.a.g("FontProvider.getFontFamilyResult");
        try {
            ArrayList arrayList = new ArrayList();
            for (int i5 = 0; i5 < list.size(); i5++) {
                c cVar = (c) list.get(i5);
                if (Build.VERSION.SDK_INT >= 31) {
                    String str = cVar.f5830e;
                    j jVar = r2.d.f5701a;
                    if (str == null || str.isEmpty()) {
                        typefaceCreate = null;
                    } else {
                        typefaceCreate = Typeface.create(str, 0);
                        Typeface typefaceCreate2 = Typeface.create(Typeface.DEFAULT, 0);
                        if (typefaceCreate == null || typefaceCreate.equals(typefaceCreate2)) {
                            typefaceCreate = null;
                        }
                    }
                    if (typefaceCreate == null || r2.d.a(typefaceCreate) == null) {
                        providerInfoB = b(context.getPackageManager(), cVar, context.getResources());
                        if (providerInfoB == null) {
                            l lVar = new l();
                            lVar.f3377a = 1;
                            lVar.f3378b = Collections.singletonList(null);
                            return lVar;
                        }
                        arrayList.add(c(context, cVar, providerInfoB.authority));
                    } else {
                        arrayList.add(new d[]{new d(str, cVar.f5831f)});
                    }
                } else {
                    providerInfoB = b(context.getPackageManager(), cVar, context.getResources());
                    if (providerInfoB == null) {
                        l lVar2 = new l();
                        lVar2.f3377a = 1;
                        lVar2.f3378b = Collections.singletonList(null);
                        return lVar2;
                    }
                    arrayList.add(c(context, cVar, providerInfoB.authority));
                }
            }
            l lVar3 = new l();
            lVar3.f3377a = 0;
            lVar3.f3378b = arrayList;
            return lVar3;
        } finally {
            Trace.endSection();
        }
    }

    public static ProviderInfo b(PackageManager packageManager, c cVar, Resources resources) {
        w wVar = f5825b;
        o oVar = f5824a;
        d4.a.g("FontProvider.getProvider");
        try {
            List listJ = cVar.f5829d;
            String str = cVar.f5826a;
            String str2 = cVar.f5827b;
            if (listJ == null) {
                listJ = j.J(resources, 0);
            }
            a aVar = new a();
            aVar.f5821a = str;
            aVar.f5822b = str2;
            aVar.f5823c = listJ;
            ProviderInfo providerInfo = (ProviderInfo) oVar.a(aVar);
            if (providerInfo != null) {
                Trace.endSection();
                return providerInfo;
            }
            ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(str, 0);
            if (providerInfoResolveContentProvider == null) {
                throw new PackageManager.NameNotFoundException("No package found for authority: " + str);
            }
            if (!providerInfoResolveContentProvider.packageName.equals(str2)) {
                throw new PackageManager.NameNotFoundException("Found content provider " + str + ", but package was not " + str2);
            }
            Signature[] signatureArr = packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures;
            ArrayList arrayList = new ArrayList();
            for (Signature signature : signatureArr) {
                arrayList.add(signature.toByteArray());
            }
            Collections.sort(arrayList, wVar);
            for (int i5 = 0; i5 < listJ.size(); i5++) {
                ArrayList arrayList2 = new ArrayList((Collection) listJ.get(i5));
                Collections.sort(arrayList2, wVar);
                if (arrayList.size() == arrayList2.size()) {
                    int i6 = 0;
                    while (true) {
                        if (i6 >= arrayList.size()) {
                            oVar.b(aVar, providerInfoResolveContentProvider);
                            Trace.endSection();
                            return providerInfoResolveContentProvider;
                        }
                        if (!Arrays.equals((byte[]) arrayList.get(i6), (byte[]) arrayList2.get(i6))) {
                            break;
                        }
                        i6++;
                    }
                }
            }
            Trace.endSection();
            return null;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public static d[] c(Context context, c cVar, String str) {
        d4.a.g("FontProvider.query");
        try {
            ArrayList arrayList = new ArrayList();
            Uri uriBuild = new Uri.Builder().scheme("content").authority(str).build();
            Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
            ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(uriBuild);
            Cursor cursorQuery = null;
            try {
                String[] strArr = {"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"};
                d4.a.g("ContentQueryWrapper.query");
                try {
                    String[] strArr2 = {cVar.f5828c};
                    if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                        try {
                            cursorQuery = contentProviderClientAcquireUnstableContentProviderClient.query(uriBuild, strArr, "query = ?", strArr2, null, null);
                        } catch (RemoteException e5) {
                            Log.w("FontsProvider", "Unable to query the content provider", e5);
                        }
                    }
                    Trace.endSection();
                    if (cursorQuery != null && cursorQuery.getCount() > 0) {
                        int columnIndex = cursorQuery.getColumnIndex("result_code");
                        ArrayList arrayList2 = new ArrayList();
                        int columnIndex2 = cursorQuery.getColumnIndex("_id");
                        int columnIndex3 = cursorQuery.getColumnIndex("file_id");
                        int columnIndex4 = cursorQuery.getColumnIndex("font_ttc_index");
                        int columnIndex5 = cursorQuery.getColumnIndex("font_weight");
                        int columnIndex6 = cursorQuery.getColumnIndex("font_italic");
                        while (cursorQuery.moveToNext()) {
                            int i5 = columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0;
                            arrayList2.add(new d(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, cVar.f5831f, i5));
                        }
                        arrayList = arrayList2;
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                        contentProviderClientAcquireUnstableContentProviderClient.close();
                    }
                    d[] dVarArr = (d[]) arrayList.toArray(new d[0]);
                    Trace.endSection();
                    return dVarArr;
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            } catch (Throwable th2) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                    contentProviderClientAcquireUnstableContentProviderClient.close();
                }
                throw th2;
            }
        } catch (Throwable th3) {
            Trace.endSection();
            throw th3;
        }
    }
}
