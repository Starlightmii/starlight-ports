package com.oplus.systemui.qs.tiles;

import com.android.internal.logging.InstanceId;
import com.android.internal.logging.InstanceIdSequence;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import java.io.PrintWriter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusInnerFakeTile implements QSTile, Dumpable {
    public static final Companion Companion = new Companion();
    public static final String SPEC_BRIGHTNESS_VOLUME = "innerFake_brightness_volume";
    public static final String SPEC_DEVICE = "innerFake_device";
    public static final String SPEC_MEDIA = "innerFake_media";
    private final String tileSpec;
    private QSTile.BooleanState state = new QSTile.BooleanState();
    public final InstanceId instanceId = new InstanceIdSequence(100000).newInstanceId();
    private final OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState = (OplusSeparateNotificationAndQSState) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSState.class);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusInnerFakeTile(String str, boolean z) {
        this.tileSpec = str;
    }

    public void addCallback(QSTile.Callback callback) {
    }

    public void click(Expandable expandable) {
    }

    public final void destroy() {
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusInnerFakeTile:");
        printWriter.print("    " + this.tileSpec);
    }

    public int getCurrentTileUser() {
        return 0;
    }

    public InstanceId getInstanceId() {
        return this.instanceId;
    }

    public int getMetricsCategory() {
        return 0;
    }

    public QSTile.State getState() {
        return this.state;
    }

    public CharSequence getTileLabel() {
        return "";
    }

    public String getTileSpec() {
        return this.tileSpec;
    }

    public final boolean isAvailable() {
        return ((Boolean) this.oplusSeparateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue();
    }

    public final boolean isDestroyed() {
        return false;
    }

    public final boolean isListening() {
        return false;
    }

    public void longClick(Expandable expandable) {
    }

    public void preloadForSecondaryClick(Expandable expandable) {
    }

    public final void refreshState() {
    }

    public void releasePreloadedForSecondaryClick(Expandable expandable) {
    }

    public void removeCallback(QSTile.Callback callback) {
    }

    public final void removeCallbacks() {
    }

    public void secondaryClick(Expandable expandable) {
    }

    public void setDetailListening(boolean z) {
    }

    public void setListening(Object obj, boolean z) {
    }

    public void setTileSpec(String str) {
    }

    public final void userSwitch(int i) {
    }
}
