package com.oplus.systemui.opthreekey.observer;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreeKeyStealthModeObserver extends ContentObserver {
    public static final String INTENT_ACTION_STEALTH_MODE_DETAIL = "oplus.safecenter.intent.action.STEALTH_INTRODUCE_ACTIVITY";
    public static final String STEALTH_MODE = "stealth_security_mode";
    private static final String TAG = "StealthModeObserver";
    private final AtomicReference<Context> mApplicationContext;
    private final AtomicBoolean mIsRegistered;
    private final AtomicBoolean mIsStealthModeOn;
    private final LinkedHashSet<Consumer<Boolean>> mObservers;
    private final Uri mStealthModeUri;
    public static final Companion Companion = new Companion();
    public static final String INTENT_PACKAGE_STEALTH_MODE_DETAIL = "com.oplus.safecenter";
    public static final String INTENT_ACTIVITY_STEALTH_MODE_DETAIL = "com.oplus.safecenter.stealth.StealthIntroduceActivity";
    private static final ComponentName INTENT_COMPONENT_STEALTH_MODE_DETAIL = new ComponentName(INTENT_PACKAGE_STEALTH_MODE_DETAIL, INTENT_ACTIVITY_STEALTH_MODE_DETAIL);
    private static final Lazy Instance$delegate = LazyKt__LazyJVMKt.lazy(new ThreeKeyStealthModeObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final ComponentName getINTENT_COMPONENT_STEALTH_MODE_DETAIL() {
            return ThreeKeyStealthModeObserver.INTENT_COMPONENT_STEALTH_MODE_DETAIL;
        }

        public final ThreeKeyStealthModeObserver getInstance() {
            return (ThreeKeyStealthModeObserver) ThreeKeyStealthModeObserver.Instance$delegate.getValue();
        }

        public final Intent getStealthModeDetailIntent() {
            return new Intent(ThreeKeyStealthModeObserver.INTENT_ACTION_STEALTH_MODE_DETAIL).setPackage(ThreeKeyStealthModeObserver.INTENT_PACKAGE_STEALTH_MODE_DETAIL).setComponent(getINTENT_COMPONENT_STEALTH_MODE_DETAIL());
        }

        public final boolean isStealthModeOn(Context context) {
            VolumeFeatureOption.INSTANCE.getClass();
            return VolumeFeatureOption.isSupportsStealthMode() && Settings.Global.getInt(context.getContentResolver(), ThreeKeyStealthModeObserver.STEALTH_MODE, -1) == 1;
        }
    }

    public ThreeKeyStealthModeObserver(Handler handler) {
        super(handler);
        this.mStealthModeUri = Settings.Global.getUriFor(STEALTH_MODE);
        this.mIsStealthModeOn = new AtomicBoolean(false);
        this.mIsRegistered = new AtomicBoolean(false);
        this.mObservers = new LinkedHashSet<>();
        this.mApplicationContext = new AtomicReference<>();
    }

    public static final ThreeKeyStealthModeObserver getInstance() {
        return Companion.getInstance();
    }

    public static final Intent getStealthModeDetailIntent() {
        return Companion.getStealthModeDetailIntent();
    }

    public final boolean addObserver(Context context, Consumer<Boolean> consumer) {
        boolean zAdd;
        synchronized (this.mObservers) {
            try {
                this.mApplicationContext.compareAndSet(null, context != null ? context.getApplicationContext() : null);
                if (this.mObservers.isEmpty()) {
                    internalRegisterObserve(this.mApplicationContext.get());
                }
                zAdd = this.mObservers.add(consumer);
            } catch (Throwable th) {
                throw th;
            }
        }
        return zAdd;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001b  */
    public final void internalRegisterObserve(Context context) {
        Context context2;
        Object obj;
        if (context != null) {
            if (this.mStealthModeUri != null) {
                VolumeFeatureOption.INSTANCE.getClass();
                if (!VolumeFeatureOption.isSupportsStealthMode() || this.mIsRegistered.get()) {
                    context2 = null;
                } else {
                    context2 = context;
                }
            } else {
                context2 = null;
            }
            if (context2 == null) {
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "StealthModeObserver-->internalRegisterObserve:");
            }
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver != null) {
                try {
                    Uri uri = this.mStealthModeUri;
                    if (uri != null) {
                        contentResolver.registerContentObserver(uri, false, this);
                    }
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                if (!(obj instanceof Result.Failure)) {
                    this.mIsRegistered.set(true);
                }
            }
            this.mIsStealthModeOn.set(Companion.isStealthModeOn(context));
        }
    }

    public final void internalUnregisterObserve(Context context) {
        ContentResolver contentResolver;
        if (this.mIsRegistered.compareAndSet(true, false)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                Log.d("SystemUi--Volume", "StealthModeObserver-->internalUnregisterObserve:");
            }
            if (context == null || (contentResolver = context.getContentResolver()) == null) {
                return;
            }
            try {
                contentResolver.unregisterContentObserver(this);
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
    }

    public final boolean isStealthModeOn() {
        VolumeFeatureOption.INSTANCE.getClass();
        return VolumeFeatureOption.isSupportsStealthMode() && this.mIsStealthModeOn.get();
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        Context context = this.mApplicationContext.get();
        if (context != null) {
            Uri uri2 = this.mStealthModeUri;
            if (uri2 == null || !uri2.equals(uri)) {
                context = null;
            }
            if (context == null) {
                return;
            }
            Companion.getClass();
            int i = Settings.Global.getInt(context.getContentResolver(), STEALTH_MODE, -1);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "current stealth mode state: ", "StealthModeObserver-->", "SystemUi--Volume");
            }
            if (i == -1) {
                return;
            }
            boolean z2 = i == 1;
            if (this.mIsStealthModeOn.compareAndSet(!z2, z2)) {
                synchronized (this.mObservers) {
                    Iterator<T> it = this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((Consumer) it.next()).accept(Boolean.valueOf(z2));
                    }
                }
            }
        }
    }

    public final boolean removeObserver(Context context, Consumer<Boolean> consumer) {
        boolean zRemove;
        synchronized (this.mObservers) {
            try {
                this.mApplicationContext.compareAndSet(null, context != null ? context.getApplicationContext() : null);
                zRemove = this.mObservers.remove(consumer);
                if (this.mObservers.isEmpty()) {
                    internalUnregisterObserve(this.mApplicationContext.get());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zRemove;
    }

    public static final boolean isStealthModeOn(Context context) {
        return Companion.isStealthModeOn(context);
    }
}
