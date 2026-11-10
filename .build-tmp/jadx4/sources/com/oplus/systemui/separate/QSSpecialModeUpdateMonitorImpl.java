package com.oplus.systemui.separate;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.android.systemui.LauncherProxyService;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.qs.OplusQsColorManagerEx;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.systemui.blur.OplusQsColorManager;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import oplus.content.res.OplusFontUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSSpecialModeUpdateMonitorImpl implements QSSpecialModeUpdateMonitor, ConfigurationController.ConfigurationListener {
    public boolean applyDynamicFontVariationConfig;
    public boolean applyFlipFontConfig;
    private final ConfigurationController configurationController;
    private final Context context;
    private final QSSpecialModeUpdateMonitorImpl$displayChangeListener$1 displayChangeListener;
    public boolean isGestureNavigation;
    private final QSSpecialModeUpdateMonitorImpl$keyguardStateCallback$1 keyguardStateCallback;
    private final KeyguardStateController keyguardStateController;
    public int lastRotation;
    private final Executor mainExecutor;
    private final NavigationModeController navigationModeController;
    private final OplusQsColorManagerEx.QsColorStateObserver qsColorObserver;
    private final QSSpecialModeUpdateMonitorImpl$superPowerObserver$1 superPowerObserver;
    private final QSSpecialModeUpdateMonitorImpl$taskbarVisibilityListener$1 taskbarVisibilityListener;
    private final UserContextProvider userContextProvider;
    private final UserTracker.Callback userTrackerCallback;
    private final Context windowContext;
    private final CopyOnWriteArrayList<QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener> listeners = new CopyOnWriteArrayList<>();
    public boolean taskbarVisible = true;

    public QSSpecialModeUpdateMonitorImpl(Context context, Context context2, ConfigurationController configurationController, NavigationModeController navigationModeController, UserContextProvider userContextProvider, LauncherProxyService launcherProxyService, OplusQsColorManager oplusQsColorManager, UserTracker userTracker, DisplayTracker displayTracker, KeyguardStateController keyguardStateController, Executor executor) {
        Configuration configuration;
        this.context = context;
        this.windowContext = context2;
        this.configurationController = configurationController;
        this.navigationModeController = navigationModeController;
        this.userContextProvider = userContextProvider;
        this.keyguardStateController = keyguardStateController;
        this.mainExecutor = executor;
        this.isGestureNavigation = true;
        QSSpecialModeUpdateMonitorImpl$superPowerObserver$1 qSSpecialModeUpdateMonitorImpl$superPowerObserver$1 = new QSSpecialModeUpdateMonitorImpl$superPowerObserver$1();
        qSSpecialModeUpdateMonitorImpl$superPowerObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.superPowerObserver = qSSpecialModeUpdateMonitorImpl$superPowerObserver$1;
        QSSpecialModeUpdateMonitorImpl$keyguardStateCallback$1 qSSpecialModeUpdateMonitorImpl$keyguardStateCallback$1 = new QSSpecialModeUpdateMonitorImpl$keyguardStateCallback$1();
        qSSpecialModeUpdateMonitorImpl$keyguardStateCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardStateCallback = qSSpecialModeUpdateMonitorImpl$keyguardStateCallback$1;
        QSSpecialModeUpdateMonitorImpl$taskbarVisibilityListener$1 qSSpecialModeUpdateMonitorImpl$taskbarVisibilityListener$1 = new QSSpecialModeUpdateMonitorImpl$taskbarVisibilityListener$1();
        qSSpecialModeUpdateMonitorImpl$taskbarVisibilityListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.taskbarVisibilityListener = qSSpecialModeUpdateMonitorImpl$taskbarVisibilityListener$1;
        QSSpecialModeUpdateMonitorImpl$qsColorObserver$1 qSSpecialModeUpdateMonitorImpl$qsColorObserver$1 = new QSSpecialModeUpdateMonitorImpl$qsColorObserver$1();
        qSSpecialModeUpdateMonitorImpl$qsColorObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.qsColorObserver = qSSpecialModeUpdateMonitorImpl$qsColorObserver$1;
        QSSpecialModeUpdateMonitorImpl$userTrackerCallback$1 qSSpecialModeUpdateMonitorImpl$userTrackerCallback$1 = new QSSpecialModeUpdateMonitorImpl$userTrackerCallback$1();
        qSSpecialModeUpdateMonitorImpl$userTrackerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userTrackerCallback = qSSpecialModeUpdateMonitorImpl$userTrackerCallback$1;
        QSSpecialModeUpdateMonitorImpl$currentMode$1 qSSpecialModeUpdateMonitorImpl$currentMode$1 = new QSSpecialModeUpdateMonitorImpl$currentMode$1();
        qSSpecialModeUpdateMonitorImpl$currentMode$1.this$0 = this;
        VarHandle.storeStoreFence();
        int iAddListener = navigationModeController.addListener(qSSpecialModeUpdateMonitorImpl$currentMode$1);
        QSSpecialModeUpdateMonitorImpl$displayChangeListener$1 qSSpecialModeUpdateMonitorImpl$displayChangeListener$1 = new QSSpecialModeUpdateMonitorImpl$displayChangeListener$1();
        qSSpecialModeUpdateMonitorImpl$displayChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.displayChangeListener = qSSpecialModeUpdateMonitorImpl$displayChangeListener$1;
        configurationController.addCallback(this);
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(qSSpecialModeUpdateMonitorImpl$superPowerObserver$1);
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
        keyguardStateControllerImpl.getClass();
        keyguardStateControllerImpl.addCallback(qSSpecialModeUpdateMonitorImpl$keyguardStateCallback$1);
        this.isGestureNavigation = QuickStepContract.isGesturalMode(iAddListener);
        launcherProxyService.addCallback(qSSpecialModeUpdateMonitorImpl$taskbarVisibilityListener$1);
        oplusQsColorManager.addQsColorStateObserver(qSSpecialModeUpdateMonitorImpl$qsColorObserver$1);
        QSFragmentHelper.getInstance().applyAccentColor(context);
        userTracker.addCallback(qSSpecialModeUpdateMonitorImpl$userTrackerCallback$1, executor);
        Resources resources = context.getResources();
        if (resources != null && (configuration = resources.getConfiguration()) != null) {
            QSFragmentHelper.getInstance().applyDynamicFontVariationConfig(configuration);
            QSFragmentHelper.getInstance().applyFlipFontConfig(configuration);
        }
        displayTracker.addDisplayChangeCallback(qSSpecialModeUpdateMonitorImpl$displayChangeListener$1, executor);
    }

    public void addListener(QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener iQSSpecialModeChangeListener) {
        this.listeners.add(iQSSpecialModeChangeListener);
    }

    public final void destroy() {
        this.listeners.clear();
    }

    public int getActiveColor() {
        return QSFragmentHelper.getInstance().getActiveColorWithDarkMode(this.context);
    }

    public Context getUserContext() {
        return this.userContextProvider.getUserContext();
    }

    public Context getWindowContext() {
        return this.windowContext;
    }

    public final boolean isApplyDynamicFontVariationConfig() {
        return this.applyDynamicFontVariationConfig;
    }

    public final boolean isApplyFlipFontConfig() {
        return this.applyFlipFontConfig;
    }

    public final boolean isGestureNavigation() {
        return this.isGestureNavigation;
    }

    public final boolean isKeyguardShowing() {
        return this.keyguardStateController.mShowing;
    }

    public final boolean isSideGestureBarHide() {
        return NavBarUtils.isSideGestureBarHide();
    }

    public final boolean isSuperPowerSaveMode() {
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        return SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel();
    }

    public final boolean isTaskbarVisible() {
        return this.taskbarVisible;
    }

    public void onConfigChanged(Configuration configuration) {
        QSFragmentHelper.getInstance().applyAccentColor(this.context);
        this.applyDynamicFontVariationConfig = QSFragmentHelper.getInstance().applyDynamicFontVariationConfig(configuration);
        this.applyFlipFontConfig = QSFragmentHelper.getInstance().applyFlipFontConfig(configuration);
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener) it.next()).onConfigChanged(configuration);
        }
    }

    public final void onDensityOrFontScaleChanged() {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener) it.next()).onDensityOrFontScaleChanged();
        }
    }

    public void onFontChanged(Configuration configuration) {
        SystemTypefaceHelper.Companion.getInstance(this.context).getClass();
        boolean z = !OplusFontUtils.isFlipFontUsed;
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener) it.next()).onFontChanged(z);
        }
    }

    public final void onLayoutDirectionChanged(boolean z) {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener) it.next()).onLayoutDirectionChanged(z);
        }
    }

    public final void onLocaleListChanged() {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener) it.next()).onLocaleListChanged();
        }
    }

    public final void onOverlayChanged() {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener) it.next()).onOverlayChanged();
        }
    }

    public final void onUiModeChanged() {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener) it.next()).onUiModeChanged();
        }
    }

    public void removeListener(QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener iQSSpecialModeChangeListener) {
        this.listeners.remove(iQSSpecialModeChangeListener);
    }
}
