package com.oplus.systemui.plugins.qs.util;

import android.view.View;
import androidx.lifecycle.LifecycleOwnerKt;
import java.lang.invoke.VarHandle;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class RepeatWhenAttachedKt {

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.util.RepeatWhenAttachedKt$repeatWhenAttached$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements DisposableHandle {
        public /* synthetic */ Ref.ObjectRef $lifecycleOwner;
        public /* synthetic */ RepeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1 $onAttachListener;
        public /* synthetic */ View $view;

        public final void dispose() {
            Assert.isMainThread();
            Ref.ObjectRef objectRef = this.$lifecycleOwner;
            ViewLifecycleOwner viewLifecycleOwner = (ViewLifecycleOwner) objectRef.element;
            if (viewLifecycleOwner != null) {
                viewLifecycleOwner.onDestroy();
            }
            objectRef.element = null;
            this.$view.removeOnAttachStateChangeListener(this.$onAttachListener);
        }
    }

    public static final DisposableHandle repeatWhenAttached(View view, CoroutineContext coroutineContext, Function3 function3) {
        Assert.isMainThread();
        CoroutineContext coroutineContextPlus = Dispatchers.getMain().plus(coroutineContext);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        RepeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1 repeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1 = new RepeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1();
        repeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1.$lifecycleOwner = objectRef;
        repeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1.$view = view;
        repeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1.$lifecycleCoroutineContext = coroutineContextPlus;
        repeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1.$block = function3;
        VarHandle.storeStoreFence();
        view.addOnAttachStateChangeListener(repeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1);
        if (view.isAttachedToWindow()) {
            ViewLifecycleOwner viewLifecycleOwner = new ViewLifecycleOwner(view);
            viewLifecycleOwner.onCreate();
            BuildersKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), coroutineContextPlus, (CoroutineStart) null, new RepeatWhenAttachedKt$createLifecycleOwnerAndRun$1$1(function3, viewLifecycleOwner, view, null), 2);
            objectRef.element = viewLifecycleOwner;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$lifecycleOwner = objectRef;
        anonymousClass1.$view = view;
        anonymousClass1.$onAttachListener = repeatWhenAttachedKt$repeatWhenAttached$onAttachListener$1;
        VarHandle.storeStoreFence();
        return anonymousClass1;
    }
}
