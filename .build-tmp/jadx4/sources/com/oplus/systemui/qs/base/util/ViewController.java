package com.oplus.systemui.qs.base.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ViewController {
    public boolean mInited;
    public final AnonymousClass1 mOnAttachStateListener;
    public final View mView;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.util.ViewController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnAttachStateChangeListener {
        public /* synthetic */ ViewController this$0;

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            this.this$0.onViewAttached();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            this.this$0.onViewDetached();
        }
    }

    public ViewController(View view) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOnAttachStateListener = anonymousClass1;
        this.mView = view;
    }

    public Context getContext() {
        return this.mView.getContext();
    }

    public Resources getResources() {
        return this.mView.getResources();
    }

    public void init() {
        if (this.mInited) {
            return;
        }
        onInit();
        this.mInited = true;
        View view = this.mView;
        AnonymousClass1 anonymousClass1 = this.mOnAttachStateListener;
        if (view != null && view.isAttachedToWindow()) {
            anonymousClass1.onViewAttachedToWindow(this.mView);
        }
        View view2 = this.mView;
        if (view2 != null) {
            view2.addOnAttachStateChangeListener(anonymousClass1);
        }
    }

    public void onInit() {
    }

    public abstract void onViewAttached();

    public abstract void onViewDetached();

    public void startComponent() {
        init();
    }
}
