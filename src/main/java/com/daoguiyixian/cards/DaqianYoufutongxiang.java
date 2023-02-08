package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.BlackTaiSuiPower;
import com.daoguiyixian.power.YoufutongxiangPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

public class DaqianYoufutongxiang extends CustomCard {

    public static final String ID = ModHelper.MakePath(DaqianYoufutongxiang.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/DaqianYoufutongxiang.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    public DaqianYoufutongxiang() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;

//        this.tags.add(CardTags.HEALING);

    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
//            this.upgradeBaseCost(1);
            this.upgradeMagicNumber(1);
//            this.isInnate = true;
//            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            this.initializeDescription();

        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new YoufutongxiangPower(abstractPlayer, this.magicNumber), this.magicNumber));


    }

    public AbstractCard makeCopy() {
        return new DaqianYoufutongxiang();
    }
}
