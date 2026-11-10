package com.oplus.systemui.shutdown.eximpl;

import android.content.Context;
import com.android.systemui.shutdown.AssistManagerEx;
import com.android.systemui.shutdown.GlobalActionsDialogEx;
import com.android.systemui.shutdown.GlobalActionsDialogSubDisplayEx;
import com.android.systemui.shutdown.OplusManuallyLockEx;
import com.android.systemui.shutdown.ShutDownDependencyEx;
import com.oplus.systemui.shutdown.AssistManagerImpl;
import com.oplus.systemui.shutdown.OplusGlobalActionsDialog;
import com.oplus.systemui.shutdown.OplusGlobalActionsDialogSubDisplay;
import com.oplus.systemui.shutdown.OplusManuallyLockExImpl;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShutDownDependencyExImpl extends ShutDownDependencyEx {
    private final Lazy assistManagerEx$delegate;
    private dagger.Lazy assistManagerImpl;
    private final Context context;
    private final Lazy globalActionsDialog$delegate;
    private dagger.Lazy globalActionsDialogEx;
    private final Lazy globalActionsDialogSubDisplay$delegate;
    private dagger.Lazy globalActionsDialogSubDisplayEx;
    private final Lazy oplusManuallyLockEx$delegate;
    private dagger.Lazy oplusManuallyLockExImpl;

    public static GlobalActionsDialogSubDisplayEx $r8$lambda$NXZqOw3SM17Wb27pwFzCaH_wlG4(ShutDownDependencyExImpl shutDownDependencyExImpl) {
        OplusGlobalActionsDialogSubDisplay oplusGlobalActionsDialogSubDisplay;
        dagger.Lazy lazy = shutDownDependencyExImpl.globalActionsDialogSubDisplayEx;
        return (lazy == null || (oplusGlobalActionsDialogSubDisplay = (OplusGlobalActionsDialogSubDisplay) lazy.get()) == null) ? super.getGlobalActionsDialogSubDisplay() : oplusGlobalActionsDialogSubDisplay;
    }

    public static OplusManuallyLockEx $r8$lambda$P4YvikrLUIAp8fNYMgiaqG9mpzg(ShutDownDependencyExImpl shutDownDependencyExImpl) {
        OplusManuallyLockExImpl oplusManuallyLockExImpl;
        dagger.Lazy lazy = shutDownDependencyExImpl.oplusManuallyLockExImpl;
        return (lazy == null || (oplusManuallyLockExImpl = (OplusManuallyLockExImpl) lazy.get()) == null) ? super.getOplusManuallyLockEx() : oplusManuallyLockExImpl;
    }

    /* JADX INFO: renamed from: $r8$lambda$ce-Wn60vmASh_cVGXKLhh0jQPxw, reason: not valid java name */
    public static AssistManagerEx m375$r8$lambda$ceWn60vmASh_cVGXKLhh0jQPxw(ShutDownDependencyExImpl shutDownDependencyExImpl) {
        AssistManagerImpl assistManagerImpl;
        dagger.Lazy lazy = shutDownDependencyExImpl.assistManagerImpl;
        return (lazy == null || (assistManagerImpl = (AssistManagerImpl) lazy.get()) == null) ? super.getAssistManagerEx() : assistManagerImpl;
    }

    public static GlobalActionsDialogEx $r8$lambda$qhBekX6AQo9GlFB3ew20n6pIG2I(ShutDownDependencyExImpl shutDownDependencyExImpl) {
        OplusGlobalActionsDialog oplusGlobalActionsDialog;
        dagger.Lazy lazy = shutDownDependencyExImpl.globalActionsDialogEx;
        return (lazy == null || (oplusGlobalActionsDialog = (OplusGlobalActionsDialog) lazy.get()) == null) ? super.getGlobalActionsDialog() : oplusGlobalActionsDialog;
    }

    public ShutDownDependencyExImpl(Context context) {
        this.context = context;
        ShutDownDependencyExImpl$$ExternalSyntheticLambda0 shutDownDependencyExImpl$$ExternalSyntheticLambda0 = new ShutDownDependencyExImpl$$ExternalSyntheticLambda0(0);
        shutDownDependencyExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.globalActionsDialog$delegate = LazyKt__LazyJVMKt.lazy(shutDownDependencyExImpl$$ExternalSyntheticLambda0);
        ShutDownDependencyExImpl$$ExternalSyntheticLambda0 shutDownDependencyExImpl$$ExternalSyntheticLambda1 = new ShutDownDependencyExImpl$$ExternalSyntheticLambda0(1);
        shutDownDependencyExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.globalActionsDialogSubDisplay$delegate = LazyKt__LazyJVMKt.lazy(shutDownDependencyExImpl$$ExternalSyntheticLambda1);
        ShutDownDependencyExImpl$$ExternalSyntheticLambda0 shutDownDependencyExImpl$$ExternalSyntheticLambda2 = new ShutDownDependencyExImpl$$ExternalSyntheticLambda0(2);
        shutDownDependencyExImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.assistManagerEx$delegate = LazyKt__LazyJVMKt.lazy(shutDownDependencyExImpl$$ExternalSyntheticLambda2);
        ShutDownDependencyExImpl$$ExternalSyntheticLambda0 shutDownDependencyExImpl$$ExternalSyntheticLambda3 = new ShutDownDependencyExImpl$$ExternalSyntheticLambda0(3);
        shutDownDependencyExImpl$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusManuallyLockEx$delegate = LazyKt__LazyJVMKt.lazy(shutDownDependencyExImpl$$ExternalSyntheticLambda3);
    }

    public AssistManagerEx getAssistManagerEx() {
        return (AssistManagerEx) this.assistManagerEx$delegate.getValue();
    }

    public final dagger.Lazy getAssistManagerImpl() {
        return this.assistManagerImpl;
    }

    public final Context getContext() {
        return this.context;
    }

    public GlobalActionsDialogEx getGlobalActionsDialog() {
        return (GlobalActionsDialogEx) this.globalActionsDialog$delegate.getValue();
    }

    public final dagger.Lazy getGlobalActionsDialogEx() {
        return this.globalActionsDialogEx;
    }

    public GlobalActionsDialogSubDisplayEx getGlobalActionsDialogSubDisplay() {
        return (GlobalActionsDialogSubDisplayEx) this.globalActionsDialogSubDisplay$delegate.getValue();
    }

    public final dagger.Lazy getGlobalActionsDialogSubDisplayEx() {
        return this.globalActionsDialogSubDisplayEx;
    }

    public OplusManuallyLockEx getOplusManuallyLockEx() {
        return (OplusManuallyLockEx) this.oplusManuallyLockEx$delegate.getValue();
    }

    public final dagger.Lazy getOplusManuallyLockExImpl() {
        return this.oplusManuallyLockExImpl;
    }

    public final void setAssistManagerImpl(dagger.Lazy lazy) {
        this.assistManagerImpl = lazy;
    }

    public final void setGlobalActionsDialogEx(dagger.Lazy lazy) {
        this.globalActionsDialogEx = lazy;
    }

    public final void setGlobalActionsDialogSubDisplayEx(dagger.Lazy lazy) {
        this.globalActionsDialogSubDisplayEx = lazy;
    }

    public final void setOplusManuallyLockExImpl(dagger.Lazy lazy) {
        this.oplusManuallyLockExImpl = lazy;
    }
}
