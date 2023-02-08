package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.tag.CustomTags.DA_QIAN_LU;

//镇定剂-回血-解除疯癫（回能）
public class AnDing extends CustomCard {

    public static final String ID = ModHelper.MakePath(AnDing.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/AnDing.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final AbstractCard.CardColor COLOR = EXAMPLE_CARD;
    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    public AnDing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 4;
        this.exhaust=true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //回血
        this.addToBot(new HealAction(p, p, this.magicNumber));
        //==>
        if(p.hasPower(ModHelper.MakePath("CrazyPower"))){
            this.addToBot(new RemoveSpecificPowerAction(p, p, ModHelper.MakePath("CrazyPower")));
            this.addToBot(new GainEnergyAction(1));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            //保留
            this.selfRetain=true;

            // 加上以下两行就能使用UPGRADE_DESCRIPTION了（如果你写了的话）
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new AnDing();
    }







//    static {
//        cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");
//    }

}
