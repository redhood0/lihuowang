package com.daoguiyixian.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class XiesuiwangAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractMonster m;
    private AbstractPlayer p;
    private int damage;
    private DamageInfo damageInfo;

    public XiesuiwangAction(AbstractMonster m, DamageInfo damageInfo) {
//        this.info = info;
//        this.setValues(target, info);
//
//        this.actionType = AbstractGameAction.ActionType.WAIT;
//        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
//        this.startingDuration = Settings.ACTION_DUR_FAST;
//        this.duration = this.startingDuration;

        this.m = m;
        this.p = p;
        this.damageInfo = damageInfo;
    }

    public void update() {
        int count = AbstractDungeon.player.hand.size();
        ArrayList<AbstractCard> group = AbstractDungeon.player.exhaustPile.group;
        int curseNum = 0;
        for(AbstractCard c : group){
            if(c.type == AbstractCard.CardType.CURSE){
                curseNum++;
            }
        }
        this.addToBot(new DamageAction(m, damageInfo, AbstractGameAction.AttackEffect.BLUNT_HEAVY));


        this.isDone = true;
    }
}
