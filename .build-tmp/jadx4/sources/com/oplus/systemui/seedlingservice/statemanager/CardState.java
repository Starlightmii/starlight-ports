package com.oplus.systemui.seedlingservice.statemanager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CardState {
    private final long lastChangeTime;
    private final CardLifeCycleState lifeCycleState;

    public CardState(CardLifeCycleState cardLifeCycleState, long j) {
        this.lifeCycleState = cardLifeCycleState;
        this.lastChangeTime = j;
    }

    public static /* synthetic */ CardState copy$default(CardState cardState, CardLifeCycleState cardLifeCycleState, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            cardLifeCycleState = cardState.lifeCycleState;
        }
        if ((i & 2) != 0) {
            j = cardState.lastChangeTime;
        }
        return cardState.copy(cardLifeCycleState, j);
    }

    public final CardLifeCycleState component1() {
        return this.lifeCycleState;
    }

    public final long component2() {
        return this.lastChangeTime;
    }

    public final CardState copy(CardLifeCycleState cardLifeCycleState, long j) {
        return new CardState(cardLifeCycleState, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CardState)) {
            return false;
        }
        CardState cardState = (CardState) obj;
        return this.lifeCycleState == cardState.lifeCycleState && this.lastChangeTime == cardState.lastChangeTime;
    }

    public final long getLastChangeTime() {
        return this.lastChangeTime;
    }

    public final CardLifeCycleState getLifeCycleState() {
        return this.lifeCycleState;
    }

    public int hashCode() {
        return Long.hashCode(this.lastChangeTime) + (this.lifeCycleState.hashCode() * 31);
    }

    public String toString() {
        return "CardState(lifeCycleState=" + this.lifeCycleState + ", lastChangeTime=" + this.lastChangeTime + ")";
    }
}
