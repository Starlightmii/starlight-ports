package com.oplus.systemui.notification.notificationcenter.antivoyeur;

import android.content.Context;
import android.util.Log;
import androidx.car.app.model.constraints.ActionsConstraints$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl$callbackEvents$1$1$;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AonConfigMangerImpl implements AonConfigManger {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AonConfigManger";
    private final SmartAntiVoyeurAppListManager appListManager;
    private final AntiVoyeurConfig config;
    private final Context context;
    private final Lazy encryptAppObserver$delegate;
    private final Lazy faceEnrollObserver$delegate;
    private final Lazy faceForEncryptObserver$delegate;
    public final int userId;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$9woODuDsjZ96-6EUj3npDwLzsV8, reason: not valid java name */
    public static Unit m93$r8$lambda$9woODuDsjZ966EUj3npDwLzsV8(AonConfigMangerImpl aonConfigMangerImpl) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileConnectionRepositoryImpl$callbackEvents$1$1$.ExternalSyntheticOutline0.m(aonConfigMangerImpl.userId, "encryptAppObserver(", "): onChange", "AonConfigManger-->", "SystemUi--Notification");
        }
        ((SmartAntiVoyeurAppListManagerImpl) aonConfigMangerImpl.appListManager).notifyEncryptAppsChange();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$afCi4fnalcA3Q-m1tHDUIpY3qvE, reason: not valid java name */
    public static Unit m94$r8$lambda$afCi4fnalcA3Qm1tHDUIpY3qvE(AonConfigMangerImpl aonConfigMangerImpl) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int i = aonConfigMangerImpl.userId;
            boolean faceForAppEncryptState = aonConfigMangerImpl.config.getFaceForAppEncryptState();
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("AonConfigManger-->", "SystemUi--Notification", KeyguardLogger$.ExternalSyntheticOutline0.m("faceForEncryptObserver(", i, "): onChange ", OplusPreventModeData.SEPARATOR, faceForAppEncryptState), aonConfigMangerImpl.config.getSuperSavePowerModeState());
        }
        if (!aonConfigMangerImpl.config.getFaceForAppEncryptState() && !aonConfigMangerImpl.config.getSuperSavePowerModeState()) {
            aonConfigMangerImpl.config.setAllowAntiVoyeurEncryptedAppState(false);
            ((SmartAntiVoyeurAppListManagerImpl) aonConfigMangerImpl.appListManager).removeAllEncryptedAppFromSettings();
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$prjC7BPYpH5Zty1dFMtXHWgmFR0(AonConfigMangerImpl aonConfigMangerImpl) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", MobileInputLogger$.ExternalSyntheticOutline0.m("faceEnrollObserver(", aonConfigMangerImpl.userId, "): onChange ", "AonConfigManger-->", aonConfigMangerImpl.config.getFaceEnrollState()));
        }
        if (!aonConfigMangerImpl.config.getFaceEnrollState()) {
            aonConfigMangerImpl.config.setAllowAntiVoyeurEncryptedState(false);
        }
        return Unit.INSTANCE;
    }

    public AonConfigMangerImpl(Context context) {
        this.context = context;
        this.userId = context.getUserId();
        AntiVoyeurConfig antiVoyeurConfig = new AntiVoyeurConfig(context);
        this.config = antiVoyeurConfig;
        this.appListManager = SmartAntiVoyeurAppListManager.Companion.create(context, antiVoyeurConfig);
        AonConfigMangerImpl$$ExternalSyntheticLambda0 aonConfigMangerImpl$$ExternalSyntheticLambda0 = new AonConfigMangerImpl$$ExternalSyntheticLambda0(0);
        aonConfigMangerImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.faceForEncryptObserver$delegate = LazyKt__LazyJVMKt.lazy(aonConfigMangerImpl$$ExternalSyntheticLambda0);
        AonConfigMangerImpl$$ExternalSyntheticLambda0 aonConfigMangerImpl$$ExternalSyntheticLambda1 = new AonConfigMangerImpl$$ExternalSyntheticLambda0(1);
        aonConfigMangerImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.encryptAppObserver$delegate = LazyKt__LazyJVMKt.lazy(aonConfigMangerImpl$$ExternalSyntheticLambda1);
        AonConfigMangerImpl$$ExternalSyntheticLambda0 aonConfigMangerImpl$$ExternalSyntheticLambda2 = new AonConfigMangerImpl$$ExternalSyntheticLambda0(2);
        aonConfigMangerImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.faceEnrollObserver$delegate = LazyKt__LazyJVMKt.lazy(aonConfigMangerImpl$$ExternalSyntheticLambda2);
    }

    private final UriProviderObserver getEncryptAppObserver() {
        return (UriProviderObserver) this.encryptAppObserver$delegate.getValue();
    }

    private final UriProviderObserver getFaceEnrollObserver() {
        return (UriProviderObserver) this.faceEnrollObserver$delegate.getValue();
    }

    private final UriProviderObserver getFaceForEncryptObserver() {
        return (UriProviderObserver) this.faceForEncryptObserver$delegate.getValue();
    }

    @Override // com.oplus.systemui.notification.notificationcenter.antivoyeur.AonConfigManger
    public void notifyPkgRemoved(String str, boolean z) {
        this.appListManager.notifyPkgRemoved(str, z);
    }

    @Override // com.oplus.systemui.notification.notificationcenter.antivoyeur.AonConfigManger
    public void setAllowAntiVoyeurEncryptedState(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", MobileInputLogger$.ExternalSyntheticOutline0.m("setAllowAntiVoyeurEncryptedState(", this.userId, ") state: ", "AonConfigManger-->", z));
        }
        this.config.setAllowAntiVoyeurEncryptedState(z);
    }

    public final void start() {
        boolean aonAntiEnable = this.config.getAonAntiEnable();
        int i = this.userId;
        if (!aonAntiEnable) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ActionsConstraints$.ExternalSyntheticOutline0.m(i, "start(", "): isAonAntiEnable==false"), null, 8);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ActionsConstraints$.ExternalSyntheticOutline0.m(i, "start(", "): isAonAntiEnable"), null, 8);
        try {
            getFaceForEncryptObserver().register(this.context);
        } catch (SecurityException e) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, TAG, "faceForEncrypt SecurityException", e);
        }
        try {
            getEncryptAppObserver().register(this.context);
        } catch (SecurityException e2) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, TAG, "SecurityException", e2);
        }
    }

    public final void stop() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ActionsConstraints$.ExternalSyntheticOutline0.m(this.userId, "stop(", "): "), null, 8);
        getFaceForEncryptObserver().unRegister(this.context);
        getEncryptAppObserver().unRegister(this.context);
    }
}
