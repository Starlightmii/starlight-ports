package com.oplus.systemui.statusbar.seeding;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.SystemUIAppComponentFactoryBase;
import com.android.systemui.application.ContentProviderContextAvailableCallback;
import com.android.systemui.application.ContentProviderContextInitializer;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.oplus.systemui.statusbar.seeding.livealert.LitePluginLoadManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingCardProvider extends ContentProvider implements ContentProviderContextInitializer, Listener {
    public static final Companion Companion = new Companion();
    public static final String PROVIDER_IMPL = "com.oplus.systemui.plugins.seedling.SeedlingCardServerProvider";
    public static final String TAG = "SeedlingCardProvider";
    private ContentProviderContextAvailableCallback contextAvailableCallback;
    public Lazy litePluginLoadManager;
    private ContentProvider pluginContentProvider;
    public Lazy seedlingPluginManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.SeedlingCardProvider$call$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements Runnable {
        public /* synthetic */ String $arg;
        public /* synthetic */ String $authority;
        public /* synthetic */ Bundle $extras;
        public /* synthetic */ String $method;
        public /* synthetic */ SeedlingCardProvider this$0;

        @Override // java.lang.Runnable
        public final void run() {
            Lazy seedlingPluginManager;
            SeedlingPluginManager seedlingPluginManager2;
            Context pluginContext;
            if (this.this$0.pluginContentProvider == null && (seedlingPluginManager = this.this$0.getSeedlingPluginManager()) != null && (seedlingPluginManager2 = (SeedlingPluginManager) seedlingPluginManager.get()) != null && (pluginContext = seedlingPluginManager2.getPluginContext()) != null) {
                this.this$0.createPluginContentProvider(pluginContext);
            }
            ContentProvider contentProvider = this.this$0.pluginContentProvider;
            if (contentProvider != null) {
                contentProvider.call(this.$authority, this.$method, this.$arg, this.$extras);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createPluginContentProvider(Context context) {
        try {
            Class[] clsArr = new Class[0];
            ContentProvider contentProvider = (ContentProvider) context.getClassLoader().loadClass(PROVIDER_IMPL).getDeclaredConstructor(null).newInstance(null);
            contentProvider.onCreate();
            this.pluginContentProvider = contentProvider;
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "create plugin provider failed " + e);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            Log.d("SystemUi--SeedlingPlugin", "SeedlingCardProvider-->".concat("create plugin provider " + this.pluginContentProvider));
        }
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        SeedlingPluginManager seedlingPluginManager;
        ContentProviderContextAvailableCallback contentProviderContextAvailableCallback = this.contextAvailableCallback;
        if (contentProviderContextAvailableCallback == null) {
            contentProviderContextAvailableCallback = null;
        }
        if (context == null) {
            Path$.ExternalSyntheticBUOutline0.m$1("Required value was null.");
            return;
        }
        ((SystemUIAppComponentFactoryBase.instantiateProviderCompat.1) contentProviderContextAvailableCallback).onContextAvailable(context);
        super.attachInfo(context, providerInfo);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            Log.d("SystemUi--SeedlingPlugin", "SeedlingCardProvider-->".concat("register plugin callback. seedlingPluginManager:" + getSeedlingPluginManager()));
        }
        Lazy seedlingPluginManager2 = getSeedlingPluginManager();
        if (seedlingPluginManager2 == null || (seedlingPluginManager = (SeedlingPluginManager) seedlingPluginManager2.get()) == null) {
            return;
        }
        seedlingPluginManager.addCallback(this);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, String str3, Bundle bundle) {
        LitePluginLoadManager litePluginLoadManager;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            Object obj = this.pluginContentProvider;
            if (obj == null) {
                obj = " empty provider";
            }
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("provider call ", str, "  ", str2, " ");
            sbM.append(obj);
            Log.d("SystemUi--SeedlingPlugin", "SeedlingCardProvider-->".concat(sbM.toString()));
        }
        if (this.pluginContentProvider != null || !FeatureOption.isLiteLiveAlertDevice()) {
            ContentProvider contentProvider = this.pluginContentProvider;
            if (contentProvider != null) {
                return contentProvider.call(str, str2, str3, bundle);
            }
            return null;
        }
        Lazy litePluginLoadManager2 = getLitePluginLoadManager();
        if (litePluginLoadManager2 != null && (litePluginLoadManager = (LitePluginLoadManager) litePluginLoadManager2.get()) != null) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3();
            anonymousClass3.this$0 = this;
            anonymousClass3.$authority = str;
            anonymousClass3.$method = str2;
            anonymousClass3.$arg = str3;
            anonymousClass3.$extras = bundle;
            VarHandle.storeStoreFence();
            litePluginLoadManager.call(str2, bundle, anonymousClass3);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        ContentProvider contentProvider = this.pluginContentProvider;
        if (contentProvider != null) {
            return contentProvider.delete(uri, str, strArr);
        }
        return 0;
    }

    public final Lazy getLitePluginLoadManager() {
        Lazy lazy = this.litePluginLoadManager;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public final Lazy getSeedlingPluginManager() {
        Lazy lazy = this.seedlingPluginManager;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        ContentProvider contentProvider = this.pluginContentProvider;
        if (contentProvider != null) {
            return contentProvider.getType(uri);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        ContentProvider contentProvider = this.pluginContentProvider;
        if (contentProvider != null) {
            return contentProvider.insert(uri, contentValues);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            return true;
        }
        Log.d("SystemUi--SeedlingPlugin", "SeedlingCardProvider-->onCreate");
        return true;
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public /* bridge */ /* synthetic */ void onDispatchTouchEvent(MotionEvent motionEvent) {
        super.onDispatchTouchEvent(motionEvent);
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public void onPluginAdded(SeedlingPlugin seedlingPlugin, Context context) {
        Lazy seedlingPluginManager;
        SeedlingPluginManager seedlingPluginManager2;
        Context pluginContext;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            Log.d("SystemUi--SeedlingPlugin", "SeedlingCardProvider-->".concat("onPluginAdded " + context + ", " + this.pluginContentProvider));
        }
        if (this.pluginContentProvider != null || (seedlingPluginManager = getSeedlingPluginManager()) == null || (seedlingPluginManager2 = (SeedlingPluginManager) seedlingPluginManager.get()) == null || (pluginContext = seedlingPluginManager2.getPluginContext()) == null) {
            return;
        }
        createPluginContentProvider(pluginContext);
    }

    @Override // com.oplus.systemui.statusbar.seeding.Listener
    public void onPluginRemove(SeedlingPlugin seedlingPlugin) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            Log.d("SystemUi--SeedlingPlugin", "SeedlingCardProvider-->onPluginRemove");
        }
        this.pluginContentProvider = null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        ContentProvider contentProvider = this.pluginContentProvider;
        if (contentProvider != null) {
            return contentProvider.query(uri, strArr, str, strArr2, str2);
        }
        return null;
    }

    public void setContextAvailableCallback(ContentProviderContextAvailableCallback contentProviderContextAvailableCallback) {
        this.contextAvailableCallback = contentProviderContextAvailableCallback;
    }

    public final void setLitePluginLoadManager(Lazy lazy) {
        this.litePluginLoadManager = lazy;
    }

    public final void setSeedlingPluginManager(Lazy lazy) {
        this.seedlingPluginManager = lazy;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        ContentProvider contentProvider = this.pluginContentProvider;
        if (contentProvider != null) {
            return contentProvider.update(uri, contentValues, str, strArr);
        }
        return 0;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_SEEDLING)) {
            Object obj = this.pluginContentProvider;
            if (obj == null) {
                obj = " empty provider";
            }
            Log.d("SystemUi--SeedlingPlugin", "SeedlingCardProvider-->".concat("provider call " + str + " " + obj));
        }
        ContentProvider contentProvider = this.pluginContentProvider;
        if (contentProvider != null) {
            return contentProvider.call(str, str2, bundle);
        }
        return null;
    }
}
