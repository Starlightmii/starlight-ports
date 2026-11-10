package com.oplus.systemui.plugins.qs.util;

import android.view.View;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RepeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ Function3 $block;
    public /* synthetic */ CoroutineContext $lifecycleCoroutineContext;
    public /* synthetic */ Ref.ObjectRef $lifecycleOwner;
    public /* synthetic */ View $view;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        Assert.isMainThread();
        ViewLifecycleOwner viewLifecycleOwner = (ViewLifecycleOwner) this.$lifecycleOwner.element;
        if (viewLifecycleOwner != null) {
            viewLifecycleOwner.onDestroy();
        }
        Ref.ObjectRef objectRef = this.$lifecycleOwner;
        View view2 = this.$view;
        CoroutineContext coroutineContext = this.$lifecycleCoroutineContext;
        Function3 function3 = this.$block;
        ViewLifecycleOwner viewLifecycleOwner2 = new ViewLifecycleOwner(view2);
        viewLifecycleOwner2.onCreate();
        BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner2), coroutineContext, (CoroutineStart) null, new RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1(function3, viewLifecycleOwner2, view2, null), 2);
        objectRef.element = viewLifecycleOwner2;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        ViewLifecycleOwner viewLifecycleOwner = (ViewLifecycleOwner) this.$lifecycleOwner.element;
        if (viewLifecycleOwner != null) {
            viewLifecycleOwner.onDestroy();
        }
        this.$lifecycleOwner.element = null;
    }
}
