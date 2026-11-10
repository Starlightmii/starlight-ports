package com.oplus.systemui.plugins.qs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation;
import com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplusos.systemui.common.model.SpanSize;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IQsComponent extends IQSExpandOrCollapseAnimation, IEditModeSwitcher {
    @Override // com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    /* bridge */ /* synthetic */ default void bindTargetProperties(Set set) {
        super.bindTargetProperties(set);
    }

    boolean checkEditModeAbnormal();

    @Override // com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    /* bridge */ /* synthetic */ default SpringTransition[] createSpringTransition(String str) {
        return super.createSpringTransition(str);
    }

    void destroyComponent();

    default void dump(PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    /* bridge */ /* synthetic */ default View getAnimatorView(int i) {
        return super.getAnimatorView(i);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    /* bridge */ /* synthetic */ default int getSubSize() {
        return super.getSubSize();
    }

    default void injectHostAbility(IComponentHostAbility iComponentHostAbility) {
    }

    void onCollapseQSPanelStart(boolean z);

    default void onEnterAddingMode(boolean z, boolean z2, Bundle bundle) {
    }

    void onEnterDragDisableMode();

    default void onEnterDragMode(boolean z, Bundle bundle) {
    }

    void onEnterEditMode(boolean z);

    void onExitAddingMode(boolean z);

    void onExitDragDisableMode();

    void onExitDragMode();

    void onExitEditMode(boolean z);

    void onThemeApplied(boolean z);

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    /* bridge */ /* synthetic */ default void saveLatestNoEditModeLocList(List list) {
        super.saveLatestNoEditModeLocList(list);
    }

    default void setEmptyTouchListener(Function1 function1) {
    }

    default void setLayerType(boolean z) {
    }

    default void setListening(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    /* bridge */ /* synthetic */ default void setNodeNumChange(IQSExpandOrCollapseAnimation.QSNodeChangeListener qSNodeChangeListener) {
        super.setNodeNumChange(qSNodeChangeListener);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.IQSExpandOrCollapseAnimation
    /* bridge */ /* synthetic */ default void setPageSelectedListener(IQSExpandOrCollapseAnimation.QSNodePageSelectedListener qSNodePageSelectedListener) {
        super.setPageSelectedListener(qSNodePageSelectedListener);
    }

    void startComponent();

    default void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
    }

    default void updateDrawable(List<SpanSize> list, boolean z) {
    }

    void updateIconDrawable(boolean z);

    void updateIconTint();

    void updateNodeTranslationY();

    default void updateOutline(List<SpanSize> list) {
    }

    void updateQuickEntranceDrawable();

    default void updateResource(boolean z, Context context) {
    }
}
