package com.oplus.sysuiinlineview.host;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import androidx.slice.widget.RowView$;
import com.android.internal.util.Preconditions;
import com.oplus.sysuiinlineview.host.controller.SysUiInlineHostViewController;
import com.oplus.sysuiinlineview.host.view.SysUiInlineHostView;
import com.oplus.sysuiinlineview.host.widget.SysUiInlineViewFactory;
import com.oplus.sysuiinlineview.utils.SysUiInlineConstants;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUiInlineViewHost implements SysUiInlineServiceManager.Listener {
    private final String category;
    private final SysUiInlineServiceManager serviceManager;
    private final SysUiInlineViewFactory viewFactory;
    private final String TAG = "SysUiInlineViewHost";
    private final WeakHashMap<SysUiInlineHostView, SysUiInlineHostViewController> mHostViews = new WeakHashMap<>();

    public SysUiInlineViewHost(String str, SysUiInlineViewFactory sysUiInlineViewFactory, SysUiInlineServiceManager sysUiInlineServiceManager) {
        this.category = str;
        this.viewFactory = sysUiInlineViewFactory;
        this.serviceManager = sysUiInlineServiceManager;
        sysUiInlineServiceManager.addListener(this);
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        Log.d(this.TAG, Intrinsics.stringPlus(Integer.valueOf(this.mHostViews.size()), "binderDied: mHostViews.size = "));
        Iterator<T> it = this.mHostViews.keySet().iterator();
        while (it.hasNext()) {
            ((SysUiInlineHostView) it.next()).binderDied();
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final SysUiInlineHostView buildHostView(Context context, ViewGroup viewGroup, boolean z) {
        String[] rEQUIRED_PERMISSIONS$SystemUiInlineView_release = SysUiInlineConstants.INSTANCE.getREQUIRED_PERMISSIONS$SystemUiInlineView_release();
        int length = rEQUIRED_PERMISSIONS$SystemUiInlineView_release.length;
        int i = 0;
        while (i < length) {
            String str = rEQUIRED_PERMISSIONS$SystemUiInlineView_release[i];
            i++;
            Preconditions.checkArgument(context.checkSelfPermission(str) == 0, Intrinsics.stringPlus(" permission not granted.", str), new Object[0]);
        }
        SysUiInlineHostView sysUiInlineHostViewMakeHostView = this.viewFactory.makeHostView(context, viewGroup, z);
        if (sysUiInlineHostViewMakeHostView == null) {
            return null;
        }
        SysUiInlineHostViewController sysUiInlineHostViewController = new SysUiInlineHostViewController(this.category, this.serviceManager);
        sysUiInlineHostViewMakeHostView.setController(sysUiInlineHostViewController);
        sysUiInlineHostViewMakeHostView.setPreviewView(this.viewFactory.makePreviewView(context, sysUiInlineHostViewMakeHostView));
        sysUiInlineHostViewMakeHostView.setSurfaceView(this.viewFactory.makeSurfaceView(context, sysUiInlineHostViewMakeHostView));
        SysUiInlineViewHost$buildHostView$1$1 sysUiInlineViewHost$buildHostView$1$1 = new SysUiInlineViewHost$buildHostView$1$1();
        sysUiInlineViewHost$buildHostView$1$1.this$0 = this;
        sysUiInlineViewHost$buildHostView$1$1.$controller = sysUiInlineHostViewController;
        VarHandle.storeStoreFence();
        sysUiInlineHostViewMakeHostView.addOnAttachStateChangeListener(sysUiInlineViewHost$buildHostView$1$1);
        return sysUiInlineHostViewMakeHostView;
    }

    public final String getCategory() {
        return this.category;
    }

    public final SysUiInlineServiceManager getServiceManager() {
        return this.serviceManager;
    }

    public final boolean isListening() {
        WeakHashMap<SysUiInlineHostView, SysUiInlineHostViewController> weakHashMap = this.mHostViews;
        boolean z = false;
        if (!weakHashMap.isEmpty()) {
            Iterator<Map.Entry<SysUiInlineHostView, SysUiInlineHostViewController>> it = weakHashMap.entrySet().iterator();
            while (it.hasNext()) {
                if (it.next().getValue().isHostViewRunning()) {
                    z = true;
                    break;
                }
            }
        }
        String str = this.TAG;
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("isListening: isListening = ", ", mHostViews.size = ", z);
        sbM.append(this.mHostViews.size());
        Log.d(str, sbM.toString());
        return z;
    }
}
