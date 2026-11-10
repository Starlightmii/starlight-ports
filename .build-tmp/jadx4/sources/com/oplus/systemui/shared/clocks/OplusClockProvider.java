package com.oplus.systemui.shared.clocks;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.systemui.plugins.keyguard.ui.clocks.AxisPresetConfig;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockController;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockMessageBuffers;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockMetadata;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockPickerConfig;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockProvider;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockSettings;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusClockProvider implements ClockProvider {
    private final Context context;
    private ClockMessageBuffers messageBuffers;

    public OplusClockProvider(Context context) {
        this.context = context;
    }

    public abstract ClockController createClock(Context context, ClockSettings clockSettings);

    public ClockPickerConfig getClockPickerConfig(ClockSettings clockSettings) {
        return new ClockPickerConfig("", "", "", getContext().getResources().getDrawable(2131232809, null), false, (List) null, (AxisPresetConfig) null, 112, (DefaultConstructorMarker) null);
    }

    public final Drawable getClockThumbnail(String str) {
        return getContext().getResources().getDrawable(2131232809, null);
    }

    public List<ClockMetadata> getClocks() {
        return CollectionsKt__CollectionsKt.emptyList();
    }

    public Context getContext() {
        return this.context;
    }

    public final ClockMessageBuffers getMessageBuffers() {
        return this.messageBuffers;
    }

    public void initialize(ClockMessageBuffers clockMessageBuffers) {
        this.messageBuffers = clockMessageBuffers;
    }

    public final void setMessageBuffers(ClockMessageBuffers clockMessageBuffers) {
        this.messageBuffers = clockMessageBuffers;
    }
}
