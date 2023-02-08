package com.daoguiyixian.cards;

import basemod.abstracts.CustomCard;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.power.BlackTaiSuiPower;
import com.daoguiyixian.power.LisuiPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;

public class Lisui_dog extends CustomCard {
    public static final String ID = ModHelper.MakePath(Lisui_dog.class.getSimpleName());;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMG_PATH = "lihuowangResources/img/cards/Lisui_dog.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = EXAMPLE_CARD;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    public Lisui_dog() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.timesUpgraded = 0;
        this.damage = this.baseDamage = 3;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.isInnate = true;

//        this.tags.add(CardTags.HEALING);

    }

    @Override
    public void upgrade() {

            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
//            this.upgradeBaseCost(1);
//            this.upgradeMagicNumber(1);
//            this.isInnate = true;
//            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            this.initializeDescription();

            this.upgradeDamage((this.timesUpgraded/5)); //增加数值
//            ++this.timesUpgraded;//这个可能不需要
            this.upgradeMagicNumber((this.timesUpgraded+3)/2);
            this.upgraded = true;
            this.name = cardStrings.NAME + "+" + this.timesUpgraded;
            this.initializeTitle();

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        if (abstractMonster != null) {
//            this.addToBot(new VFXAction(new HemokinesisEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, abstractMonster.hb.cX, abstractMonster.hb.cY), 0.5F));
//        }
        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new BlackTaiSuiPower(abstractPlayer, 1), 1));
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer, new ArtifactPower(abstractPlayer, 1), 1));
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer, new LisuiPower(abstractPlayer, this.timesUpgraded,this.damage,this.magicNumber), this.timesUpgraded));


    }
    public boolean canUpgrade() {
        return true;
    }
    public AbstractCard makeCopy() {
        return new Lisui_dog();
    }
}
