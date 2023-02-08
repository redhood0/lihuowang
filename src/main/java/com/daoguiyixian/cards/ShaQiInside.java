package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.BlackTaiSuiPower;
import com.daoguiyixian.power.ShaQiPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

public class ShaQiInside extends CustomCard {
    public static final String ID = ModHelper.MakePath(ShaQiInside.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/ShaQiInside.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    public ShaQiInside() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
//
//        this.tags.add(CardTags.HEALING);

    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
////            this.upgradeBaseCost(1);
            this.upgradeMagicNumber(2);
//            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();

        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        if (abstractMonster != null) {
//            this.addToBot(new VFXAction(new HemokinesisEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, abstractMonster.hb.cX, abstractMonster.hb.cY), 0.5F));
//        }
//x
        if(this.upgraded){
            if(abstractPlayer.hasPower(ModHelper.MakePath("ShaQiPower"))){
                int num =   abstractPlayer.getPower(ModHelper.MakePath("ShaQiPower")).amount;
                this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ShaQiPower(abstractPlayer, num*2), num*2));
            }else {
                this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ShaQiPower(abstractPlayer, this.magicNumber), this.magicNumber));
            }
        }else {
            if(abstractPlayer.hasPower(ModHelper.MakePath("ShaQiPower"))){
                int num =   abstractPlayer.getPower(ModHelper.MakePath("ShaQiPower")).amount;
                this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ShaQiPower(abstractPlayer, num), num));
            }else {
                this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ShaQiPower(abstractPlayer, this.magicNumber), this.magicNumber));
            }
        }




    }

    public AbstractCard makeCopy() {
        return new ShaQiInside();
    }
}
