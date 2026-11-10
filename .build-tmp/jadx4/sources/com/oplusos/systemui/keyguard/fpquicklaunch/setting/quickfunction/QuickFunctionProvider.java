package com.oplusos.systemui.keyguard.fpquicklaunch.setting.quickfunction;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.biometrics.Utils;
import com.oplus.systemui.biometrics.finger.udfps.fpquicklaunch.setting.quickfunction.AbsQuickFunction;
import com.oplus.systemui.biometrics.finger.udfps.fpquicklaunch.setting.quickfunction.BreenoScanObjectFunction;
import com.oplus.systemui.biometrics.finger.udfps.fpquicklaunch.setting.quickfunction.ScanDocumentFunction;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QuickFunctionProvider extends ContentProvider {
    private static final String COMPONENT_SAFE = "oplus.permission.OPLUS_COMPONENT_SAFE";
    public static final Companion Companion = new Companion();
    private static final String GET_ALL = "quickFunctionGetAll";
    private static final String GET_ICON = "quickFunctionGetIcon";
    private static final String GET_TITLE = "quickFunctionGetTitle";
    private static final String TAG = "QuickFunctionProvider";
    private List<AbsQuickFunction> absQuickFunctionList = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        Context context;
        String callingPackage = getCallingPackage();
        if (getContext() != null && (context = getContext()) != null && context.checkCallingPermission("oplus.permission.OPLUS_COMPONENT_SAFE") == -1) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "QuickFunctionProvider-->permission denied!");
            }
            return null;
        }
        if (TextUtils.isEmpty(callingPackage) || TextUtils.isEmpty(str) || this.absQuickFunctionList.isEmpty()) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        int iHashCode = str.hashCode();
        if (iHashCode != -1911578550) {
            if (iHashCode != -338765904) {
                if (iHashCode == 880949415 && str.equals(GET_TITLE)) {
                    for (AbsQuickFunction absQuickFunction : this.absQuickFunctionList) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("QFTitle", absQuickFunction.getQFTitle());
                        bundle2.putBundle(String.valueOf(absQuickFunction.getQFClassName()), bundle3);
                    }
                }
            } else if (str.equals(GET_ALL)) {
                for (AbsQuickFunction absQuickFunction2 : this.absQuickFunctionList) {
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("QFPkgName", absQuickFunction2.getQFPkgName());
                    bundle4.putString("QFClassName", absQuickFunction2.getQFClassName());
                    bundle4.putObject("QFComponentName", absQuickFunction2.getQFComponentName());
                    bundle4.putString("QFTitle", absQuickFunction2.getQFTitle());
                    bundle4.putObject("QFStartIntent", absQuickFunction2.getHandleStartIntent());
                    bundle4.putObject("QFIcon", Utils.toBitmap(absQuickFunction2.getQFIcon()));
                    bundle2.putBundle(String.valueOf(absQuickFunction2.getQFClassName()), bundle4);
                }
            }
        } else if (str.equals(GET_ICON)) {
            for (AbsQuickFunction absQuickFunction3 : this.absQuickFunctionList) {
                Bundle bundle5 = new Bundle();
                bundle5.putObject("QFIcon", Utils.toBitmap(absQuickFunction3.getQFIcon()));
                bundle2.putBundle(String.valueOf(absQuickFunction3.getQFClassName()), bundle5);
            }
        }
        return bundle2;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public final List<AbsQuickFunction> getAbsQuickFunctionList() {
        return this.absQuickFunctionList;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "QuickFunctionProvider-->init absQuickFunctionList!");
        }
        Context context = getContext();
        if (context == null) {
            return true;
        }
        this.absQuickFunctionList = CollectionsKt__CollectionsKt.mutableListOf(new AbsQuickFunction[]{new BreenoScanObjectFunction(context), new ScanDocumentFunction(context), new AlipayPayCodeQuickFunction(context), new AlipayScanQuickFunction(context), new AlipayTrafficQuickFunction(context), new AmapBusQuickFunction(context), new AmapTaxiQuickFunction(context), new BaiduBusQuickFunction(context), new BaiduTaxiQuickFunction(context), new TTWorkQuickFunction(context), new WeChatPayQuickFunction(context), new WeChatScanQuickFunction(context)});
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public final void setAbsQuickFunctionList(List<AbsQuickFunction> list) {
        this.absQuickFunctionList = list;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
