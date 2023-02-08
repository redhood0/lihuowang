package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import basemod.devcommands.relic.Relic;
import com.daoguiyixian.action.DengjieAction;
import com.daoguiyixian.characters.Lihuowang_Character;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.relics.Dengjie;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.relics.Dengjie.DAQIANLU_LEVEL;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

public class DaqianDengJie extends CustomCard {
    public static final String ID = ModHelper.MakePath(DaqianDengJie.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/DaqianDengJie.png";
    private static final int COST = 5;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public DaqianDengJie() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 8;

        this.exhaust=true;

        this.tags.add(DA_QIAN_LU);
    }
//    public static final AbstractRelic dengjie =  new Dengjie();
    public void use(AbstractPlayer p, AbstractMonster m) {

//        this.addToBot(new LoseHPAction(p, p, 8));

        this.addToBot(new DengjieAction());
        if(upgraded){
            this.addToBot(new DrawCardAction(p, 6));

        }else {
            this.addToBot(new DrawCardAction(p, 4));
        }

        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            //99易伤
//            this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, 99, false), 99, true, AbstractGameAction.AttackEffect.NONE));
            this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 99, false), 99, true, AbstractGameAction.AttackEffect.NONE));
        }
//        this.addToBot(new DamageAllEnemiesAction(p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));

        if( AbstractDungeon.player.currentHealth < this.magicNumber){
            this.addToBot(new GainEnergyAction(2));
//            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
            this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));

//            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 1), 1));
            //获得登阶道具（0）

            if(!AbstractDungeon.player.hasRelic("lihuowang:Dengjie")){
                AbstractRelic dengjie =  new Dengjie();
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2),  dengjie);
//                DAQIANLU_LEVEL = 1;
                CardCrawlGame.screenShake.mildRumble(5.0F);
                CardCrawlGame.sound.play("BLUNT_HEAVY");

                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 8), 8));
//                dengjie.setCounter(1);
            }else {
                //调用遗物方法
//                ((Dengjie)dengjie).updateCount();
                AbstractRelic dengjie = AbstractDungeon.player.getRelic("lihuowang:Dengjie");
                dengjie.flash();

//                if(AbstractDungeon.player.hasPower("Regeneration")){
//                  int amount =   AbstractDungeon.player.getPower("Regeneration").amount;
//                  int[] number = {8,12,14,15,16,17,18,19,20,21};
//                }

                if( dengjie.counter < 10 ){
                    dengjie.counter++;
                }
                this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 8), 8));
//                if(dengjie.counter == 1){
//                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 8), 8));
//                }
//                else if(dengjie.counter == 2){
//                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 12), 12));
//                }else if(dengjie.counter == 3){
//                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 14), 14));
//
//                }else if(dengjie.counter == 4){
//                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 15), 15));
//                }else{
//                    this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 16), 16));
//                }
            }
        }

    }




    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            this.upgradeMagicNumber(2);
            this.upgradeBaseCost(4);

            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new DaqianDengJie();
    }

//    static {
//        cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");
//    }
}
