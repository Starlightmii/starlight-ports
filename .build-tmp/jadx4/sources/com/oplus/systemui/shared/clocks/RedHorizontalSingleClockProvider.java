package com.oplus.systemui.shared.clocks;

import android.content.Context;
import android.view.LayoutInflater;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockController;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockMetadata;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockSettings;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class RedHorizontalSingleClockProvider extends OplusClockProvider {
    private final SysuiColorExtractor colorExtractor;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public RedHorizontalSingleClockProvider(Context context, LayoutInflater layoutInflater, SysuiColorExtractor sysuiColorExtractor) {
        super(context);
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.colorExtractor = sysuiColorExtractor;
    }

    @Override // com.oplus.systemui.shared.clocks.OplusClockProvider
    public ClockController createClock(Context context, ClockSettings clockSettings) {
        return new RedHorizontalSingleClockController(getContext(), this.layoutInflater, this.colorExtractor);
    }

    @Override // com.oplus.systemui.shared.clocks.OplusClockProvider
    public List<ClockMetadata> getClocks() {
        return CollectionsKt__CollectionsJVMKt.listOf(new ClockMetadata(RedHorizontalSingleClockProvider.class.getName(), false, (String) null, 6, (DefaultConstructorMarker) null));
    }

    public final SysuiColorExtractor getColorExtractor() {
        return this.colorExtractor;
    }

    @Override // com.oplus.systemui.shared.clocks.OplusClockProvider
    public Context getContext() {
        return this.context;
    }

    public final LayoutInflater getLayoutInflater() {
        return this.layoutInflater;
    }
}
