package com.daoguiyixian.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
//反伤
public class FanShangAction extends AbstractGameAction {
    private AbstractPlayer p;

    public FanShangAction() {
        this.actionType = ActionType.WAIT;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST && this.p.hasPower("Thorns")) {
            int strAmt = this.p.getPower("Thorns").amount;
            this.addToTop(new ApplyPowerAction(this.p, this.p, new ThornsPower(this.p, strAmt), strAmt));
        }

        this.tickDuration();
    }
}
