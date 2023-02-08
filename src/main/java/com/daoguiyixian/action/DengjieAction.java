package com.daoguiyixian.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DengjieAction extends AbstractGameAction{
    private DamageInfo info;
    private float startingDuration;

    public DengjieAction() {
//        this.info = info;
//        this.setValues(target, info);
//
//        this.actionType = AbstractGameAction.ActionType.WAIT;
//        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
//        this.startingDuration = Settings.ACTION_DUR_FAST;
//        this.duration = this.startingDuration;
    }

    public void update() {
        int count = AbstractDungeon.player.hand.size();

        int i;
//        for(i = 0; i < count; ++i) {
////            this.addToTop(new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.FIRE));
//            //抽4
//        }

        for(i = 0; i < count; ++i) {
            if (Settings.FAST_MODE) {
                this.addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else {
                this.addToTop(new ExhaustAction(1, true, true));
            }
        }

        this.isDone = true;
    }
}
