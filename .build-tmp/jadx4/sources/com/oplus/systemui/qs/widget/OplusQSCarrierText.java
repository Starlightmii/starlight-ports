package com.oplus.systemui.qs.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.connectivity.IconState;
import com.android.systemui.statusbar.connectivity.MobileDataIndicators;
import com.android.systemui.statusbar.connectivity.SignalCallback;
import com.android.systemui.statusbar.connectivity.WifiIndicators;
import com.oplus.systemui.qs.OplusQSCarrierTextController;
import com.oplus.systemui.qs.OplusQSCarrierTextControllerEx;
import com.oplus.systemui.qs.helper.OplusQSFontHelper;
import com.oplus.systemui.qs.observer.DataUsageObserver;
import com.oplus.systemui.qs.observer.DataUsageUsedObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsSettingsValueProxy;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.lang.invoke.VarHandle;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSCarrierText extends AppCompatTextView implements SignalCallback, View.OnClickListener {
    public final ActivityStarter mActivityStarter;
    public boolean mAirplaneMode;
    public boolean mAnySimReady;
    public OplusQSCarrierTextController.CarrierTextCallback mCarrierTextCallback;
    public final OplusQSCarrierTextController mCarrierTextController;
    public final AnonymousClass2 mDataUsageObserver;
    public boolean mDataUsageShow;
    public CharSequence mDataUsageString;
    public final AnonymousClass3 mDataUsageUsedObserver;
    public boolean mIsAdaptiveSize;
    public CharSequence mLastText;
    public boolean mListening;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.widget.OplusQSCarrierText$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements OplusQSCarrierTextController.CarrierTextCallback {
        public /* synthetic */ OplusQSCarrierText this$0;

        @Override // com.oplus.systemui.qs.OplusQSCarrierTextController.CarrierTextCallback
        public /* bridge */ /* synthetic */ void setVisible(boolean z) {
            super.setVisible(z);
        }

        /* JADX WARN: Type inference failed for: r2v1, types: [android.widget.TextView, com.oplus.systemui.qs.widget.OplusQSCarrierText] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // com.oplus.systemui.qs.OplusQSCarrierTextController.CarrierTextCallback
        public final void updateCarrierInfo(OplusQSCarrierTextController.OplusCarrierTextCallbackInfo oplusCarrierTextCallbackInfo) {
            ?? r2 = this.this$0;
            if (r2.mListening) {
                r2.mAnySimReady = oplusCarrierTextCallbackInfo.anySimReady;
                r2.mAirplaneMode = oplusCarrierTextCallbackInfo.airplaneMode;
                r2.mDataUsageString = oplusCarrierTextCallbackInfo.dataUsage;
                LogUtil.d(LogUtil.TAG_QS, "OplusQSCarrierText", "updateCarrierInfo: mDataUsageShow=" + r2.mDataUsageShow + ";mAnySimReady=" + r2.mAnySimReady + ";mAirplaneMode=" + r2.mAirplaneMode);
                boolean zIsSimEnabledShowDataUsage = r2.isSimEnabledShowDataUsage();
                CharSequence charSequence = r2.mLastText;
                if (zIsSimEnabledShowDataUsage) {
                    if (charSequence.equals(r2.mDataUsageString)) {
                        return;
                    }
                    r2.setText(r2.mDataUsageString);
                    r2.setClickable(KeyguardUpdateMonitor.getCurrentUser() == 0);
                    r2.mLastText = r2.mDataUsageString;
                    return;
                }
                if (charSequence.equals("")) {
                    return;
                }
                r2.setText("");
                r2.setClickable(false);
                r2.mLastText = "";
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: -$$Nest$mupdateDataUsageShow, reason: not valid java name */
    public static void m352$$Nest$mupdateDataUsageShow(OplusQSCarrierText oplusQSCarrierText) {
        boolean zIsDataUsageShow = QsSettingsValueProxy.isDataUsageShow(((TextView) oplusQSCarrierText).mContext, 0);
        oplusQSCarrierText.mDataUsageShow = zIsDataUsageShow;
        oplusQSCarrierText.mCarrierTextController.setShowDataUsage(zIsDataUsageShow);
        oplusQSCarrierText.updateEverything();
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [com.oplus.systemui.qs.widget.OplusQSCarrierText$2] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.oplus.systemui.qs.widget.OplusQSCarrierText$3] */
    public OplusQSCarrierText(Context context, AttributeSet attributeSet, ActivityStarter activityStarter) {
        super(context, attributeSet);
        this.mDataUsageShow = false;
        this.mLastText = "";
        this.mIsAdaptiveSize = true;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCarrierTextCallback = anonymousClass1;
        this.mDataUsageObserver = new DataUsageObserver() { // from class: com.oplus.systemui.qs.widget.OplusQSCarrierText.2
            @Override // com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                OplusQSCarrierText.m352$$Nest$mupdateDataUsageShow(OplusQSCarrierText.this);
            }
        };
        this.mDataUsageUsedObserver = new DataUsageUsedObserver() { // from class: com.oplus.systemui.qs.widget.OplusQSCarrierText.3
            @Override // com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                OplusQSCarrierText.m352$$Nest$mupdateDataUsageShow(OplusQSCarrierText.this);
            }
        };
        this.mActivityStarter = activityStarter;
        this.mCarrierTextController = (OplusQSCarrierTextController) DependencyEx.sDependency.getDependency(OplusQSCarrierTextController.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean hasOverlappingRendering() {
        if (isSimEnabledShowDataUsage()) {
            return false;
        }
        return super/*android.widget.TextView*/.hasOverlappingRendering();
    }

    public final boolean isSimEnabledShowDataUsage() {
        return this.mDataUsageShow && !this.mAirplaneMode && this.mAnySimReady && !TextUtils.isEmpty(this.mDataUsageString) && OplusQSCarrierTextController.isAnySimEnabled();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.TextView*/.onAttachedToWindow();
        this.mAirplaneMode = QsSettingsValueProxy.isAirplaneModeOn(((TextView) this).mContext);
        this.mDataUsageShow = QsSettingsValueProxy.isDataUsageShow(((TextView) this).mContext, 0);
        registerForUserSwitch(((TextView) this).mContext, -1, false);
        registerForUserSwitch(((TextView) this).mContext, -1, false);
        setOnClickListener(this);
        this.mCarrierTextController.setListening(this.mCarrierTextCallback);
        this.mCarrierTextController.setShowDataUsage(this.mDataUsageShow);
        updateEverything();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.mDataUsageShow && !this.mAirplaneMode && this.mAnySimReady && !TextUtils.isEmpty(this.mDataUsageString) && KeyguardUpdateMonitor.getCurrentUser() == 0) {
            LogUtil.d(LogUtil.TAG_QS, "OplusQSCarrierText", "clicked ------");
            this.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("oplus.datausage.settings.DATA_USAGE_SETTINGS"), 0);
            QsStatisticUtil.collectShortcutsCenterEvent(((TextView) this).mContext, "traffic_click");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onConfigurationChanged(Configuration configuration) {
        super/*android.widget.TextView*/.onConfigurationChanged(configuration);
        updateEverything();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mCarrierTextController.removeCallback(this.mCarrierTextCallback);
        unregister(((TextView) this).mContext);
        unregister(((TextView) this).mContext);
    }

    public /* bridge */ /* synthetic */ void setCallIndicator(IconState iconState, int i) {
        super.setCallIndicator(iconState, i);
    }

    public /* bridge */ /* synthetic */ void setEthernetIndicators(IconState iconState) {
        super.setEthernetIndicators(iconState);
    }

    public /* bridge */ /* synthetic */ void setHasAnySimReady(boolean z) {
        super.setHasAnySimReady(z);
    }

    public void setIsAdaptiveSize(boolean z) {
        this.mIsAdaptiveSize = z;
    }

    public /* bridge */ /* synthetic */ void setIsAirplaneMode(IconState iconState) {
        super.setIsAirplaneMode(iconState);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setListening(boolean z) {
        if (z == this.mListening) {
            return;
        }
        this.mListening = z;
        OplusQSCarrierTextControllerEx.getInstance(((TextView) this).mContext).setListening(z);
        if (this.mListening) {
            updateEverything();
        }
    }

    public /* bridge */ /* synthetic */ void setMobileDataEnabled(boolean z) {
        super.setMobileDataEnabled(z);
    }

    public /* bridge */ /* synthetic */ void setMobileDataIndicators(MobileDataIndicators mobileDataIndicators) {
        super.setMobileDataIndicators(mobileDataIndicators);
    }

    public /* bridge */ /* synthetic */ void setSubs(List list) {
        super.setSubs(list);
    }

    public /* bridge */ /* synthetic */ void setWifiIndicators(WifiIndicators wifiIndicators) {
        super.setWifiIndicators(wifiIndicators);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateEverything() {
        OplusQSCarrierTextController oplusQSCarrierTextController = this.mCarrierTextController;
        if (oplusQSCarrierTextController == null) {
            return;
        }
        oplusQSCarrierTextController.updateCarrierTextInBgThread(false);
        setSelected(true);
        if (this.mIsAdaptiveSize) {
            setTextSize(OplusQSFontHelper.getInstance(((TextView) this).mContext).getAdapterTextSizeSp(1));
        }
    }

    public OplusQSCarrierText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, (ActivityStarter) DependencyEx.sDependency.getDependency(ActivityStarter.class));
    }
}
