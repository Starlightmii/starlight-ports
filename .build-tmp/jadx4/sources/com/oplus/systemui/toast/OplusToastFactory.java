package com.oplus.systemui.toast;

import android.content.Context;
import android.view.LayoutInflater;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.ToastPlugin;
import com.oplus.systemui.common.helper.ResponsiveUIModelHelper;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusToastFactory implements Dumpable {
    public ToastPlugin mPlugin;

    /* JADX INFO: renamed from: com.oplus.systemui.toast.OplusToastFactory$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements PluginListener {
        public /* synthetic */ OplusToastFactory this$0;

        public final void onPluginConnected(Plugin plugin, Context context) {
            this.this$0.mPlugin = (ToastPlugin) plugin;
        }

        public final void onPluginDisconnected(Plugin plugin) {
            OplusToastFactory oplusToastFactory = this.this$0;
            if (((ToastPlugin) plugin).equals(oplusToastFactory.mPlugin)) {
                oplusToastFactory.mPlugin = null;
            }
        }
    }

    public OplusToastFactory(LayoutInflater layoutInflater, PluginManager pluginManager, DumpManager dumpManager) {
        dumpManager.registerDumpable("OplusToastFactory", this);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        pluginManager.addPluginListener(anonymousClass1, ToastPlugin.class, false);
    }

    public final OplusSystemUIToast createToast(Context context, CharSequence charSequence, String str, int i, int i2, ResponsiveUIModelHelper responsiveUIModelHelper) {
        if (this.mPlugin != null) {
            LayoutInflater.from(context);
            return new OplusSystemUIToast(context, charSequence, this.mPlugin.createToast(charSequence, str, i), str, i, i2, responsiveUIModelHelper);
        }
        LayoutInflater.from(context);
        return new OplusSystemUIToast(context, charSequence, null, str, i, i2, responsiveUIModelHelper);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("OplusToastFactory:", "    mAttachedPlugin=", printWriter);
        sbM.append(this.mPlugin);
        printWriter.println(sbM.toString());
    }
}
