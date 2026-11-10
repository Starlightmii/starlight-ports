package com.oplus.systemui.qs.slider.detail.tile.volume;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import androidx.car.app.serialization.Bundler$;
import androidx.databinding.ObservableField;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.NonNullObservableField;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSVolume;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.settings.UserContextProvider;
import com.oplus.systemui.qs.anim.OplusQSActivityStarter;
import com.oplus.systemui.qs.base.seek.OplusQsVolumeSliderController;
import com.oplus.systemui.qs.base.util.PrefUtil;
import com.oplus.systemui.qs.slider.OplusQsVolumeController;
import com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaAudioMuteDetailTile extends QsDetailTileImpl {
    public static final Companion Companion = new Companion();
    public static final String TILE_SPEC = "mediumute";
    public final int detailType;
    private final NonNullObservableField<Unit> musicStreamZenMutedUpdate;
    private final NonNullObservableField<Unit> musicVolumeStateUpdate;
    private final OplusQsVolumeController.Factory qsVolumeControllerFactory;
    private final String tileSpec;
    private final UserContextProvider userContextProvider;
    private final QSVolume volumeController;

    public MediaAudioMuteDetailTile(Context context, OplusQSActivityStarter oplusQSActivityStarter, OplusQsVolumeController.Factory factory, UserContextProvider userContextProvider) {
        super(context, oplusQSActivityStarter);
        this.qsVolumeControllerFactory = factory;
        this.userContextProvider = userContextProvider;
        QSVolume qSVolumeCreate = factory.create();
        this.volumeController = qSVolumeCreate;
        ObservableField[] observableFieldArr = {qSVolumeCreate.getMusicVolumeState()};
        MediaAudioMuteDetailTile$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new MediaAudioMuteDetailTile$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.musicVolumeStateUpdate = new NonNullObservableField<>(observableFieldArr, false, externalSyntheticLambda0);
        ObservableField[] observableFieldArr2 = {qSVolumeCreate.getMusicStreamZenMuted()};
        MediaAudioMuteDetailTile$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new MediaAudioMuteDetailTile$.ExternalSyntheticLambda0(1);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.musicStreamZenMutedUpdate = new NonNullObservableField<>(observableFieldArr2, false, externalSyntheticLambda1);
        this.detailType = 2;
        this.tileSpec = TILE_SPEC;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public int getClickTipsType() {
        return 0;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public int getDetailType() {
        return this.detailType;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public Intent getLongClickIntent() {
        Intent intent = new Intent("android.settings.SOUND_SETTINGS");
        intent.addFlags(268468224);
        return intent;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public String getTileLabel() {
        return getContext().getString(2131956570);
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl, com.oplus.systemui.qs.slider.detail.tile.QsDetailTile
    public String getTileSpec() {
        return this.tileSpec;
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl
    public void handleClick(Expandable expandable) {
        QSVolume.VolumeState volumeState = (QSVolume.VolumeState) this.volumeController.getMusicVolumeState().get();
        int level = volumeState.getLevel();
        int minLevel = volumeState.getMinLevel();
        if (level == minLevel) {
            this.volumeController.setMusicVolumeLevel(PrefUtil.getInt(this.userContextProvider.getUserContext(), OplusQsVolumeSliderController.PREF_KEY_LAST_MUSIC_VOLUME_LEVEL, 1));
        } else if (level > minLevel) {
            PrefUtil.putInt(this.userContextProvider.getUserContext(), OplusQsVolumeSliderController.PREF_KEY_LAST_MUSIC_VOLUME_LEVEL, level);
            this.volumeController.setMusicVolumeLevel(minLevel);
        }
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl
    public final void handleSetListening(boolean z) {
        NonNullObservableField<Unit> nonNullObservableField = this.musicVolumeStateUpdate;
        if (z) {
            nonNullObservableField.attach();
            this.musicStreamZenMutedUpdate.attach();
            this.volumeController.registerCallbacks();
        } else {
            nonNullObservableField.detach();
            this.musicStreamZenMutedUpdate.detach();
            this.volumeController.unregisterCallbacks();
        }
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        QSVolume.VolumeState volumeState = (QSVolume.VolumeState) this.volumeController.getMusicVolumeState().get();
        boolean zBooleanValue = ((Boolean) this.volumeController.getMusicStreamZenMuted().get()).booleanValue();
        int minLevel = volumeState.getMinLevel();
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            sbM.append("handleUpdateState: musicVolumeState = " + volumeState + ", musicStreamZenMuted = " + zBooleanValue + ", minVolumeLevel = " + minLevel);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        int i = 0;
        boolean z = volumeState.getLevel() == minLevel;
        ((QSTile.State) booleanState).label = getTileLabel();
        ((QSTile.AdapterState) booleanState).value = z;
        if (!zBooleanValue) {
            i = z ? 2 : 1;
        }
        ((QSTile.State) booleanState).state = i;
        ((QSTile.State) booleanState).icon = QSTileImpl.ResourceIcon.get(2131237185);
        ((QSTile.State) booleanState).contentDescription = ((QSTile.State) booleanState).label;
        ((QSTile.State) booleanState).expandedAccessibilityClassName = Button.class.getName();
    }

    @Override // com.oplus.systemui.qs.slider.detail.tile.QsDetailTileImpl
    /* JADX INFO: renamed from: newTileState, reason: merged with bridge method [inline-methods] */
    public QSTile.BooleanState mo309newTileState() {
        return new QSTile.BooleanState();
    }
}
