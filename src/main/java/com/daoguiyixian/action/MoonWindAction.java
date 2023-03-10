package com.daoguiyixian.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import javax.smartcardio.Card;

public class MoonWindAction extends AbstractGameAction {
    private int increaseHpAmount;
    private DamageInfo info;
    private int cardnum;
    private AbstractPlayer p;
    private static final float DURATION = 0.1F;

    public MoonWindAction(AbstractPlayer p,AbstractCreature target, DamageInfo info, int cardnum) {
        this.info = info;
        this.p = p;
        this.setValues(target, info);
        this.cardnum = cardnum;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_LIGHT));
            this.target.damage(this.info);
            if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
//                AbstractDungeon.player.increaseMaxHp(this.increaseHpAmount, false);
                //这里加能量
                p.gainEnergy(1);
                this.addToBot(new DrawCardAction(p, this.cardnum, false));

                if (this.target instanceof Donu) {
//                    UnlockTracker.unlockAchievement("DONUT");
                }
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}
