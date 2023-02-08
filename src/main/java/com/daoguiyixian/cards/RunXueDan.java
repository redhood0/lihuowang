package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.action.RemoveRandomDebufAction;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//回8  AbstractDungeon.player.increaseMaxHp(this.increaseHpAmount, false);
public class RunXueDan extends CustomCard {
    public static final String ID = ModHelper.MakePath(RunXueDan.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/RunXueDan.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    private static int increaseHpAmount = 0;

    public RunXueDan() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        this.baseBlock = 3;
        this.magicNumber = this.baseMagicNumber = 8;
        this.exhaust=true;


    }

    public void use(AbstractPlayer p, AbstractMonster m) {

//        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new HealAction(p, p, this.magicNumber));
        if(this.upgraded){
            this.addToBot(new RemoveRandomDebufAction(AbstractDungeon.player));
        }

//        if(increaseHpAmount < 10){
//            AbstractDungeon.player.increaseMaxHp(1, false);
//        }
//        increaseHpAmount++;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
//            this.upgradeBlock(2);
            this.upgradeMagicNumber(2);

            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new RunXueDan();
    }

//    static {
//        cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");
//    }
}
