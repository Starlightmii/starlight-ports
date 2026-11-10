package com.oplusos.systemui.common.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import kotlin.jvm.internal.Ref$ObjectRef;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractObserver {
    public static final Companion Companion = new Companion();
    public boolean mHasRegister;
    private String mKey;
    private final List<IObserverListener> mListeners = new CopyOnWriteArrayList();
    private ContentObserver mObserver;
    private Uri mUri;
    private WeakReference<Context> mWeakCtx;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final Function<String, Uri> toGlobalUri() {
            return AbstractObserver$Companion$toGlobalUri$1.INSTANCE;
        }

        public final Function<String, Uri> toSecureUri() {
            return AbstractObserver$Companion$toSecureUri$1.INSTANCE;
        }

        public final Function<String, Uri> toSystemUri() {
            return AbstractObserver$Companion$toSystemUri$1.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.observer.AbstractObserver$onChange$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Consumer {
        public /* synthetic */ boolean $selfChange;

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            ((IObserverListener) obj).onChange(this.$selfChange);
        }
    }

    public static final Function<String, Uri> toGlobalUri() {
        return Companion.toGlobalUri();
    }

    public static final Function<String, Uri> toSecureUri() {
        return Companion.toSecureUri();
    }

    public static final Function<String, Uri> toSystemUri() {
        return Companion.toSystemUri();
    }

    public void addListener(IObserverListener iObserverListener) {
        this.mListeners.add(iObserverListener);
    }

    public Context getContext() {
        WeakReference<Context> weakReference = this.mWeakCtx;
        if (weakReference == null) {
            return null;
        }
        weakReference.getClass();
        if (weakReference.get() == null) {
            return null;
        }
        WeakReference<Context> weakReference2 = this.mWeakCtx;
        weakReference2.getClass();
        Context context = weakReference2.get();
        context.getClass();
        return context;
    }

    public Handler getHandler() {
        return null;
    }

    public abstract String getKey();

    public final List<IObserverListener> getMListeners() {
        return this.mListeners;
    }

    public Uri getUri(Context context) {
        return null;
    }

    public void onChange(boolean z) {
        List<IObserverListener> list = this.mListeners;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$selfChange = z;
        VarHandle.storeStoreFence();
        list.forEach(anonymousClass1);
    }

    public void onRegister(Context context) {
    }

    public void register(Context context, boolean z) {
        registerImpl(context, z);
    }

    public void registerForUserSwitch(Context context, boolean z) {
        registerImpl(context, z);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, android.os.Handler] */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, android.os.Handler] */
    public final void registerImpl(Context context, boolean z) {
        if (this.mHasRegister) {
            return;
        }
        this.mWeakCtx = new WeakReference<>(context);
        this.mKey = getKey();
        Uri uri = getUri(context);
        this.mUri = uri;
        if (uri == null) {
            Function<String, Uri> uri2 = getUri();
            if (uri2 == null) {
                return;
            }
            String str = this.mKey;
            str.getClass();
            this.mUri = uri2.apply(str);
        }
        if (this.mUri == null) {
            return;
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ?? handler = getHandler();
        ref$ObjectRef.element = handler;
        if (handler == 0) {
            ref$ObjectRef.element = new Handler(Looper.getMainLooper());
        }
        if (this.mObserver == null) {
            this.mObserver = new ContentObserver(ref$ObjectRef, this) { // from class: com.oplusos.systemui.common.observer.AbstractObserver.registerImpl.1
                public final /* synthetic */ AbstractObserver this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super((Handler) ref$ObjectRef.element);
                    this.this$0 = this;
                }

                @Override // android.database.ContentObserver
                public final void onChange(boolean z2) {
                    super.onChange(z2);
                    this.this$0.onChange(z2);
                }
            };
        }
        MultiUserContentHelper.Companion companion = MultiUserContentHelper.Companion;
        Uri uri3 = this.mUri;
        uri3.getClass();
        ContentObserver contentObserver = this.mObserver;
        contentObserver.getClass();
        companion.registerForUserSwitch(context, uri3, z, contentObserver);
        this.mHasRegister = true;
        onRegister(context);
    }

    public void removeListener(IObserverListener iObserverListener) {
        this.mListeners.remove(iObserverListener);
    }

    public void unregister(Context context) {
        ContentObserver contentObserver;
        if (!this.mHasRegister || (contentObserver = this.mObserver) == null) {
            return;
        }
        MultiUserContentHelper.Companion.unregister(context, contentObserver);
        this.mHasRegister = false;
    }

    public Function<String, Uri> getUri() {
        return null;
    }

    public void register(Context context, int i, boolean z) {
        registerImpl(context, z);
    }

    public void registerForUserSwitch(Context context, int i, boolean z) {
        registerImpl(context, z);
    }
}
