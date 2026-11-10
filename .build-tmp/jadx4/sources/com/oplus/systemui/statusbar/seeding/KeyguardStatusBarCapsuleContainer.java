package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardStatusBarCapsuleContainer extends LinearLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "KeyguardStatusBarCapsuleContainer";
    private final Lazy carrierPadding$delegate;
    private View carrierText;
    private final View.OnLayoutChangeListener layoutChangeListener;
    private View promptView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public KeyguardStatusBarCapsuleContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        KeyguardStatusBarCapsuleContainer$$ExternalSyntheticLambda0 keyguardStatusBarCapsuleContainer$$ExternalSyntheticLambda0 = new KeyguardStatusBarCapsuleContainer$$ExternalSyntheticLambda0();
        keyguardStatusBarCapsuleContainer$$ExternalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        this.carrierPadding$delegate = LazyKt__LazyJVMKt.lazy(keyguardStatusBarCapsuleContainer$$ExternalSyntheticLambda0);
        KeyguardStatusBarCapsuleContainer$layoutChangeListener$1 keyguardStatusBarCapsuleContainer$layoutChangeListener$1 = new KeyguardStatusBarCapsuleContainer$layoutChangeListener$1();
        keyguardStatusBarCapsuleContainer$layoutChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.layoutChangeListener = keyguardStatusBarCapsuleContainer$layoutChangeListener$1;
    }

    public static final void access$updateCarrierVisible(KeyguardStatusBarCapsuleContainer keyguardStatusBarCapsuleContainer, ViewGroup viewGroup) {
        if (viewGroup != null && viewGroup.getChildCount() == 2 && OplusStatusBarUtils.isCenterHole()) {
            View view = keyguardStatusBarCapsuleContainer.carrierText;
            if (view != null) {
                view.setVisibility(8);
                return;
            }
            return;
        }
        View view2 = keyguardStatusBarCapsuleContainer.carrierText;
        if (view2 != null) {
            view2.setVisibility(0);
        }
    }

    private final int getCarrierPadding() {
        return ((Number) this.carrierPadding$delegate.getValue()).intValue();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        this.carrierText = viewGroup != null ? viewGroup.findViewById(2131363560) : null;
        ViewParent parent2 = getParent();
        ViewGroup viewGroup2 = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
        this.promptView = viewGroup2 != null ? viewGroup2.findViewById(2131364693) : null;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "KeyguardStatusBarCapsuleContainer-->".concat("onAttachedToWindow " + this.carrierText + " " + this.promptView));
        }
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(getChildCount(), "onViewAdded ", "KeyguardStatusBarCapsuleContainer-->", "SystemUi--Keyguard");
        }
        View view2 = this.carrierText;
        if (view2 != null) {
            view2.setPadding(getCarrierPadding(), 0, getCarrierPadding(), 0);
        }
        View view3 = this.promptView;
        if (view3 != null) {
            view3.setVisibility(8);
        }
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.addOnLayoutChangeListener(this.layoutChangeListener);
        }
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        View view2 = this.carrierText;
        if (view2 != null) {
            view2.setPadding(0, 0, 0, 0);
        }
        View view3 = this.promptView;
        if (view3 != null) {
            view3.setVisibility(0);
        }
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.removeOnLayoutChangeListener(this.layoutChangeListener);
        }
    }
}
