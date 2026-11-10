package com.oplus.systemui.qs.customize;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Trace;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.navigationbar.TaskbarDelegate$1$;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.qs.QSContainerController;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileQueryHelper;
import com.android.systemui.plugins.qs.QSTileView;
import com.android.systemui.qs.QSEditEvent;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.deprecated.QSPanelController;
import com.android.systemui.qs.deprecated.QuickQSPanelController;
import com.android.systemui.qs.deprecated.customize.IQSCustomizerController;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.LargeScreenUtils;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.blur.OplusQsColorManager;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimViewDelegate;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.util.OpUtils;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSCustomizerController extends ViewController implements IQSCustomizerController {
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass2 mConfigurationListener;
    public final OplusQSCustomizerToolbar mCustomizerToolbar;
    public boolean mIsNightMode;
    public final AnonymousClass1 mKeyguardCallback;
    public final KeyguardStateController mKeyguardStateController;
    public final PlatformBlurManager mPlatformBlurManager;
    public final QSHost mQsTileHost;
    public final ScreenLifecycle mScreenLifecycle;
    public final OplusTileAdapter mTileAdapterHighLight;
    public final OplusTileAdapter mTileAdapterTop;
    public final OplusTileQueryHelper mTileQueryHelper;
    public final UiEventLogger mUiEventLogger;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.customize.OplusQSCustomizerController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements KeyguardStateController.Callback {
        public /* synthetic */ OplusQSCustomizerController this$0;

        public final void onKeyguardShowingChanged() {
            OplusQSCustomizerController oplusQSCustomizerController = this.this$0;
            if (oplusQSCustomizerController.isAttachedToWindow() && oplusQSCustomizerController.mKeyguardStateController.mShowing && !((OplusQSCustomizer) ((ViewController) oplusQSCustomizerController).mView).mOpening) {
                oplusQSCustomizerController.hideCustomizer();
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.customize.OplusQSCustomizerController$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ OplusQSCustomizerController this$0;

        public final void onConfigChanged(Configuration configuration) {
            OplusQSCustomizerController oplusQSCustomizerController = this.this$0;
            oplusQSCustomizerController.getContext().getDisplay().getRotation();
            ((OplusQSCustomizer) ((ViewController) oplusQSCustomizerController).mView).updateResources();
            TextView textView = ((OplusQSCustomizer) ((ViewController) oplusQSCustomizerController).mView).mMoreFunctionLabel;
            if (textView != null) {
                textView.setTextAppearance(2130969765);
            }
            oplusQSCustomizerController.hide(false);
        }
    }

    public OplusQSCustomizerController(OplusQSCustomizer oplusQSCustomizer, OplusTileQueryHelper oplusTileQueryHelper, QSHost qSHost, ScreenLifecycle screenLifecycle, KeyguardStateController keyguardStateController, LightBarController lightBarController, ConfigurationController configurationController, UiEventLogger uiEventLogger, OplusQsColorManager oplusQsColorManager, PlatformBlurManager platformBlurManager) {
        super(oplusQSCustomizer);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mKeyguardCallback = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mConfigurationListener = anonymousClass2;
        this.mTileQueryHelper = oplusTileQueryHelper;
        this.mQsTileHost = qSHost;
        this.mTileAdapterHighLight = oplusQSCustomizer.getTileAdapterHighLight();
        this.mTileAdapterTop = oplusQSCustomizer.getTileAdapterTop();
        this.mScreenLifecycle = screenLifecycle;
        this.mKeyguardStateController = keyguardStateController;
        this.mConfigurationController = configurationController;
        this.mUiEventLogger = uiEventLogger;
        this.mPlatformBlurManager = platformBlurManager;
        getContext().getDisplay().getRotation();
        this.mIsNightMode = OplusDarkModeUtil.isNightMode(oplusQSCustomizer.getContext());
        OplusQSCustomizerController$$ExternalSyntheticLambda2 oplusQSCustomizerController$$ExternalSyntheticLambda2 = new OplusQSCustomizerController$$ExternalSyntheticLambda2();
        oplusQSCustomizerController$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        oplusQSCustomizer.setSaveRunnable(oplusQSCustomizerController$$ExternalSyntheticLambda2);
        this.mCustomizerToolbar = oplusQSCustomizer.getToolbar();
        oplusTileQueryHelper.queryTiles();
        updateTextColor$1(QsColorUtil.getQsColorState());
        init();
    }

    public final void hide(boolean z) {
        if (((OplusQSCustomizer) ((ViewController) this).mView).mIsShown) {
            this.mPlatformBlurManager.forceStopMotionBlurRefresh(false);
            MetricsLogger.hidden(getContext(), 358);
            save();
            ((OplusQSCustomizer) ((ViewController) this).mView).hide(z);
            this.mCustomizerToolbar.dismissPopupWindow();
            this.mKeyguardStateController.removeCallback(this.mKeyguardCallback);
            ((OplusQSCustomizer) ((ViewController) this).mView).mLightBarController.setQsCustomizing(false);
        }
    }

    public final void hideCustomizer() {
        hide(this.mScreenLifecycle.getScreenState() != 0);
    }

    public final boolean isCustomizing() {
        OplusQSCustomizer oplusQSCustomizer = (OplusQSCustomizer) ((ViewController) this).mView;
        return oplusQSCustomizer.mCustomizing || oplusQSCustomizer.mOpening;
    }

    public final void onViewAttached() {
        Trace.traceBegin(8L, "OplusQSCustomizerController onViewAttached");
        this.mTileQueryHelper.setColorTileStateListener(((ViewController) this).mView);
        this.mConfigurationController.addCallback(this.mConfigurationListener);
        OplusQSCustomizerController$$ExternalSyntheticLambda0 oplusQSCustomizerController$$ExternalSyntheticLambda0 = new OplusQSCustomizerController$$ExternalSyntheticLambda0();
        oplusQSCustomizerController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        OplusQSCustomizerToolbar oplusQSCustomizerToolbar = this.mCustomizerToolbar;
        oplusQSCustomizerToolbar.setOnBackClickListener(oplusQSCustomizerController$$ExternalSyntheticLambda0);
        OplusQSCustomizerController$$ExternalSyntheticLambda1 oplusQSCustomizerController$$ExternalSyntheticLambda1 = new OplusQSCustomizerController$$ExternalSyntheticLambda1();
        oplusQSCustomizerController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        oplusQSCustomizerToolbar.setOnMoreItemClickListener(oplusQSCustomizerController$$ExternalSyntheticLambda1);
        Trace.traceEnd(8L);
    }

    public final void onViewDetached() {
        OplusTileQueryHelper oplusTileQueryHelper = this.mTileQueryHelper;
        oplusTileQueryHelper.setColorTileStateListener(null);
        this.mConfigurationController.removeCallback(this.mConfigurationListener);
        OplusQSCustomizerToolbar oplusQSCustomizerToolbar = this.mCustomizerToolbar;
        oplusQSCustomizerToolbar.setOnBackClickListener((View.OnClickListener) null);
        oplusQSCustomizerToolbar.setOnMoreItemClickListener((AdapterView.OnItemClickListener) null);
        oplusTileQueryHelper.clearCacheTile();
    }

    public final void restoreInstanceState(Bundle bundle) {
        if (bundle.getBoolean("qs_customizing")) {
            OplusQSCustomizer oplusQSCustomizer = (OplusQSCustomizer) ((ViewController) this).mView;
            3 r0 = new 3();
            r0.this$0 = this;
            VarHandle.storeStoreFence();
            oplusQSCustomizer.addOnLayoutChangeListener(r0);
        }
    }

    public final void save() {
        if (this.mTileQueryHelper.isFinished) {
            ArrayList arrayList = new ArrayList();
            OplusTileAdapter oplusTileAdapter = this.mTileAdapterHighLight;
            arrayList.addAll(oplusTileAdapter.getTiles());
            OplusTileAdapter oplusTileAdapter2 = this.mTileAdapterTop;
            arrayList.addAll(oplusTileAdapter2.getTiles());
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(oplusTileAdapter.getCurrentSpecs());
            arrayList2.addAll(oplusTileAdapter2.getCurrentSpecs());
            ArrayList arrayList3 = new ArrayList();
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i) != null && ((QSTileQueryHelper.OplusTileInfo) arrayList.get(i)).getVisible() && ((QSTileQueryHelper.OplusTileInfo) arrayList.get(i)).getSpec() != null) {
                    arrayList3.add(((QSTileQueryHelper.OplusTileInfo) arrayList.get(i)).getSpec());
                }
            }
            this.mQsTileHost.changeTilesByUser(arrayList2, arrayList3);
            if (arrayList2.equals(arrayList3)) {
                return;
            }
            boolean isEditTileBefore = OpUtils.getIsEditTileBefore();
            LogUtil.d(LogUtil.TAG_QS, "OplusQSCustomizerController", "newSpecs=" + arrayList3 + ", " + isEditTileBefore);
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isExpRegion() || isEditTileBefore) {
                return;
            }
            Context context = getContext();
            if (FeatureOption.isExpRegion()) {
                if (!OpUtils.sEditTileBefore) {
                    Settings.Secure.putIntForUser(context.getContentResolver(), "sysui_qs_edited", 1, 0);
                    Log.d("OpUtils", "edit tile");
                }
                OpUtils.sEditTileBefore = true;
            }
        }
    }

    public final void saveInstanceState(Bundle bundle) {
        if (((OplusQSCustomizer) ((ViewController) this).mView).mIsShown) {
            this.mKeyguardStateController.removeCallback(this.mKeyguardCallback);
        }
        OplusQSCustomizer oplusQSCustomizer = (OplusQSCustomizer) ((ViewController) this).mView;
        bundle.putBoolean("qs_customizing", oplusQSCustomizer.mCustomizing || oplusQSCustomizer.mOpening);
    }

    public void setContainerController(QSContainerController qSContainerController) {
        ((OplusQSCustomizer) ((ViewController) this).mView).setContainer(qSContainerController);
    }

    public void setQs(QS qs) {
        ((OplusQSCustomizer) ((ViewController) this).mView).setQs(qs);
    }

    public final void show(int i, int i2, boolean z) {
        if (!LargeScreenUtils.shouldShowEditOption(((OplusQSCustomizer) ((ViewController) this).mView).getResources())) {
            LogUtil.d(LogUtil.TAG_QS, "OplusQSCustomizerController", "can not show editor due to split notification shade.");
            return;
        }
        if (((OplusQSCustomizer) ((ViewController) this).mView).mIsShown) {
            return;
        }
        TaskbarDelegate$1$.ExternalSyntheticOutline0.m("show immediate= ", LogUtil.TAG_QS, "OplusQSCustomizerController", z);
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguardStateController;
        if (keyguardStateControllerImpl.mShowing) {
            this.mPlatformBlurManager.forceStopMotionBlurRefresh(true);
        }
        ((OplusQSCustomizer) ((ViewController) this).mView).initAdapter();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i3 = 0;
        for (QSTile qSTile : this.mQsTileHost.getTiles()) {
            if (i3 <= 1) {
                arrayList.add(qSTile.getTileSpec());
            } else {
                arrayList2.add(qSTile.getTileSpec());
            }
            i3++;
        }
        OplusTileAdapter oplusTileAdapter = this.mTileAdapterHighLight;
        oplusTileAdapter.setTileSpecs(arrayList);
        oplusTileAdapter.notifyDataSetChanged();
        OplusTileAdapter oplusTileAdapter2 = this.mTileAdapterTop;
        oplusTileAdapter2.setTileSpecs(arrayList2);
        oplusTileAdapter2.notifyDataSetChanged();
        View view = ((ViewController) this).mView;
        if (z) {
            ((OplusQSCustomizer) view).showImmediately();
        } else {
            ((OplusQSCustomizer) view).show();
            this.mUiEventLogger.log(QSEditEvent.QS_EDIT_OPEN);
        }
        this.mTileQueryHelper.queryTiles();
        keyguardStateControllerImpl.getClass();
        keyguardStateControllerImpl.addCallback(this.mKeyguardCallback);
        ((OplusQSCustomizer) ((ViewController) this).mView).mLightBarController.setQsCustomizing(false);
        updateTextColor$1(QsColorUtil.getQsColorState());
    }

    public final void showWorkTileAnime(int i, boolean z, QSPanelController qSPanelController, QuickQSPanelController quickQSPanelController) {
        if (!z || i < getContext().getResources().getInteger(2131427793) || i > getContext().getResources().getInteger(2131427792)) {
            return;
        }
        QSHost qSHost = this.mQsTileHost;
        if (qSHost == null || qSHost.indexOf("work") == -1) {
            LogUtil.d(LogUtil.TAG_QS, "OplusQSCustomizerController", "do not have work profile mode");
            return;
        }
        QSTileView tileView = null;
        QSTileView tileView2 = (qSPanelController == null || !((ViewController) qSPanelController).mView.mExpanded) ? null : qSPanelController.getTileView();
        if (tileView2 != null) {
            tileView = tileView2;
        } else if (quickQSPanelController != null) {
            tileView = quickQSPanelController.getTileView();
        }
        if (tileView != null) {
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(tileView, PropertyValuesHolder.ofFloat(MultiAnimViewDelegate.PROPERTY_SCALE_X, 0.0f, 0.1f), PropertyValuesHolder.ofFloat(MultiAnimViewDelegate.PROPERTY_SCALE_Y, 0.0f, 0.1f));
            objectAnimatorOfPropertyValuesHolder.setDuration(500L);
            ObjectAnimator objectAnimatorOfPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(tileView, PropertyValuesHolder.ofFloat(MultiAnimViewDelegate.PROPERTY_SCALE_X, 0.1f, 1.0f), PropertyValuesHolder.ofFloat(MultiAnimViewDelegate.PROPERTY_SCALE_Y, 0.1f, 1.0f));
            objectAnimatorOfPropertyValuesHolder2.setDuration(1000L);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(objectAnimatorOfPropertyValuesHolder, objectAnimatorOfPropertyValuesHolder2);
            animatorSet.start();
            CustomizeFeatureOption.setSupportWorTileAnime(false);
        }
    }

    public final void updateTextColor$1(int i) {
        LogUtil.i(LogUtil.TAG_QS, "OplusQSCustomizerController", "updateTextColor  qsColorState =" + i);
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(getContext());
        if (this.mIsNightMode != zIsNightMode) {
            this.mIsNightMode = zIsNightMode;
            ((OplusQSCustomizer) ((ViewController) this).mView).mCustomizerToolbar.resetPopupWindow();
        }
        ((OplusQSCustomizer) ((ViewController) this).mView).mCustomizerToolbar.updateResources();
        ((OplusQSCustomizer) ((ViewController) this).mView).updateLabelColor(i);
        OplusQSCustomizer oplusQSCustomizer = (OplusQSCustomizer) ((ViewController) this).mView;
        oplusQSCustomizer.mTileAdapterHighLight.setQsColorState(0);
        oplusQSCustomizer.mTileAdapterTop.setQsColorState(i);
        oplusQSCustomizer.mTileAdapterBottom.setQsColorState(i);
    }
}
