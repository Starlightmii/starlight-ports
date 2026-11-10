package com.oplus.systemui.qs;

import android.content.Context;
import com.android.systemui.fragments.FragmentHostManager;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.observer.AccessOplusSettingObserver;
import com.oplus.systemui.qs.observer.AccessOplusSettingValueProxy;
import com.oplus.systemui.qs.observer.SimpleModeObserver;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsImplHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsImplHelper";
    private Context context;
    public boolean simpleMode;
    private final AccessOplusSettingObserver accessOplusSettingObserver = new AccessOplusSettingObserver() { // from class: com.oplus.systemui.qs.OplusQsImplHelper$accessOplusSettingObserver$1
        {
            this.oldValue = -1;
        }

        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public final void onChange(boolean z) {
            FragmentHostManager fragmentHostManager;
            super.onChange(z);
            Context context = this.this$0.context;
            if (context != null) {
                OplusQSContainerImpl qsContainer = QSFragmentHelper.getInstance().getQsContainer();
                int accessOplusSettingValue = AccessOplusSettingValueProxy.Companion.getAccessOplusSettingValue(context);
                LogUtil.i$default(LogUtil.TAG_QS, "OplusQsImplHelper", "accessOplusSettingObserver qsContainer= " + qsContainer + ", oldValue= " + getOldValue() + ", value= " + accessOplusSettingValue, null, 8);
                if (getOldValue() != accessOplusSettingValue) {
                    if (this.isInitialized && qsContainer != null && (fragmentHostManager = FragmentHostManager.get(qsContainer)) != null) {
                        fragmentHostManager.reloadFragments();
                    }
                    setOldValue(accessOplusSettingValue);
                }
            }
        }
    };
    private final SimpleModeObserver simpleModeObserver = new SimpleModeObserver() { // from class: com.oplus.systemui.qs.OplusQsImplHelper$simpleModeObserver$1
        @Override // com.oplusos.systemui.common.observer.AbstractObserver
        public final void onChange(boolean z) {
            FragmentHostManager fragmentHostManager;
            super.onChange(z);
            OplusQsImplHelper oplusQsImplHelper = this.this$0;
            Context context = oplusQsImplHelper.context;
            if (context != null) {
                OplusQSContainerImpl qsContainer = QSFragmentHelper.getInstance().getQsContainer();
                boolean zIsSimpleMode = KeyguardUtils.Companion.isSimpleMode(context);
                LogUtil.i$default(LogUtil.TAG_QS, "OplusQsImplHelper", "simpleModeObserver qsContainer= " + qsContainer + ", simpleMode= " + oplusQsImplHelper.simpleMode + ", isSimpleMode= " + zIsSimpleMode, null, 8);
                if (zIsSimpleMode != oplusQsImplHelper.simpleMode) {
                    oplusQsImplHelper.simpleMode = zIsSimpleMode;
                    if (qsContainer == null || (fragmentHostManager = FragmentHostManager.get(qsContainer)) == null) {
                        return;
                    }
                    fragmentHostManager.reloadFragments();
                }
            }
        }
    };

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void registerObserver(Context context) {
        this.context = context;
        if (context != null) {
            this.simpleMode = KeyguardUtils.Companion.isSimpleMode(context);
            this.accessOplusSettingObserver.registerForUserSwitch(context, -1, false);
            this.simpleModeObserver.registerForUserSwitch(context, -1, false);
        }
    }

    public final void unRegisterObserver(Context context) {
        this.accessOplusSettingObserver.unregister(context);
        this.simpleModeObserver.unregister(context);
        this.context = null;
    }
}
