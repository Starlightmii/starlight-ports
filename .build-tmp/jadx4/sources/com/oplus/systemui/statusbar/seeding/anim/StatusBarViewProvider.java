package com.oplus.systemui.statusbar.seeding.anim;

import android.view.ViewGroup;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarViewProvider implements IStatusBarViewProvider {
    private final Lazy endAreaContent$delegate;
    private final Lazy startAreaContent$delegate;
    private final ViewGroup statusBarContent;

    public StatusBarViewProvider(ViewGroup viewGroup) {
        this.statusBarContent = viewGroup;
        StatusBarViewProvider$$ExternalSyntheticLambda0 statusBarViewProvider$$ExternalSyntheticLambda0 = new StatusBarViewProvider$$ExternalSyntheticLambda0(0);
        statusBarViewProvider$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.startAreaContent$delegate = LazyKt__LazyJVMKt.lazy(statusBarViewProvider$$ExternalSyntheticLambda0);
        StatusBarViewProvider$$ExternalSyntheticLambda0 statusBarViewProvider$$ExternalSyntheticLambda1 = new StatusBarViewProvider$$ExternalSyntheticLambda0(1);
        statusBarViewProvider$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.endAreaContent$delegate = LazyKt__LazyJVMKt.lazy(statusBarViewProvider$$ExternalSyntheticLambda1);
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.IStatusBarViewProvider
    public /* bridge */ /* synthetic */ List endAreaIcons() {
        return super.endAreaIcons();
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.IStatusBarViewProvider
    public ViewGroup getEndAreaContent() {
        return (ViewGroup) this.endAreaContent$delegate.getValue();
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.IStatusBarViewProvider
    public ViewGroup getStartAreaContent() {
        return (ViewGroup) this.startAreaContent$delegate.getValue();
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.IStatusBarViewProvider
    public ViewGroup getStatusBarContent() {
        return this.statusBarContent;
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.IStatusBarViewProvider
    public /* bridge */ /* synthetic */ List startAreaIcons() {
        return super.startAreaIcons();
    }
}
