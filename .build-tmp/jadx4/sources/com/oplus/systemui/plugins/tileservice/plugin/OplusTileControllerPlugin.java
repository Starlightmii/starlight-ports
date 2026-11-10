package com.oplus.systemui.plugins.tileservice.plugin;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import android.os.Process;
import android.service.quicksettings.Tile;
import android.util.Log;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.qs.OplusTileController;
import com.oplus.quicksettings.OplusBaseTileService;
import com.oplus.quicksettings.OplusTileManager;
import com.oplus.systemui.plugins.tileservice.util.OplusTileXMLParser;
import com.oplus.systemui.plugins.tileservice.util.TileThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
@Requires(target = OplusTileController.class, version = 0)
public final class OplusTileControllerPlugin implements OplusTileController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusTileControllerPlugin";
    private final OplusBaseTileService service = new OplusBaseTileService() { // from class: com.oplus.systemui.plugins.tileservice.plugin.OplusTileControllerPlugin$service$1
        public Tile getOplusTile(ComponentName componentName) {
            OplusTileController.ITileServicesWrapper iTileServicesWrapper;
            if (componentName == null || (iTileServicesWrapper = this.this$0.wrapper) == null) {
                return null;
            }
            return iTileServicesWrapper.getQsTile(componentName);
        }

        public void setDeathRecipientToken(IBinder iBinder, ComponentName componentName) {
            TileStateController tileStateController;
            if (componentName == null || (tileStateController = this.this$0.tileStateController) == null) {
                return;
            }
            tileStateController.setDeathRecipientToken(iBinder, componentName);
        }

        public void updateOplusTile(Tile tile, ComponentName componentName) {
            if (tile != null) {
                OplusTileControllerPlugin oplusTileControllerPlugin = this.this$0;
                if (componentName != null) {
                    oplusTileControllerPlugin.getClass();
                    Log.d("OplusTileControllerPlugin", "updateOplusTile: " + ("Tile(icon=" + tile.getIcon() + ", state=" + tile.getState() + ", label=" + ((Object) tile.getLabel()) + ", subTile=" + ((Object) tile.getSubtitle()) + ")") + ", componentName: " + componentName);
                    OplusTileController.ITileServicesWrapper iTileServicesWrapper = oplusTileControllerPlugin.wrapper;
                    if (iTileServicesWrapper != null) {
                        iTileServicesWrapper.updateQsTile(componentName, tile);
                    }
                }
            }
        }
    };
    private Context systemUIContext;
    private final TileStateController.TileStateChangeListener tileStateChangeListener;
    private TileStateController tileStateController;
    private OplusTileController.ITileServicesWrapper wrapper;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.tileservice.plugin.OplusTileControllerPlugin$addTile$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ ComponentName $componentName;
        public /* synthetic */ Context $userContext;
        public /* synthetic */ OplusTileControllerPlugin this$0;

        @Override // java.lang.Runnable
        public final void run() {
            Context context = this.$userContext;
            if (context != null) {
                ComponentName componentName = this.$componentName;
                OplusTileControllerPlugin oplusTileControllerPlugin = this.this$0;
                TileStateController.OplusCustomTileServiceInfo oplusCustomTileServiceInfo = OplusTileXMLParser.Companion.getInstance().parse(componentName, context);
                if (oplusCustomTileServiceInfo != null) {
                    TileStateController tileStateController = oplusTileControllerPlugin.tileStateController;
                    if (tileStateController != null) {
                        tileStateController.addInfo(componentName, oplusCustomTileServiceInfo);
                        return;
                    }
                    return;
                }
                TileStateController tileStateController2 = oplusTileControllerPlugin.tileStateController;
                if (tileStateController2 != null) {
                    tileStateController2.removeInfo(componentName);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.tileservice.plugin.OplusTileControllerPlugin$removeTile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02471 implements Runnable {
        public /* synthetic */ ComponentName $componentName;
        public /* synthetic */ OplusTileControllerPlugin this$0;

        @Override // java.lang.Runnable
        public final void run() {
            TileStateController tileStateController = this.this$0.tileStateController;
            if (tileStateController != null) {
                tileStateController.removeInfo(this.$componentName);
            }
        }
    }

    public OplusTileControllerPlugin() {
        OplusTileControllerPlugin$tileStateChangeListener$1 oplusTileControllerPlugin$tileStateChangeListener$1 = new OplusTileControllerPlugin$tileStateChangeListener$1();
        oplusTileControllerPlugin$tileStateChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.tileStateChangeListener = oplusTileControllerPlugin$tileStateChangeListener$1;
    }

    public void addTile(ComponentName componentName, Context context) {
        Log.d(TAG, "addTile component:" + componentName);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$userContext = context;
        anonymousClass1.$componentName = componentName;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        TileThreadUtil.runOnWorkThread(anonymousClass1);
    }

    public boolean bindAppWhenListening(ComponentName componentName) {
        TileStateController tileStateController = this.tileStateController;
        if (tileStateController != null) {
            return tileStateController.bindAppWhenListening(componentName);
        }
        return false;
    }

    public void onCreate(Context context, Context context2) {
        if (!Process.myUserHandle().isSystem()) {
            Log.i(TAG, "onCreate: not SystemUser, ignore");
            return;
        }
        this.systemUIContext = context;
        Log.d(TAG, "onCreate: hostContext=" + context);
        TileStateController tileStateController = new TileStateController(context);
        tileStateController.setTileStateChangeListener(this.tileStateChangeListener);
        this.tileStateController = tileStateController;
        OplusTileManager.getInstance().registerOplusTileService(this.service);
    }

    public final void onDestroy() {
        Log.d(TAG, "onDestroy");
        TileStateController tileStateController = this.tileStateController;
        if (tileStateController != null) {
            tileStateController.setTileStateChangeListener(null);
        }
        TileStateController tileStateController2 = this.tileStateController;
        if (tileStateController2 != null) {
            tileStateController2.destroy();
        }
        TileThreadUtil.getWorkHandler().removeCallbacksAndMessages(null);
    }

    public void removeTile(ComponentName componentName) {
        Log.d(TAG, "removeTile component:" + componentName);
        RunnableC02471 runnableC02471 = new RunnableC02471();
        runnableC02471.this$0 = this;
        runnableC02471.$componentName = componentName;
        VarHandle.storeStoreFence();
        TileThreadUtil.runOnWorkThread(runnableC02471);
    }

    public void setListening(boolean z, ComponentName componentName, Context context) {
        TileStateController tileStateController = this.tileStateController;
        if (tileStateController != null) {
            tileStateController.setListening(z, componentName, context);
        }
    }

    public void setWrapper(OplusTileController.ITileServicesWrapper iTileServicesWrapper) {
        this.wrapper = iTileServicesWrapper;
    }

    public boolean shouldDisableTile(ComponentName componentName) {
        TileStateController tileStateController = this.tileStateController;
        if (tileStateController != null) {
            return tileStateController.shouldDisableTile(componentName);
        }
        return false;
    }

    public void updateCurrentStateIsNotDirty(ComponentName componentName) {
        TileStateController tileStateController = this.tileStateController;
        if (tileStateController != null) {
            tileStateController.updateCurrentStateIsNotDirty(componentName);
        }
    }
}
