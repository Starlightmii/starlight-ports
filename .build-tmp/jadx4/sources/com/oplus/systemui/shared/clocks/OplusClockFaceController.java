package com.oplus.systemui.shared.clocks;

import android.view.View;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockAnimations;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockFaceConfig;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockFaceController;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockFaceLayout;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockTickRate;
import com.android.systemui.plugins.keyguard.ui.clocks.ThemeConfig;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusClockFaceController implements ClockFaceController {
    private final ClockAnimations animations;
    private final ClockFaceConfig config;
    private final Lazy layout$delegate;
    private ThemeConfig theme;

    public OplusClockFaceController() {
        OplusClockFaceController$$ExternalSyntheticLambda0 oplusClockFaceController$$ExternalSyntheticLambda0 = new OplusClockFaceController$$ExternalSyntheticLambda0();
        oplusClockFaceController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.layout$delegate = LazyKt__LazyJVMKt.lazy(oplusClockFaceController$$ExternalSyntheticLambda0);
        this.config = new ClockFaceConfig((ClockTickRate) null, false, false, false, 15, (DefaultConstructorMarker) null);
        this.theme = new ThemeConfig(true, -1);
        this.animations = new OplusClockFaceController$animations$1();
    }

    public ClockAnimations getAnimations() {
        return this.animations;
    }

    public ClockFaceConfig getConfig() {
        return this.config;
    }

    /* JADX INFO: renamed from: getEvents */
    public abstract OplusClockFaceEvents mo367getEvents();

    public ClockFaceLayout getLayout() {
        return (ClockFaceLayout) this.layout$delegate.getValue();
    }

    public ThemeConfig getTheme() {
        return this.theme;
    }

    public abstract View getView();

    public void setTheme(ThemeConfig themeConfig) {
        this.theme = themeConfig;
    }
}
