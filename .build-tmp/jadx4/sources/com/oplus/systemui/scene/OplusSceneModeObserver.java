package com.oplus.systemui.scene;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSceneModeObserver {
    public static final Companion Companion = new Companion();
    private static final String OP_BREATH_MODE_STATUS = "op_breath_mode_status";
    private static final String OP_LIGHT_BREATH_MODE_STATUS = "op_light_breath_mode_status";
    private static final String TAG = "OplusSceneModeObserver";
    private static final String VALUE_ON = "1";
    private static OplusSceneModeObserver instance;
    public boolean isInBrickMode;
    public boolean isInDeepBrickMode;
    public boolean isInLightBrickMode;
    private final ArrayList<Callback> mCallbacks = new ArrayList<>();
    private Context mContext;
    private final SettingsObserver mSettingsObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
        void onBrickModeChanged();

        void onDeepBrickModeChanged();

        void onLightBrickModeChanged();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusSceneModeObserver getInstance(Context context) {
            OplusSceneModeObserver oplusSceneModeObserver;
            OplusSceneModeObserver oplusSceneModeObserver2 = OplusSceneModeObserver.instance;
            if (oplusSceneModeObserver2 != null) {
                return oplusSceneModeObserver2;
            }
            synchronized (OplusSceneModeObserver.class) {
                oplusSceneModeObserver = OplusSceneModeObserver.instance;
                if (oplusSceneModeObserver == null) {
                    oplusSceneModeObserver = new OplusSceneModeObserver(context);
                    OplusSceneModeObserver.instance = oplusSceneModeObserver;
                }
            }
            return oplusSceneModeObserver;
        }

        public final <T> void safeForeach(List<? extends T> list, Consumer<T> consumer) {
            for (T t : CollectionsKt___CollectionsKt.toList(list)) {
                if (t != null) {
                    consumer.accept(t);
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SettingsObserver extends ContentObserver {
        public final Uri mOpBrickModeStatusUri;

        public SettingsObserver(Handler handler) {
            super(handler);
            this.mOpBrickModeStatusUri = Settings.Secure.getUriFor("op_breath_mode_status");
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            update(uri);
        }

        public final void update(Uri uri) {
            Context context = OplusSceneModeObserver.this.mContext;
            context.getClass();
            ContentResolver contentResolver = context.getContentResolver();
            if (uri == null || Intrinsics.areEqual(this.mOpBrickModeStatusUri, uri)) {
                boolean zEquals = "1".equals(Settings.Secure.getStringForUser(contentResolver, "op_breath_mode_status", -2));
                OplusSceneModeObserver oplusSceneModeObserver = OplusSceneModeObserver.this;
                if (oplusSceneModeObserver.isInBrickMode != zEquals) {
                    oplusSceneModeObserver.isInBrickMode = zEquals;
                    Companion companion = OplusSceneModeObserver.Companion;
                    companion.safeForeach(oplusSceneModeObserver.mCallbacks, update.1.INSTANCE);
                    boolean zEquals2 = "1".equals(Settings.Secure.getStringForUser(contentResolver, OplusSceneModeObserver.OP_LIGHT_BREATH_MODE_STATUS, -2));
                    OplusSceneModeObserver oplusSceneModeObserver2 = OplusSceneModeObserver.this;
                    if (oplusSceneModeObserver2.isInLightBrickMode != zEquals2) {
                        oplusSceneModeObserver2.isInLightBrickMode = zEquals2;
                        companion.safeForeach(oplusSceneModeObserver2.mCallbacks, update.2.INSTANCE);
                    }
                    boolean z = zEquals && !zEquals2;
                    OplusSceneModeObserver oplusSceneModeObserver3 = OplusSceneModeObserver.this;
                    if (oplusSceneModeObserver3.isInDeepBrickMode != z) {
                        oplusSceneModeObserver3.isInDeepBrickMode = z;
                        companion.safeForeach(oplusSceneModeObserver3.mCallbacks, update.3.INSTANCE);
                    }
                }
            }
            OplusSceneModeObserver oplusSceneModeObserver4 = OplusSceneModeObserver.this;
            Log.d(OplusSceneModeObserver.TAG, "update uri: " + uri + ", mIsInBrickMode: " + oplusSceneModeObserver4.isInBrickMode + ", mIsInLightBrickMode: " + oplusSceneModeObserver4.isInLightBrickMode + ", mIsInDeepBrickMode: " + oplusSceneModeObserver4.isInDeepBrickMode);
        }
    }

    public OplusSceneModeObserver(Context context) {
        this.mContext = context;
        SettingsObserver settingsObserver = new SettingsObserver(new Handler());
        this.mSettingsObserver = settingsObserver;
        Context context2 = OplusSceneModeObserver.this.mContext;
        context2.getClass();
        context2.getContentResolver().registerContentObserver(settingsObserver.mOpBrickModeStatusUri, false, settingsObserver, -1);
        settingsObserver.update(null);
    }

    public final void addCallback(Callback callback) {
        this.mCallbacks.add(callback);
    }

    public final void removeCallback(Callback callback) {
        this.mCallbacks.remove(callback);
    }
}
