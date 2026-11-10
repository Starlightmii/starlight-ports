package com.oplus.systemui.navigationbar.observer;

import android.content.Context;
import android.net.Uri;
import android.os.Trace;
import androidx.exifinterface.media.ExifInterface$;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemFoldModeObserver extends AbstractObserver {
    private static String SYSTEM_FOLDING_MODE_KEYS = "oplus_system_folding_mode";
    private static String TAG = "SystemFoldModeObserver";
    private AtomicInteger mFolddingMode;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new SystemFoldModeObserver$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SystemFoldModeObserver getInstance() {
            return (SystemFoldModeObserver) SystemFoldModeObserver.instance$delegate.getValue();
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$BVEIW0gaOFkei8BivS4i-HTL2-0, reason: not valid java name */
    public static SystemFoldModeObserver m34$r8$lambda$BVEIW0gaOFkei8BivS4iHTL20() {
        SystemFoldModeObserver systemFoldModeObserver = new SystemFoldModeObserver();
        systemFoldModeObserver.mFolddingMode = new AtomicInteger(-1);
        return systemFoldModeObserver;
    }

    public final int getFoldMode() {
        return this.mFolddingMode.get();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return SYSTEM_FOLDING_MODE_KEYS;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Uri getUri(Context context) {
        return MultiUserContentHelper.Companion.getGlobalUri(getKey());
    }

    public final boolean isFoldOpen() {
        return this.mFolddingMode.get() == 1;
    }

    public final boolean isFolding() {
        return this.mFolddingMode.get() == 0;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        Trace.traceBegin(8L, "SystemFoldModeObserver.onChange");
        Context context = getContext();
        if (context != null) {
            int globalIntValue = AbsSettingsValueProxy.Companion.getGlobalIntValue(context, SYSTEM_FOLDING_MODE_KEYS, -1);
            ExifInterface$.ExternalSyntheticOutline1.m(globalIntValue, "SystemFoldModeObserver onChanged, folddingMode=", TAG);
            if (globalIntValue != this.mFolddingMode.get()) {
                this.mFolddingMode.set(globalIntValue);
                super.onChange(z);
            }
        }
        Trace.traceEnd(8L);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        this.mFolddingMode.set(AbsSettingsValueProxy.Companion.getGlobalIntValue(context, SYSTEM_FOLDING_MODE_KEYS, -1));
    }
}
