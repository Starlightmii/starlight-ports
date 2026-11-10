package com.oplus.systemui.statusbar.notification.stack;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.statusbar.notification.SourceType;
import com.android.systemui.statusbar.notification.row.ExpandableOutlineView;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSeedingContainer extends ExpandableOutlineView {
    public static final Companion Companion = new Companion();
    private SeedlingPlugin plugin;
    private Function0 visibilityListener;

    public NotificationSeedingContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static final Sequence getSeedingCards(NotificationSeedingContainer notificationSeedingContainer) {
        return Companion.getSeedingCards(notificationSeedingContainer);
    }

    public /* bridge */ /* synthetic */ float getBottomCornerRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomCornerRadius();
    }

    public /* bridge */ /* synthetic */ float getBottomRoundness() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomRoundness();
    }

    public /* bridge */ /* synthetic */ Set getBottomRoundnessSources() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getBottomRoundnessSources();
    }

    public /* bridge */ /* synthetic */ float getMaxRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getMaxRadius();
    }

    public /* bridge */ /* synthetic */ float getTopCornerRadius() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopCornerRadius();
    }

    public /* bridge */ /* synthetic */ float getTopRoundness() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopRoundness();
    }

    public /* bridge */ /* synthetic */ Set getTopRoundnessSources() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getTopRoundnessSources();
    }

    public /* bridge */ /* synthetic */ float[] getUpdatedRadii() {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.getUpdatedRadii();
    }

    public final void initPlugin(SeedlingPlugin seedlingPlugin) {
        this.plugin = seedlingPlugin;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onVisibilityChanged(View view, int i) {
        super/*android.widget.FrameLayout*/.onVisibilityChanged(view, i);
        Function0 function0 = this.visibilityListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public void performAddAnimation(long j, long j2, boolean z, boolean z2, Runnable runnable) {
    }

    public long performRemoveAnimation(long j, long j2, float f, boolean z, boolean z2, Runnable runnable, Runnable runnable2, AnimatorListenerAdapter animatorListenerAdapter, ExpandableView.ClipSide clipSide) {
        return 0L;
    }

    public /* bridge */ /* synthetic */ boolean requestBottomRoundness(float f, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestBottomRoundness(f, sourceType);
    }

    public /* bridge */ /* synthetic */ boolean requestRoundness(float f, float f2, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundness(f, f2, sourceType);
    }

    public /* bridge */ /* synthetic */ void requestRoundnessReset(SourceType sourceType) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundnessReset(sourceType);
    }

    public /* bridge */ /* synthetic */ boolean requestTopRoundness(float f, SourceType sourceType) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestTopRoundness(f, sourceType);
    }

    public final void resetMenuView(MotionEvent motionEvent, boolean z) {
    }

    public void setClipBottomAmount(int i) {
        super.setClipBottomAmount(i);
    }

    public void setClipTopAmount(int i) {
        super.setClipTopAmount(i);
    }

    public final void setSeedingVisibilityListener(Function0 function0) {
        this.visibilityListener = function0;
    }

    public void setTranslationY(float f) {
        super/*com.android.systemui.statusbar.notification.row.ExpandableView*/.setTranslationY(f);
    }

    public final boolean shouldClipToActualHeight() {
        return !FeatureOption.getUseLayeredPanelAnimation();
    }

    public /* bridge */ /* synthetic */ void updateRadii(float f, float f2, float[] fArr) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.updateRadii(f, f2, fArr);
    }

    public final void updateSeedlingCardLayeredPosition(Function1 function1) {
    }

    public /* bridge */ /* synthetic */ void requestRoundnessReset(SourceType sourceType, boolean z) {
        super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundnessReset(sourceType, z);
    }

    public /* bridge */ /* synthetic */ boolean requestRoundness(float f, float f2, SourceType sourceType, boolean z) {
        return super/*com.android.systemui.statusbar.notification.Roundable*/.requestRoundness(f, f2, sourceType, z);
    }
}
