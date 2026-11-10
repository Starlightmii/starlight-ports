package com.oplus.systemui.notification.material;

import android.content.Context;
import android.util.Log;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.coui.appcompat.COUIMaterialStateManager;
import com.coui.appcompat.OnMaterialStateChangeListener;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightEffectManager implements OnMaterialStateChangeListener {
    private static final String TAG = "NotificationSpotLightEffectManager";
    public static boolean isPanoramicAod;
    public static boolean spotLightEnabled;
    public static final NotificationSpotLightEffectManager INSTANCE = new NotificationSpotLightEffectManager();
    private static final CopyOnWriteArrayList<Runnable> availabilityListeners = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<Runnable> uiModeListeners = new CopyOnWriteArrayList<>();
    private static final NotificationSpotLightEffectManager$configurationListener$1 configurationListener = new NotificationSpotLightEffectManager$configurationListener$1();
    private static final Lazy configurationController$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, new NotificationSpotLightEffectManager$$ExternalSyntheticLambda0());
    private static final OplusPanoramicAodRepository.PanoramicAodProgressChangeListener panoramicAodObserver = new NotificationSpotLightEffectManager$panoramicAodObserver$1();

    public static final void access$notifyUiModeChanged(NotificationSpotLightEffectManager notificationSpotLightEffectManager) {
        notificationSpotLightEffectManager.getClass();
        Iterator<T> it = uiModeListeners.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }

    public static final void addOnSpotLightAvailabilityChangedListener(Runnable runnable) {
        availabilityListeners.add(runnable);
    }

    public static final void addOnSpotLightUiModeChangedListener(Runnable runnable) {
        uiModeListeners.add(runnable);
    }

    private final ConfigurationController getConfigurationController() {
        return (ConfigurationController) configurationController$delegate.getValue();
    }

    public static final void init(Context context, dagger.Lazy lazy) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "[NotifCard-SpotLight] init, context=" + context, null, 8);
        NotificationSpotLightEffectManager notificationSpotLightEffectManager = INSTANCE;
        ConfigurationController configurationController = notificationSpotLightEffectManager.getConfigurationController();
        if (configurationController != null) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "[NotifCard-SpotLight] add configurationListener", null, 8);
            configurationController.addCallback(configurationListener);
        }
        try {
            COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
            cOUIMaterialStateManager.getClass();
            if (COUIMaterialStateManager.materialVersionCheck()) {
                NotificationSpotLightDelegate.Companion.init();
                cOUIMaterialStateManager.registerMaterialStateObserver(context, 7);
                cOUIMaterialStateManager.addOnMaterialStateChangedListener(notificationSpotLightEffectManager);
                ((OplusPanoramicAodRepository) lazy.get()).addCallback(panoramicAodObserver);
                syncCouiSpotLightFromSystem();
                NotificationSpotLightParams.Companion.init(context);
            }
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public static final void removeOnSpotLightAvailabilityChangedListener(Runnable runnable) {
        availabilityListeners.remove(runnable);
    }

    public static final void removeOnSpotLightUiModeChangedListener(Runnable runnable) {
        uiModeListeners.remove(runnable);
    }

    public static void syncCouiSpotLightFromSystem() {
        COUIMaterialStateManager cOUIMaterialStateManager = COUIMaterialStateManager.INSTANCE;
        cOUIMaterialStateManager.getClass();
        if (COUIMaterialStateManager.materialVersionCheck() && ScrimUtil.isMiddleOrHighAnimLevel((Context) DependencyEx.sDependency.getDependency(Context.class)) && FeatureOption.getEnableLightFeature() && !isPanoramicAod) {
            spotLightEnabled = cOUIMaterialStateManager.getMATERIAL_SPOTLIGHT_ENABLE$coui_support_effect_release();
        } else {
            spotLightEnabled = false;
        }
        Iterator<T> it = availabilityListeners.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }

    public CharSequence onDump() {
        return "OplusNotificationSpotLightEffectManager";
    }

    public final void onMaterialStateChange(int i, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", MobileInputLogger$.ExternalSyntheticOutline0.m("[NotifCard-SpotLight] onMaterialStateChange flag=", i, ", newState=", "NotificationSpotLightEffectManager-->", z));
        }
        if (i == 1 || i == 4) {
            syncCouiSpotLightFromSystem();
        }
    }
}
