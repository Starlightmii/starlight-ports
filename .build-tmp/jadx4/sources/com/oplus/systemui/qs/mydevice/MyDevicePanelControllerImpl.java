package com.oplus.systemui.qs.mydevice;

import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.android.systemui.plugins.qs.SpanSizeInPlugin;
import com.android.systemui.qs.ExpandableNode;
import com.android.systemui.qs.QSNode;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.util.ViewController;
import com.coui.appcompat.animation.COUIMoveEaseInterpolator;
import com.oplus.systemui.qs.OplusQSContainerImplController;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.qs.pipeline.data.repository.SuperPowerSaveRepositoryImpl;
import com.oplus.systemui.qs.utils.SpringOverScroller;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.utils.DeviceUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class MyDevicePanelControllerImpl extends ViewController implements QSNode, ViewController.OnConfigurationChangedListener, IObserverListener, PluginListener {
    public static final Companion Companion = new Companion();
    private static final String HIDE_DEVICE = "false";
    private static final String QS_DEVICE_SPACE = "qs_device_space";
    private static final String SHOW_DEVICE = "true";
    private static final String TAG = "MyDevicePanelControllerImpl";
    public boolean isQsListening;
    private final KeyguardUpdateMonitorCallback mCallback;
    public boolean mCustomizing;
    private final MyDevicePanelControllerImpl$mDevicePanelShowObserver$1 mDevicePanelShowObserver;
    public boolean mDevicePanelShowState;
    private QsPanelPlugin mDevicePlugin;
    public float mExpansion;
    private Handler mHandler;
    private KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    private PluginListener<QsPanelPlugin> mPluginListener;
    private PluginManager mPluginManager;
    public boolean mRegister;
    public boolean mRemoved;
    private ExpandableNode.OnStateListener mStateListener;
    private final MyDevicePanel view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.oplus.systemui.qs.mydevice.MyDevicePanelControllerImpl$mDevicePanelShowObserver$1] */
    public MyDevicePanelControllerImpl(MyDevicePanel myDevicePanel) {
        super(myDevicePanel);
        this.view = myDevicePanel;
        this.mDevicePanelShowState = true;
        final Handler handler = new Handler(Looper.getMainLooper());
        this.mHandler = handler;
        this.mDevicePanelShowObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.qs.mydevice.MyDevicePanelControllerImpl$mDevicePanelShowObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                FeatureOption.INSTANCE.getClass();
                String str = FeatureOption.isExpRegion() ? "false" : "true";
                String stringForUser = Settings.Secure.getStringForUser(this.this$0.getContext().getContentResolver(), "qs_device_space", -2);
                if (stringForUser != null) {
                    str = stringForUser;
                }
                this.this$0.mDevicePanelShowState = str.equals("true");
                OplusNotificationLogger.logDevicePanel("MyDevicePanelControllerImpl", "onChange: QS_DEVICE_SPACE mDevicePanelShowState is: " + this.this$0.mDevicePanelShowState);
                this.this$0.updatePluginState();
                this.this$0.updateRemovedState();
                MyDevicePanelControllerImpl myDevicePanelControllerImpl = this.this$0;
                boolean z2 = myDevicePanelControllerImpl.mDevicePanelShowState;
                boolean z3 = myDevicePanelControllerImpl.isQsListening;
                boolean z4 = myDevicePanelControllerImpl.mRemoved;
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("setAlphaAnimation mDevicePanelShowState: ", " isQsListening: ", "  removed: ", z2, z3);
                sbM.append(z4);
                OplusNotificationLogger.logDevicePanel("MyDevicePanelControllerImpl", sbM.toString());
                if (!myDevicePanelControllerImpl.isQsListening || myDevicePanelControllerImpl.mRemoved) {
                    return;
                }
                MyDevicePanel view = myDevicePanelControllerImpl.getView();
                boolean z5 = myDevicePanelControllerImpl.mDevicePanelShowState;
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, z5 ? 0.0f : 1.0f, z5 ? 1.0f : 0.0f);
                objectAnimatorOfFloat.setDuration(250L);
                objectAnimatorOfFloat.setInterpolator(new COUIMoveEaseInterpolator());
                MyDevicePanelControllerImpl.setAlphaAnimation.1 r0 = new MyDevicePanelControllerImpl.setAlphaAnimation.1();
                r0.this$0 = myDevicePanelControllerImpl;
                VarHandle.storeStoreFence();
                objectAnimatorOfFloat.addListener(r0);
                objectAnimatorOfFloat.start();
            }
        };
        MyDevicePanelControllerImpl$mCallback$1 myDevicePanelControllerImpl$mCallback$1 = new MyDevicePanelControllerImpl$mCallback$1();
        myDevicePanelControllerImpl$mCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCallback = myDevicePanelControllerImpl$mCallback$1;
    }

    private final void setVisibility(boolean z) {
        ((MyDevicePanel) ((ViewController) this).mView).setVisibility((z || !this.mDevicePanelShowState) ? 4 : 0);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("MyDevicePanelController: ");
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    mExpansion: ", this.mExpansion, printWriter);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "    mCustomizing: ", this.mCustomizing);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "    mRemoved: ", this.mRemoved);
        View view = ((ViewController) this).mView;
        if (view != null) {
            BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    translationY: ", ((MyDevicePanel) view).getTranslationY(), printWriter);
            SpringOverScroller springOverScroller = ((MyDevicePanel) ((ViewController) this).mView).getSpringOverScroller();
            printWriter.println("    couiCurrY: " + (springOverScroller != null ? Integer.valueOf(springOverScroller.getCOUICurrY()) : null));
        }
    }

    public /* bridge */ /* synthetic */ View getAnimatorView() {
        return super.getAnimatorView();
    }

    public int getCollapseHeight() {
        return 0;
    }

    public int getExpandHeight() {
        MyDevicePanel myDevicePanel = (MyDevicePanel) ((ViewController) this).mView;
        if (myDevicePanel != null) {
            return myDevicePanel.getHeight();
        }
        return 0;
    }

    public /* bridge */ /* synthetic */ int getFloorCount() {
        return super.getFloorCount();
    }

    public final float getTopGap(float f) {
        return MathUtils.lerp(0, ((MyDevicePanel) ((ViewController) this).mView).mExpandGap, f);
    }

    public final void initDevicePanelShowState() {
        FeatureOption featureOption = FeatureOption.INSTANCE;
        featureOption.getClass();
        if (FeatureOption.isSupportMyDevice()) {
            SuperPowerSaveRepositoryImpl.Companion.getClass();
            if (SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
                return;
            }
            featureOption.getClass();
            String str = FeatureOption.isExpRegion() ? "false" : "true";
            String stringForUser = Settings.Secure.getStringForUser(getContext().getContentResolver(), QS_DEVICE_SPACE, -2);
            if (stringForUser != null) {
                str = stringForUser;
            }
            boolean zEquals = str.equals("true");
            this.mDevicePanelShowState = zEquals;
            OplusNotificationLogger.logDevicePanel(TAG, "initDevicePanelShowState mDevicePanelShowState: " + zEquals);
        }
    }

    public final boolean isRemoved() {
        return this.mRemoved;
    }

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        SuperPowerSaveRepositoryImpl.Companion.getClass();
        OplusNotificationLogger.logDevicePanel(TAG, "onChange SuperPowerSaveModel: " + SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel());
        initDevicePanelShowState();
        updatePluginState();
        updateRemovedState();
    }

    public void onConfigurationChange(Configuration configuration) {
        updateRemovedState();
    }

    public final void onInit() {
        if (((ViewController) this).mView != null) {
            OplusNotificationLogger.logDevicePanel(TAG, "MyDevicePanelController onInit");
            ((MyDevicePanel) ((ViewController) this).mView).setOnConfigurationChangeListener(this);
            initDevicePanelShowState();
            updateRemovedState();
            QSFeatureOption.INSTANCE.getClass();
            if (QSFeatureOption.isSuperPowerModeSupport) {
                SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(this);
            }
            updatePluginState();
        }
    }

    public void onPluginConnected(QsPanelPlugin qsPanelPlugin, Context context) {
        OplusNotificationLogger.logDevicePanel(TAG, "onPluginConnected sysuiContext: " + getContext() + " pluginContext: " + context);
        this.mDevicePlugin = qsPanelPlugin;
        View view = ((ViewController) this).mView;
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup != null && qsPanelPlugin != null) {
            SpanSizeInPlugin spanSizeInPlugin = new SpanSizeInPlugin(0, 0, 3, (DefaultConstructorMarker) null);
            Bundle bundle = new Bundle();
            bundle.putInt("device_mode_key", 0);
            qsPanelPlugin.onCreateView(viewGroup, spanSizeInPlugin, bundle);
        }
        OplusNotificationLogger.logDevicePanel(TAG, "parentViewGroup: " + viewGroup + " width: " + (viewGroup != null ? Integer.valueOf(viewGroup.getWidth()) : null) + " height: " + (viewGroup != null ? Integer.valueOf(viewGroup.getHeight()) : null));
    }

    public void onPluginDisconnected(QsPanelPlugin qsPanelPlugin) {
        OplusNotificationLogger.logDevicePanel(TAG, "onPluginDisconnected");
        this.mDevicePlugin = null;
    }

    public final void onViewAttached() {
        ContentResolver contentResolver;
        OplusNotificationLogger.logDevicePanel(TAG, "onViewAttached");
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportMyDevice() && (contentResolver = getContext().getContentResolver()) != null) {
            contentResolver.registerContentObserver(Settings.Secure.getUriFor(QS_DEVICE_SPACE), false, this.mDevicePanelShowObserver, -1);
        }
    }

    public final void onViewDetached() {
        ContentResolver contentResolver;
        OplusNotificationLogger.logDevicePanel(TAG, "onViewDetached");
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSuperPowerModeSupport) {
            SuperPowerSaveSettingsObserver.Companion.getInstance().removeListener(this);
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportMyDevice() && (contentResolver = getContext().getContentResolver()) != null) {
            contentResolver.unregisterContentObserver(this.mDevicePanelShowObserver);
        }
        unRegisterPluginState();
        this.isQsListening = false;
    }

    public void setContainer(ExpandableNode.OnStateListener onStateListener) {
        this.mStateListener = onStateListener;
    }

    public void setCustomizing(boolean z) {
        this.mCustomizing = z;
        updateRemovedState();
    }

    public final void setDeviceListening(boolean z) {
        QsPanelPlugin qsPanelPlugin;
        QsPanelPlugin qsPanelPlugin2 = this.mDevicePlugin;
        if (qsPanelPlugin2 != null) {
            qsPanelPlugin2.setListening(z);
        }
        this.isQsListening = z;
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet() && DeviceUtil.isLandscape(getContext()) && !z && (qsPanelPlugin = this.mDevicePlugin) != null) {
            qsPanelPlugin.setQsExpansion(0.0f);
        }
        if (z || this.mDevicePlugin != null) {
            return;
        }
        View view = ((ViewController) this).mView;
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup == null || viewGroup.getChildCount() <= 0) {
            return;
        }
        OplusNotificationLogger.logDevicePanel(TAG, "setDeviceListening: remove myDevice view!");
        viewGroup.removeAllViews();
    }

    public /* bridge */ /* synthetic */ void setExpanded(boolean z) {
        super.setExpanded(z);
    }

    public void setExpansion(float f) {
        this.mExpansion = f;
        ((MyDevicePanel) ((ViewController) this).mView).setExpansion(f);
        updateRemovedState();
    }

    public /* bridge */ /* synthetic */ void setListening(boolean z) {
        super.setListening(z);
    }

    public final void setQsDeviceExpansion(float f) {
        QsPanelPlugin qsPanelPlugin;
        if ((!this.mRemoved || f <= 0.65f) && (qsPanelPlugin = this.mDevicePlugin) != null) {
            qsPanelPlugin.setQsExpansion(f);
        }
    }

    public /* bridge */ /* synthetic */ float setTranslationY(float f, int i, Function1 function1) {
        return super.setTranslationY(f, i, function1);
    }

    public final boolean shouldHideInSplitMode() {
        return true;
    }

    public final void unRegisterPluginState() {
        OplusNotificationLogger.logDevicePanel(TAG, "on unRegisterPluginState removePluginListener");
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
        if (keyguardUpdateMonitor != null) {
            keyguardUpdateMonitor.removeCallback(this.mCallback);
        }
        this.mKeyguardUpdateMonitor = null;
        PluginManager pluginManager = this.mPluginManager;
        if (pluginManager != null) {
            pluginManager.removePluginListener(this);
        }
        this.mPluginManager = null;
    }

    public final void updatePluginState() {
        OplusNotificationLogger.logDevicePanel(TAG, "updatePluginState mDevicePanelShowState: " + this.mDevicePanelShowState + " mRegister: " + this.mRegister);
        if (this.mDevicePanelShowState) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isSupportMyDevice()) {
                SuperPowerSaveRepositoryImpl.Companion.getClass();
                if (!SuperPowerSaveRepositoryImpl.Companion.isSuperPowerSaveModel()) {
                    if (this.mRegister) {
                        return;
                    }
                    OplusNotificationLogger.logDevicePanel(TAG, "on registerPluginState create mPluginManager and addPluginListener");
                    this.mPluginManager = (PluginManager) Dependency.sDependency.getDependencyInner(PluginManager.class);
                    this.mPluginListener = this;
                    this.mKeyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
                    PluginManager pluginManager = this.mPluginManager;
                    if (pluginManager != null) {
                        pluginManager.addPluginListener(this, QsPanelPlugin.class, true);
                    }
                    KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
                    if (keyguardUpdateMonitor != null) {
                        keyguardUpdateMonitor.registerCallback(this.mCallback);
                    }
                    this.mRegister = true;
                    return;
                }
            }
        }
        unRegisterPluginState();
        this.mRegister = false;
    }

    public final void updateRemovedState() {
        boolean z = this.mExpansion < 0.65f || (DeviceUtil.isLandscape(getContext()) && !DeviceUtil.shouldUseLargeFoldConfig(getResources())) || SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
        if (this.mRemoved != z) {
            this.mRemoved = z;
            ExpandableNode.OnStateListener onStateListener = this.mStateListener;
            if (onStateListener != null) {
                ((OplusQSContainerImplController) onStateListener).updatePosition$2();
            }
            OplusNotificationLogger.logDevicePanel(TAG, "remove status change: " + this.mRemoved);
            float f = (this.mRemoved || ((SysuiStatusBarStateController) DependencyEx.sDependency.getDependency(SysuiStatusBarStateController.class)).getCurrentOrUpcomingState() == 1) ? 0.0f : 1.0f;
            updateViewState(f, f);
        }
        setVisibility(this.mCustomizing || z);
    }

    public MyDevicePanel getView() {
        return this.view;
    }
}
