package com.oplus.systemui.qs;

import android.graphics.Rect;
import android.util.MathUtils;
import android.view.View;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.qs.ExpandableNode;
import com.android.systemui.qs.QSNode;
import com.android.systemui.util.ViewController;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPanelContainerController extends ViewController implements QSNode {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSPanelContainerController";
    public float mExpansion;
    private final Rect mQsBounds;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.OplusQSPanelContainerController$onViewAttached$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnLayoutChangeListener {
        public /* synthetic */ OplusQSPanelContainerController this$0;

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.this$0.updateQsBounds$1();
        }
    }

    public OplusQSPanelContainerController(OplusQSPanelContainer oplusQSPanelContainer) {
        super(oplusQSPanelContainer);
        this.mQsBounds = new Rect();
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusQSPanelContainerController: ");
        printWriter.println("    mExpansion: " + this.mExpansion);
        printWriter.println("    mCollapseHeight: " + getCollapseHeight());
        printWriter.println("    mExpandHeight: + " + getExpandHeight());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    topGap: ", getTopGap(0.0f), printWriter);
        View view = ((ViewController) this).mView;
        if (view != null) {
            printWriter.println("    clipBounds: " + ((OplusQSPanelContainer) view).getClipBounds());
            BiometricMessageDeferral$.ExternalSyntheticOutline0.m("    translationY: ", ((OplusQSPanelContainer) ((ViewController) this).mView).getTranslationY(), printWriter);
        }
    }

    public View getAnimatorView() {
        OplusQSPanelContainer oplusQSPanelContainer = (OplusQSPanelContainer) ((ViewController) this).mView;
        if (oplusQSPanelContainer != null) {
            return oplusQSPanelContainer.getAnimatorView();
        }
        return null;
    }

    public int getCollapseHeight() {
        return ((OplusQSPanelContainer) ((ViewController) this).mView).getCollapseHeight();
    }

    public int getExpandHeight() {
        return ((OplusQSPanelContainer) ((ViewController) this).mView).getExpandHeight();
    }

    public /* bridge */ /* synthetic */ int getFloorCount() {
        return super.getFloorCount();
    }

    public final float getTopGap(float f) {
        return ((OplusQSPanelContainer) ((ViewController) this).mView).getTopGap(f);
    }

    public View getView() {
        return ((ViewController) this).mView;
    }

    public final void onViewAttached() {
        OplusQSPanelContainer oplusQSPanelContainer = (OplusQSPanelContainer) ((ViewController) this).mView;
        if (oplusQSPanelContainer != null) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            oplusQSPanelContainer.addOnLayoutChangeListener(anonymousClass1);
        }
    }

    public final void onViewDetached() {
        setListening(false);
    }

    public /* bridge */ /* synthetic */ void setContainer(ExpandableNode.OnStateListener onStateListener) {
        super.setContainer(onStateListener);
    }

    public void setCustomizing(boolean z) {
        ((OplusQSPanelContainer) ((ViewController) this).mView).setVisibility(z ? 4 : 0);
    }

    public /* bridge */ /* synthetic */ void setExpanded(boolean z) {
        super.setExpanded(z);
    }

    public void setExpansion(float f) {
        this.mExpansion = f;
        ((OplusQSPanelContainer) ((ViewController) this).mView).setExpansion(f);
        updateQsBounds$1();
    }

    public /* bridge */ /* synthetic */ void setListening(boolean z) {
        super.setListening(z);
    }

    public /* bridge */ /* synthetic */ float setTranslationY(float f, int i, Function1 function1) {
        return super.setTranslationY(f, i, function1);
    }

    public final void updateQsBounds$1() {
        OplusQSPanelContainer oplusQSPanelContainer = (OplusQSPanelContainer) ((ViewController) this).mView;
        if (this.mExpansion >= 1.0f) {
            oplusQSPanelContainer.updateQSBounds(null);
            return;
        }
        this.mQsBounds.set(0, 0, oplusQSPanelContainer.getWidth(), (int) MathUtils.lerp(getCollapseHeight(), getExpandHeight(), this.mExpansion));
        oplusQSPanelContainer.updateQSBounds(this.mQsBounds);
    }

    public final void updateViewState(float f, float f2) {
        ((OplusQSPanelContainer) ((ViewController) this).mView).updateViewState(f, f2);
    }
}
