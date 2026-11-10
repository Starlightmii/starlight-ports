package com.oplus.systemui.statusbar.widget;

import android.view.View;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.statusbar.controller.SystemPromptController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemPromptViewController extends ViewController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SystemPromptViewController";
    private final ConfigurationController configurationController;
    private final SystemPromptController systemPromptController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.widget.SystemPromptViewController$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnClickListener {
        public /* synthetic */ SystemPromptViewController this$0;

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, SystemPromptViewController.TAG, "onClick", null, 8);
            this.this$0.systemPromptController.handleClick();
        }
    }

    public SystemPromptViewController(SystemPromptController systemPromptController, ConfigurationController configurationController, SystemPromptView systemPromptView) {
        super(systemPromptView);
        this.systemPromptController = systemPromptController;
        this.configurationController = configurationController;
    }

    public final void init() {
        super.init();
        this.systemPromptController.delayUpdatePromptIcon();
        SystemPromptView systemPromptView = (SystemPromptView) ((ViewController) this).mView;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        systemPromptView.setOnClickListener(anonymousClass1);
    }

    public final void onViewAttached() {
        this.systemPromptController.addCallback((SystemPromptController.IconVisibleChange) ((ViewController) this).mView);
        this.configurationController.addCallback(((ViewController) this).mView);
    }

    public final void onViewDetached() {
        this.systemPromptController.removeCallback((SystemPromptController.IconVisibleChange) ((ViewController) this).mView);
        this.configurationController.removeCallback(((ViewController) this).mView);
        ((SystemPromptView) ((ViewController) this).mView).setAccessibilityDelegate(null);
    }
}
