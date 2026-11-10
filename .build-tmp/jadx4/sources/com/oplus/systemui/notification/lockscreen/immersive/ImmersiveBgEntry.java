package com.oplus.systemui.notification.lockscreen.immersive;

import android.graphics.Rect;
import android.view.View;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.oplus.systemui.notification.lockscreen.seedling.ImmersiveViewEntry;
import com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingImmersiveViewState;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImmersiveBgEntry extends ImmersiveViewEntry {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ImmersiveBgEntry";
    public final int cardType;
    private final CustomRowEntry entry;
    private Rect sendBounds;
    private final ExpandableView stackView;
    private final View view;
    private final SeedlingImmersiveViewState viewState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ImmersiveBgEntry(View view, CustomRowEntry customRowEntry, SeedlingImmersiveViewState seedlingImmersiveViewState, ExpandableView expandableView, Rect rect) {
        super(customRowEntry, seedlingImmersiveViewState);
        this.view = view;
        this.entry = customRowEntry;
        this.viewState = seedlingImmersiveViewState;
        this.stackView = expandableView;
        this.sendBounds = rect;
        this.cardType = LockScreenSeedingInteractor.Companion.getBgType(getEntry());
    }

    @Override // com.oplus.systemui.notification.lockscreen.seedling.ImmersiveViewEntry
    public CustomRowEntry getEntry() {
        return this.entry;
    }

    public final Rect getSendBounds() {
        return this.sendBounds;
    }

    public final ExpandableView getStackView() {
        return this.stackView;
    }

    public final View getView() {
        return this.view;
    }

    @Override // com.oplus.systemui.notification.lockscreen.seedling.ImmersiveViewEntry
    public SeedlingImmersiveViewState getViewState() {
        return this.viewState;
    }

    public final boolean isViewSurfaceType() {
        int i = this.cardType;
        return i == -1 || i == 1;
    }

    public final void setSendBounds(Rect rect) {
        this.sendBounds = rect;
    }
}
