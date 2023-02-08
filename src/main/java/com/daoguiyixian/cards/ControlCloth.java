package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.DanLuPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

//拘束服-减攻-多层护甲
public class ControlCloth extends CustomCard {

    public static final String ID = ModHelper.MakePath(ControlCloth.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/ControlCloth.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    public ControlCloth() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;


        this.tags.add(CardTags.HEALING);

    }

    @Override
    public void upgrade() {

        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeMagicNumber(2);
//            this.upgradeBaseCost(1);
//            this.upgradeMagicNumber(1);
//            this.isInnate = true;
//            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            this.initializeDescription();

        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(p, AbstractDungeon.player, new PlatedArmorPower(p, this.baseMagicNumber), this.baseMagicNumber));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -3), -3));

//        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DanLuPower(abstractPlayer), 1));


    }

    public AbstractCard makeCopy() {
        return new ControlCloth();
    }


}
