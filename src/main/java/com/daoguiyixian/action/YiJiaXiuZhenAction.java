package com.daoguiyixian.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.TransmutationAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

public class YiJiaXiuZhenAction extends AbstractGameAction {

    /* 84 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RecycleAction");
    /* 85 */   public static final String[] TEXT = uiStrings.TEXT;
    /*    */   private float startingDuration;
    /*    */   private DamageInfo info;
    /*    */   private AbstractPlayer p;
    /*    */ int[] base;
    /*    */  boolean isUpstate;
    /*    */ boolean extra_draw;

    public YiJiaXiuZhenAction(AbstractCreature target, DamageInfo info, boolean isUpstate) {
        /* 33 */
        this.info = info;
        /* 34 */
        setValues(target, info);
        /* 35 */
        this.actionType = AbstractGameAction.ActionType.WAIT;
        /* 36 */
        this.attackEffect = AttackEffect.NONE;
        /* 37 */
        this.startingDuration = Settings.ACTION_DUR_FAST;
        /* 38 */
        this.duration = this.startingDuration;
        /* 39 */
        this.isUpstate =isUpstate;
        /* 40 */
        this.p = AbstractDungeon.player;
        /*    */
    }


    @Override
    public void update() {

        /* 44 */
        if (this.duration == Settings.ACTION_DUR_FAST) {
            /* 45 */
            if (this.p.hand.isEmpty()) {
//                /* 46 */         addToTop((AbstractGameAction)new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.FIRE));
                /* 47 */
                this.isDone = true;
                /* 48 */
            } else if (this.p.hand.size() == 1) {
                /* 49 */
                if ((this.p.hand.getBottomCard()).color == AbstractCard.CardColor.CURSE || ( this.p.hand.getBottomCard()).type == AbstractCard.CardType.STATUS) {
                    /* 50 */
//                    addToBot((AbstractGameAction) new HealAction(this.p, this.p, base[0]));
//                    this.addToBot(new DrawCardAction(this.p, 1));
                    this.addToBot(new TransmutationAction(p,this.isUpstate, true, 1));
                    p.gainEnergy(1);
                    this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
//                    this.p.hand.getBottomCard().exhaust = true;

                    /*    */
                } else {
                    p.gainEnergy(1);
                    this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
                }
                /*    */
                /* 55 */
//                this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
                /* 56 */
                tickDuration();
                /*    */
            } else {
                /* 58 */
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
                /* 59 */
                tickDuration();
                /*    */
            }
            /*    */
        } else {
            /* 62 */
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                /* 63 */
                if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty())
                    /*    */ {
                    /* 65 */
                    for (Iterator<AbstractCard> var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); ) {
                        /* 66 */
                        AbstractCard c = var1.next();
                        /* 67 */
                        if (c.color == AbstractCard.CardColor.CURSE || (c).type == AbstractCard.CardType.STATUS) {
//                            addToBot((AbstractGameAction) new HealAction(this.p, this.p, base[0]));
                            this.p.hand.moveToExhaustPile(c);
                            this.addToBot(new TransmutationAction(p,this.isUpstate, true, 1));
                            p.gainEnergy(1);                         /*    */
                        } else {
                            this.p.hand.moveToExhaustPile(c);
                            p.gainEnergy(1);                 /*    */
                        }
                        /*    */
                    }
                    /*    */
                }
                /* 75 */
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                /* 76 */
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
                /*    */
            }
            /* 79 */
            tickDuration();
            /*    */
        }


    }
}
