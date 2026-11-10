package com.oplus.systemui.navigationbar.eximpl;

import android.content.Context;
import com.android.systemui.navigationbar.NavBarControllerEx;
import com.android.systemui.navigationbar.NavigationBarDependencyEx;
import com.android.systemui.navigationbar.NavigationBarGestureGearEx;
import com.android.systemui.navigationbar.NavigationBarGestureUpEx;
import com.android.systemui.navigationbar.NavigationBarViewControlEx;
import com.android.systemui.navigationbar.NavigationModeControllerEx;
import com.android.systemui.navigationbar.OplusNavigationBarManagerEx;
import com.android.systemui.navigationbar.UtilsStaticToolsEx;
import com.android.systemui.navigationbar.buttons.KeyButtonViewEx;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.oplus.systemui.navigationbar.NavBarController;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavigationBarDependencyExImpl extends NavigationBarDependencyEx {
    private final Lazy centralSurfacesImpl;
    private final Context context;
    private final kotlin.Lazy keyButtonViewEx$delegate;
    private Lazy mNavBarController;
    private NavigationBarExImpl mNavigationBarExImpl;
    private final kotlin.Lazy narBarViewControlEx$delegate;
    private final kotlin.Lazy navBarController$delegate;
    private final kotlin.Lazy navBarGestureGearEx$delegate;
    private final kotlin.Lazy navBarModeControllerEx$delegate;
    private final kotlin.Lazy navigationBarGestureUpEx$delegate;
    private final kotlin.Lazy navigationBarManager$delegate;
    private final Lazy switchCameraPreview;
    private final kotlin.Lazy utilsStaticToolsExImpl$delegate;

    /* JADX INFO: renamed from: $r8$lambda$6K6bySB_C6wP-KPja9F1S_kW_PM, reason: not valid java name */
    public static NavigationModeControllerExImpl m24$r8$lambda$6K6bySB_C6wPKPja9F1S_kW_PM(NavigationBarDependencyExImpl navigationBarDependencyExImpl) {
        return new NavigationModeControllerExImpl(navigationBarDependencyExImpl.context);
    }

    /* JADX INFO: renamed from: $r8$lambda$8t4CIYk2K-2wyzk6Jgm133sq5HQ, reason: not valid java name */
    public static NavigationBarGestureUpExlmpl m25$r8$lambda$8t4CIYk2K2wyzk6Jgm133sq5HQ(NavigationBarDependencyExImpl navigationBarDependencyExImpl) {
        return new NavigationBarGestureUpExlmpl(navigationBarDependencyExImpl.context);
    }

    public static NavigationBarGestureGearExImpl $r8$lambda$E5iSuLoAnkvQ5xH0xliAjX8OX0w(NavigationBarDependencyExImpl navigationBarDependencyExImpl) {
        return new NavigationBarGestureGearExImpl(navigationBarDependencyExImpl.context);
    }

    public static KeyButtonViewExImpl $r8$lambda$PutqrMPH1z1UQkcjHZt2E089kzE(NavigationBarDependencyExImpl navigationBarDependencyExImpl) {
        return new KeyButtonViewExImpl(navigationBarDependencyExImpl.context, navigationBarDependencyExImpl.centralSurfacesImpl, navigationBarDependencyExImpl.switchCameraPreview);
    }

    public static NavigationBarViewControlExImpl $r8$lambda$QEOM6PGJI3NPPcMad1si7RHEzCE(NavigationBarDependencyExImpl navigationBarDependencyExImpl) {
        return new NavigationBarViewControlExImpl(navigationBarDependencyExImpl.context);
    }

    /* JADX INFO: renamed from: $r8$lambda$hEy2-eKxFQEprYA_nOF-pCVLmJQ, reason: not valid java name */
    public static NavBarControllerEx m26$r8$lambda$hEy2eKxFQEprYA_nOFpCVLmJQ(NavigationBarDependencyExImpl navigationBarDependencyExImpl) {
        NavBarController navBarController;
        Lazy lazy = navigationBarDependencyExImpl.mNavBarController;
        return (lazy == null || (navBarController = (NavBarController) lazy.get()) == null) ? super.getNavBarController() : navBarController;
    }

    public static UtilsStaticToolsExImpl $r8$lambda$pURO40sGqrPuFIG_RyFWD3SXPN4(NavigationBarDependencyExImpl navigationBarDependencyExImpl) {
        return new UtilsStaticToolsExImpl(navigationBarDependencyExImpl.context);
    }

    public NavigationBarDependencyExImpl(Context context, Lazy lazy, Lazy lazy2) {
        super(context);
        this.context = context;
        this.centralSurfacesImpl = lazy;
        this.switchCameraPreview = lazy2;
        NavigationBarDependencyExImpl$$ExternalSyntheticLambda0 navigationBarDependencyExImpl$$ExternalSyntheticLambda0 = new NavigationBarDependencyExImpl$$ExternalSyntheticLambda0(0);
        navigationBarDependencyExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.narBarViewControlEx$delegate = LazyKt__LazyJVMKt.lazy(navigationBarDependencyExImpl$$ExternalSyntheticLambda0);
        NavigationBarDependencyExImpl$$ExternalSyntheticLambda0 navigationBarDependencyExImpl$$ExternalSyntheticLambda1 = new NavigationBarDependencyExImpl$$ExternalSyntheticLambda0(1);
        navigationBarDependencyExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.navBarModeControllerEx$delegate = LazyKt__LazyJVMKt.lazy(navigationBarDependencyExImpl$$ExternalSyntheticLambda1);
        NavigationBarDependencyExImpl$$ExternalSyntheticLambda0 navigationBarDependencyExImpl$$ExternalSyntheticLambda2 = new NavigationBarDependencyExImpl$$ExternalSyntheticLambda0(2);
        navigationBarDependencyExImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.keyButtonViewEx$delegate = LazyKt__LazyJVMKt.lazy(navigationBarDependencyExImpl$$ExternalSyntheticLambda2);
        NavigationBarDependencyExImpl$$ExternalSyntheticLambda0 navigationBarDependencyExImpl$$ExternalSyntheticLambda3 = new NavigationBarDependencyExImpl$$ExternalSyntheticLambda0(3);
        navigationBarDependencyExImpl$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.navBarGestureGearEx$delegate = LazyKt__LazyJVMKt.lazy(navigationBarDependencyExImpl$$ExternalSyntheticLambda3);
        NavigationBarDependencyExImpl$$ExternalSyntheticLambda0 navigationBarDependencyExImpl$$ExternalSyntheticLambda4 = new NavigationBarDependencyExImpl$$ExternalSyntheticLambda0(4);
        navigationBarDependencyExImpl$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.navBarController$delegate = LazyKt__LazyJVMKt.lazy(navigationBarDependencyExImpl$$ExternalSyntheticLambda4);
        NavigationBarDependencyExImpl$$ExternalSyntheticLambda0 navigationBarDependencyExImpl$$ExternalSyntheticLambda5 = new NavigationBarDependencyExImpl$$ExternalSyntheticLambda0(5);
        navigationBarDependencyExImpl$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.utilsStaticToolsExImpl$delegate = LazyKt__LazyJVMKt.lazy(navigationBarDependencyExImpl$$ExternalSyntheticLambda5);
        NavigationBarDependencyExImpl$$ExternalSyntheticLambda0 navigationBarDependencyExImpl$$ExternalSyntheticLambda6 = new NavigationBarDependencyExImpl$$ExternalSyntheticLambda0(6);
        navigationBarDependencyExImpl$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.navigationBarGestureUpEx$delegate = LazyKt__LazyJVMKt.lazy(navigationBarDependencyExImpl$$ExternalSyntheticLambda6);
        this.navigationBarManager$delegate = LazyKt__LazyJVMKt.lazy(new NavigationBarDependencyExImpl$$ExternalSyntheticLambda7());
    }

    public final Lazy getCentralSurfacesImpl() {
        return this.centralSurfacesImpl;
    }

    public final Context getContext() {
        return this.context;
    }

    public KeyButtonViewEx getKeyButtonViewEx() {
        return (KeyButtonViewEx) this.keyButtonViewEx$delegate.getValue();
    }

    public final Lazy getMNavBarController() {
        return this.mNavBarController;
    }

    public NavigationBarViewControlEx getNarBarViewControlEx() {
        return (NavigationBarViewControlEx) this.narBarViewControlEx$delegate.getValue();
    }

    public NavBarControllerEx getNavBarController() {
        return (NavBarControllerEx) this.navBarController$delegate.getValue();
    }

    public NavigationBarGestureGearEx getNavBarGestureGearEx() {
        return (NavigationBarGestureGearEx) this.navBarGestureGearEx$delegate.getValue();
    }

    public NavigationModeControllerEx getNavBarModeControllerEx() {
        return (NavigationModeControllerEx) this.navBarModeControllerEx$delegate.getValue();
    }

    public NavigationBarGestureUpEx getNavigationBarGestureUpEx() {
        return (NavigationBarGestureUpEx) this.navigationBarGestureUpEx$delegate.getValue();
    }

    public OplusNavigationBarManagerEx getNavigationBarManager() {
        return (OplusNavigationBarManagerEx) this.navigationBarManager$delegate.getValue();
    }

    public NavigationBarExImpl getNavigationbarEx(NavigationBar navigationBar) {
        if (this.mNavigationBarExImpl == null) {
            this.mNavigationBarExImpl = new NavigationBarExImpl(navigationBar);
        }
        NavigationBarExImpl navigationBarExImpl = this.mNavigationBarExImpl;
        navigationBarExImpl.getClass();
        return navigationBarExImpl;
    }

    public final Lazy getSwitchCameraPreview() {
        return this.switchCameraPreview;
    }

    public UtilsStaticToolsEx getUtilsStaticToolsExImpl() {
        return (UtilsStaticToolsEx) this.utilsStaticToolsExImpl$delegate.getValue();
    }

    public final void setMNavBarController(Lazy lazy) {
        this.mNavBarController = lazy;
    }
}
